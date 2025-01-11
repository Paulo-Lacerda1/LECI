/// imageBW - A simple image processing module for BW images
///           represented using run-length encoding (RLE)
///
/// This module is part of a programming project
/// for the course AED, DETI / UA.PT
///
/// You may freely use and modify this code, at your own risk,
/// as long as you give proper credit to the original and subsequent authors.
///
/// The AED Team <jmadeira@ua.pt, jmr@ua.pt, ...>
/// 2024

// Student authors (fill in below):
// NMec: 42339
// Name: Alexandre Sério
// NMec: ---
// Name: ---
//
// Date: 01/12/2024
//

#include "imageBW.h"

#include <assert.h>
#include <ctype.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "instrumentation.h"

// The data structure
//
// A BW image is stored in a structure containing 3 fields:
// Two integers store the image width and height.
// The other field is a pointer to an array that stores the pointers
// to the RLE compressed image rows.
//
// Clients should use images only through variables of type Image,
// which are pointers to the image structure, and should not access the
// structure fields directly.

// Constant value --- Use them throughout your code
// const uint8 BLACK = 1;  // Black pixel value, defined on .h
// const uint8 WHITE = 0;  // White pixel value, defined on .h
const int EOR = -1;  // Stored as the last element of a RLE row

// Internal structure for storing RLE BW images
struct image {
  uint32 width;
  uint32 height;
  int** row;  // pointer to an array of pointers referencing the compressed rows
};

// This module follows "design-by-contract" principles.
// Read `Design-by-Contract.md` for more details.

/// Error handling functions

// In this module, only functions dealing with memory allocation or file
// (I/O) operations use defensive techniques.
//
// When one of these functions fails, it signals this by returning an error
// value such as NULL or 0 (see function documentation), and sets an internal
// variable (errCause) to a string indicating the failure cause.
// The errno global variable thoroughly used in the standard library is
// carefully preserved and propagated, and clients can use it together with
// the ImageErrMsg() function to produce informative error messages.
// The use of the GNU standard library error() function is recommended for
// this purpose.
//
// Additional information:  man 3 errno;  man 3 error;

// Variable to preserve errno temporarily
static int errsave = 0;

// Error cause
static char* errCause;

/// Error cause.
/// After some other module function fails (and returns an error code),
/// calling this function retrieves an appropriate message describing the
/// failure cause.  This may be used together with global variable errno
/// to produce informative error messages (using error(), for instance).
///
/// After a successful operation, the result is not garanteed (it might be
/// the previous error cause).  It is not meant to be used in that situation!
char* ImageErrMsg() {  ///
  return errCause;
}

// Defensive programming aids
//
// Proper defensive programming in C, which lacks an exception mechanism,
// generally leads to possibly long chains of function calls, error checking,
// cleanup code, and return statements:       
//   if ( funA(x) == errorA ) { return errorX; }
//   if ( funB(x) == errorB ) { cleanupForA(); return errorY; }
//   if ( funC(x) == errorC ) { cleanupForB(); cleanupForA(); return errorZ; }
//
// Understanding such chains is difficult, and writing them is boring, messy
// and error-prone.  Programmers tend to overlook the intricate details,
// and end up producing unsafe and sometimes incorrect programs.
//
// In this module, we try to deal with these chains using a somewhat
// unorthodox technique.  It resorts to a very simple internal function
// (check) that is used to wrap the function calls and error tests, and chain
// them into a long Boolean expression that reflects the success of the entire
// operation:
//   success =
//   check( funA(x) != error , "MsgFailA" ) &&
//   check( funB(x) != error , "MsgFailB" ) &&
//   check( funC(x) != error , "MsgFailC" ) ;
//   if (!success) {
//     conditionalCleanupCode();
//   }
//   return success;
//
// When a function fails, the chain is interrupted, thanks to the
// short-circuit && operator, and execution jumps to the cleanup code.
// Meanwhile, check() set errCause to an appropriate message.
//
// This technique has some legibility issues and is not always applicable,
// but it is quite concise, and concentrates cleanup code in a single place.
//
// See example utilization in ImageLoad and ImageSave.
//
// (You are not required to use this in your code!)

// Check a condition and set errCause to failmsg in case of failure.
// This may be used to chain a sequence of operations and verify its success.
// Propagates the condition.
// Preserves global errno!
static int check(int condition, const char* failmsg) {
  errCause = (char*)(condition ? "" : failmsg);
  return condition;
}

/// Init Image library.  (Call once!)
/// Currently, simply calibrate instrumentation and set names of counters.
void ImageInit(void) {  ///
  InstrCalibrate();
  InstrName[0] = "pixel_array_accesses";  // InstrCount[0] will count pixel array acesses
  // Name other counters here...
  InstrName[1] = "Num_steps_Uncomp";
  InstrName[2] = "Num_steps_AND";
  InstrName[3] = "Num_steps_Compress";
  InstrName[4] = "Total_steps";
  InstrName[5] = "Height";
  InstrName[6] = "Width";

}
//DELIVERED

// Macros to simplify accessing instrumentation counters:
#define PIXMEM InstrCount[0]

// Add more macros here...
#define UNCOMP InstrCount[1]
#define ANDCOUNT InstrCount[2]
#define COMPR InstrCount[3]
#define TOTAL InstrCount[4]
#define HEIGHT InstrCount[5]
#define WIDTH InstrCount[6]

// TIP: Search for PIXMEM or InstrCount to see where it is incremented!

//------------------------------------------------------------------------------------------------------
/// Auxiliary (static) functions
//------------------------------------------------------------------------------------------------------

/// Create the header of an image data structure
/// And allocate the array of pointers to RLE rows
static Image AllocateImageHeader(uint32 width, uint32 height) {
  assert(width > 0 && height > 0);
  TOTAL += 3; //CONTABILIZAÇÃO DOS PASSOS DE COMPARAÇÃO E CONJUNÇÃO NO ASSERT;

  Image newHeader = malloc(sizeof(struct image)); //Alocação de memória para cabeçalho de imagem;
  assert(newHeader != NULL);                      //Verificação do sucesso da alocação;
  TOTAL += 2; //CONTABILIZAÇÃO DOS PASSOS DE ALOCAÇÃO DE MEMÓRIA E COMPARAÇÃO DO ASSERT;

  newHeader->width = width;                       //Definição da largura da imagem (INPUT);
  newHeader->height = height;                     //Definição da altura da imagem (INPUT);
  TOTAL += 2; //CONTABILIZAÇÃO DOS PASSOS DE DEFINIÇÃO DAS VARIÁVEIS;

  // Allocating the array of pointers to RLE rows
  newHeader->row = malloc(height * sizeof(int*));  //Alocação de memória para array de ponteiros para cada linha RLE da imagem (height == número total de linhas na imagem);
  assert(newHeader->row != NULL);
  TOTAL += 3; //CONTABILIZAÇÃO DO PASSO DE ALOCAÇÃO DE MEMÓRIA, COMPARAÇÃO DO ASSERT E DO SALTO DE RETORNO;
  return newHeader; //retorna o endereço para o cabeçalho da imagem;
}
//DELIVERED

/// Allocate an array to store a RLE row with n elements
static int* AllocateRLERowArray(uint32 n) {
  assert(n > 2);  //Verifica se o número de elementos no array da linha é superior a 2;
  int* newArray = malloc(n * sizeof(int));  //Alocação de memória para o array da linha com n elementos;
  assert(newArray != NULL);

  return newArray;
}
//DELIVERED

/// Conpute the number of runs of a non-compressed (RAW) image row
static uint32 GetNumRunsInRAWRow(uint32 image_width, const uint8* RAW_row) {
  assert(image_width > 0);
  assert(RAW_row != NULL);
  TOTAL += 2; //CONTABILIZAÇÃO DOS ASSERTS;
  // How many runs?
  uint32 num_runs = 1;
  TOTAL += 1; //CONTABILIZAÇÃO DA DEFINIÇÃO DA VARIÁVEL num_runs;

  for (uint32 i = 1; i < image_width; i++) {
    COMPR += 1; //CONTABILIZAÇÃO DO NÚMERO DE CICLOS NO PROCESSO DE COMPRESSÃO;
    if (RAW_row[i] != RAW_row[i - 1]) { //Se o elemento do array no índice i não for igual ao elemento anterior, incrementa uma run;
      num_runs++;
      TOTAL += 1; //CONTABILIZAÇÃO DA ADIÇÃO;
    }
    PIXMEM += 2; //CONTABILIZAÇÃO DOS ACESSOS AOS ELEMENTOS DO ARRAY DE PIXEIS PARA COMPARAÇÃO IF;
    TOTAL += 2; //CONTABILIZAÇÃO DE COMPARAÇÃO FOR E COMPARAÇÃO IF;
  }
  TOTAL += 1; //CONTABILIZAÇÃO DO SALTO DE RETORNO;
  return num_runs;//run = sequência de pixels adjacentes com o mesmo valor de intensidade;
}
//DELIVERED

/// Get the number of runs of a compressed RLE image row
static uint32 GetNumRunsInRLERow(const int* RLE_row) {
  assert(RLE_row != NULL);  //Verifica se o ponteiro inserido não aponta para nulo;

  // Go through the RLE_row until EOR is found
  // Discard RLE_row[0], since it is a pixel color

  uint32 num_runs = 0;  //Inicialização do contador de runs
  uint32 i = 1;         //Inicialização do indice a 1, uma vez o índice 0 ser correspondente ao parâmetro de cor;
  while (RLE_row[i] != EOR) { //Enquanto o elemento do RLE_row não for final de linha, incrementar o número de runs e o índice para passar ao próximo elemento;
    num_runs++;
    i++;
  }

  return num_runs;
}
//DELIVERED

/// Get the number of elements of an array storing a compressed RLE image row
static uint32 GetSizeRLERowArray(const int* RLE_row) {
  assert(RLE_row != NULL);

  // Go through the array until EOR is found
  uint32 i = 0; //Iniciação do contador de elementos do array;
  while (RLE_row[i] != EOR) { //Enquanto o elemento da linha não for final de linha, incrementar o contador;
    i++;
  }

  return (i + 1); //Devolve o número de elementos total no array a contar com mais o sinal de final de linha;
}
//DELIVERED

/// Compress into RLE format a RAW image row
/// Allocates and returns the array storing the image row in RLE format
static int* CompressRow(uint32 image_width, const uint8* RAW_row) {
  assert(image_width > 0);  //Verifica se a largura da imagem é > 0;
  assert(RAW_row != NULL);  //Verifica se o endereço para a imagem original não aponta para nulo;
  TOTAL += 2; //CONTABILIZAÇÃO DAS COMPARAÇÕES DOS ASSERTS;
  // How many runs?
  uint32 num_runs = GetNumRunsInRAWRow(image_width, RAW_row); //Armazenamento do número de runs numa linha da imagem original;
  TOTAL += 1; //CONTABILIZAÇÃO DO SALTO PARA A FUNÇÃO;

  // Allocate the RLE row array
  int* RLE_row = malloc((num_runs + 2) * sizeof(int));  //Alocação de memória para linha da imagem comprimida;
  assert(RLE_row != NULL);
  TOTAL += 2; //CONTABILIZAÇÃO DO PASSO DE ALOCAÇÃO DE MEMÓRIA E DE COMPARAÇÃO DO ASSERT;

  // Go through the RAW_row
  RLE_row[0] = (int)RAW_row[0];   // Initial pixel value (White or Black value)
  uint32 index = 1;               //Index começa em 1 uma vez que 0 é o índice para a cor do píxel inicial já definido na linha anterior;
  int num_pixels = 1;             //Inicialização do número de píxeis a 1;
  TOTAL += 3; //CONTABILIZAÇÃO DOS PASSOS DE DEFINIÇÃO DAS VARIÁVEIS;

  for (uint32 i = 1; i < image_width; i++) {  //Enquanto o índice for menor que a largura da imagem original
  TOTAL += 2; //CONTABILIZAÇÃO DOS PASSOS DE COMPARAÇÃO E ADIÇÃO DO CICLO FOR;
  COMPR += 1; //CONTABILIZAÇÃO DO NÚMERO DE CICLOS NO PROCESSO DE COMPRESSÃO;
    if (RAW_row[i] != RAW_row[i - 1]) { //Se o elemento (píxel) da imagem original for diferente do anterior
      RLE_row[index++] = num_pixels; //Adicionar mais um elemento ao array da linha de RLE com o número de píxeis iguais verificados desde a última diferença até à posição i da imagem inicial, onde o píxel seguinte difere;
      num_pixels = 0; //Reset ao contador do número de píxeis;
      TOTAL += 2; //CONTABILIZAÇÃO DOS PASSOS DE DEFINIÇÃO DAS VARIÁVEIS;
    }
    PIXMEM += 2; //CONTABILIZAÇÃO DO ACESSO AO ARRAY DE PIXEIS PARA COMPARAÇÃO EM IF;
    TOTAL += 1; //CONTABILIZAÇÃO DO PASSO DE COMPARAÇÃO IF;
    num_pixels++; //Numero de píxeis mínimo = 1, logo adicionado 1 ao contador;
    TOTAL += 1; //CONTABILIZAÇÃO DA ADIÇÃO;
  }
  RLE_row[index++] = num_pixels;
  RLE_row[index] = EOR;  // Reached the end of the row
  TOTAL += 3; //CONTABILIZAÇÃO DOS PASSOS DE DEFINIÇÃO DAS VARIÁVEIS E DO SALTO DE RETORNO;

  return RLE_row;
}
//DELIVERED

static uint8* UncompressRow(uint32 image_width, const int* RLE_row) {
  assert(image_width > 0);
  assert(RLE_row != NULL);
  TOTAL += 2; //CONTABILIZAÇÃO DO NÚMERO DE COMPARAÇÕES DOS ASSERTS;

  // The uncompressed row
  uint8* row = (uint8*)malloc(image_width * sizeof(uint8)); //Alocação de memória para ponteiro da linha RLE descomprimida;
  assert(row != NULL);
  TOTAL += 2; //CONTABILIZAÇÃO DOS PASSOS DE ALOCAÇÃO DE MEMÓRIA E COMPARAÇÃO DO ASSERT;

  // Go through the RLE_row until EOR is found
  int pixel_value = RLE_row[0]; //Valor da cor do primeiro píxel;
  uint32 i = 1; //índice;
  uint32 dest_i = 0; //índice de destino;
  TOTAL += 3; //CONTABILIZAÇÃO DOS PASSOS DE DEFINIÇÃO DAS VARIÁVEIS;

  while (RLE_row[i] != EOR) { //Enquanto o elemento da linha não for EOR
    TOTAL += 1; //CONTABILIZAÇÃO DA COMPARAÇÃO;
    // For each run
    for (int aux = 0; aux < RLE_row[i]; aux++) {  //Enquanto o índice aux for inferior ao número total de píxeis na run da linha
      TOTAL += 2; //CONTABILIZAÇÃO DA COMPARAÇÃO DO CICLO FOR E DA ADIÇÃO;
      row[dest_i++] = (uint8)pixel_value; //Elemeto do array armazena o valor de cor do píxel;
      PIXMEM += 1; //CONTABILIZAÇÃO DO NÚMERO DE ELEMENTOS ESCRITOS NO ARRAY DE PIXEIS DA LINHA RAW;
      UNCOMP += 1; //CONTABILIZAÇÃO DO NÚMERO DE PASSOS DE DESCOMPRESSÃO;
      TOTAL += 1; // CONTABILIZAÇÃO PARA O TOTAL DE PASSOS EXECUTADOS NO PROCESSO TOTAL;
    }
    // Next run
    i++; //Passa ao próximo run da linha comprimida;
    pixel_value ^= 1; //valor do píxel = valor do pixel XOR 1 (Se valor do píxel = 1, passa a 0. Se for 0 fica 0);
    TOTAL += 2; //CONTABILIZAÇÃO DO PASSO DE ADIÇÃO E INVERSÃO DE COR DO PIXEL;
  }
  TOTAL += 1; //CONTABILIZAÇÃO DO SALTO DE RETORNO;
  return row; //Retorna a linha descomprimida <=> linha raw
}
//DELIVERED

// Add your auxiliary functions here...

//------------------------------------------------------------------------------------------------------
/// Image management functions
//------------------------------------------------------------------------------------------------------

/// Create a new BW image, either BLACK or WHITE.
///   width, height : the dimensions of the new image.
///   val: the pixel color (BLACK or WHITE).
/// Requires: width and height must be non-negative, val is either BLACK or
/// WHITE.
///
/// On success, a new image is returned.
/// (The caller is responsible for destroying the returned image!)
Image ImageCreate(uint32 width, uint32 height, uint8 val) {
  assert(width > 0 && height > 0);
  assert(val == WHITE || val == BLACK);

  Image newImage = AllocateImageHeader(width, height);

  // All image pixels have the same value
  int pixel_value = (int)val;

  // Creating the image rows, each row has just 1 run of pixels
  // Each row is represented by an array of 3 elements [value,length,EOR]
  for (uint32 i = 0; i < height; i++) {
    newImage->row[i] = AllocateRLERowArray(3);
    newImage->row[i][0] = pixel_value;
    newImage->row[i][1] = (int)width;
    newImage->row[i][2] = EOR;
  }

  return newImage;
}
//DELIVERED

/// Create a new BW image, with a perfect CHESSBOARD pattern.
///   width, height : the dimensions of the new image.
///   square_edge : the lenght of the edges of the sqares making up the
///   chessboard pattern.
///   first_value: the pixel color (BLACK or WHITE) of the
///   first image pixel.
/// Requires: width and height must be non-negative, val is either BLACK or
/// WHITE.
/// Requires: for the squares, width and height must be multiples of the
/// edge lenght of the squares
///
/// On success, a new image is returned.
/// (The caller is responsible for destroying the returned image!)
Image ImageCreateChessboard(uint32 width, uint32 height, uint32 square_edge, uint8 first_value) {
  // COMPLETE THE CODE
  assert(width > 0 && height > 0);
  assert(first_value == WHITE || first_value == BLACK);
  assert(((width % square_edge)==0) && ((height % square_edge)==0));

  uint32 mem_size = 0;
  
  Image newImage = AllocateImageHeader(width, height);   //Alocação de memória; (valor comprimento [4 bytes] + valor altura [4 bytes] + array de ponteiros para linhas [4 bytes * height]
  assert(newImage!=NULL);

  mem_size += (int)sizeof(*newImage);
  //printf("Mem_size *newImage = %d\n", mem_size);

  mem_size += height * (int)sizeof(int*);
  //printf("Mem_size newImage->row = %d bytes\n", mem_size);

  int pix_val = (int)first_value; //auxiliar para inversão de cor do pixel;
  uint32 i=0; //counter for rows;
  uint32 j; //counter for columns;
  uint32 num_rle = (width/square_edge)+2;; //numero de elementos para as linhas RLE;
  uint32 num_runs = 0;

  while(i<height){  //Enquanto contador de linhas for menor que altura total
    newImage->row[i] = AllocateRLERowArray(num_rle); //Alocar memória para novo array RLE para a linha atual;
    assert(newImage!=NULL);
    mem_size += ((int)sizeof(int) * num_rle);
    num_runs += num_rle-2;

    newImage->row[i][0]=pix_val; //primeiro elemento do array da linha = valor do píxel;
    for(j=1; j<num_rle-1; j++){ // preenchimento do array da linha com as runs de pixeis iguais correspondentes aos 8 quadrados do tabuleiro de chadrez;
      newImage->row[i][j]=(int)square_edge; //fornecida a quantidade de pixeis com a mesma cor na run atual;
    }
    newImage->row[i][j]=EOR; //terminação da linha no último elemento do array RLE atual;
    i++;  //passagem para a linha seguinte;
    if(i%square_edge==0){ //por cada altura de quadrado de chadrez, inverte a cor inicial
      pix_val^=1;
    }
  }
  //printf("width_= %d ; heigth_= %d ; square_edge_= %d ; Total_num_runs_= %d ; Total_alloc_mem_= %d\n", width, height, square_edge, num_runs, mem_size);
  return newImage;
}
//OK

/// Destroy the image pointed to by (*imgp).
///   imgp : address of an Image variable.
/// If (*imgp)==NULL, no operation is performed.
/// Ensures: (*imgp)==NULL.
/// Should never fail.
void ImageDestroy(Image* imgp) {
  assert(imgp != NULL);

  Image img = *imgp;

  for (uint32 i = 0; i < img->height; i++) {
    free(img->row[i]);
  }
  free(img->row);
  free(img);

  *imgp = NULL;
}
//DELIVERED

//------------------------------------------------------------------------------------------------------
/// Printing on the console
//------------------------------------------------------------------------------------------------------

/// Output the raw BW image
void ImageRAWPrint(const Image img) {
  assert(img != NULL);

  printf("width = %d height = %d\n", img->width, img->height);
  printf(" RAW image\n");

  // Print the pixels of each image row
  for (uint32 i = 0; i < img->height; i++) {
    // The value of the first pixel in the current row
    int pixel_value = img->row[i][0];
    for (uint32 j = 1; img->row[i][j] != EOR; j++) {
      // Print the current run of pixels
      for (int k = 0; k < img->row[i][j]; k++) {
        printf("%d", pixel_value);
      }
      // Switch (XOR) to the pixel value for the next run, if any
      pixel_value ^= 1;
    }
    // At current row end
    printf("\n");
  }
  printf("\n");
}
//DELIVERED

/// Output the compressed RLE image
void ImageRLEPrint(const Image img) {
  assert(img != NULL);

  printf("width = %d height = %d\n", img->width, img->height);
  printf(" RLE encoding\n");

  // Print the compressed rows information
  for (uint32 i = 0; i < img->height; i++) {
    uint32 j;
    for (j = 0; img->row[i][j] != EOR; j++) {
      printf("%d ", img->row[i][j]);
    }
    printf("%d\n", img->row[i][j]);
  }
  printf("\n");
}
//DELIVERED

//------------------------------------------------------------------------------------------------------
/// PBM BW file operations
//------------------------------------------------------------------------------------------------------

// See PBM format specification: http://netpbm.sourceforge.net/doc/pbm.html

// Auxiliary function
static void unpackBits(int nbytes, const uint8 bytes[], uint8 raw_row[]) {
  // bitmask starts at top bit
  int offset = 0;
  uint8 mask = 1 << (7 - offset);
  while (offset < 8) {  // or (mask > 0)
    for (int b = 0; b < nbytes; b++) {
      raw_row[8 * b + offset] = (bytes[b] & mask) != 0;
    }
    mask >>= 1;
    offset++;
  }
}
//DELIVERED

// Auxiliary function
static void packBits(int nbytes, uint8 bytes[], const uint8 raw_row[]) {
  // bitmask starts at top bit
  int offset = 0;
  uint8 mask = 1 << (7 - offset); // 11111111
  while (offset < 8) {  // or (mask > 0)
    for (int b = 0; b < nbytes; b++) {
      if (offset == 0) bytes[b] = 0;
      bytes[b] |= raw_row[8 * b + offset] ? mask : 0;
    }
    mask >>= 1;
    offset++;
  }
}
//DELIVERED

// Match and skip 0 or more comment lines in file f.
// Comments start with a # and continue until the end-of-line, inclusive.
// Returns the number of comments skipped.
static int skipComments(FILE* f) {
  char c;
  int i = 0;
  while (fscanf(f, "#%*[^\n]%c", &c) == 1 && c == '\n') {
    i++;
  }
  return i;
}
//DELIVERED

/// Load a raw PBM file.
/// Only binary PBM files are accepted.
/// On success, a new image is returned.
/// (The caller is responsible for destroying the returned image!)
Image ImageLoad(const char* filename) {  ///
  int w, h;
  char c;
  FILE* f = NULL;
  Image img = NULL;

  check((f = fopen(filename, "rb")) != NULL, "Open failed");
  // Parse PBM header
  check(fscanf(f, "P%c ", &c) == 1 && c == '4', "Invalid file format");
  skipComments(f);
  check(fscanf(f, "%d ", &w) == 1 && w >= 0, "Invalid width");
  skipComments(f);
  check(fscanf(f, "%d", &h) == 1 && h >= 0, "Invalid height");
  check(fscanf(f, "%c", &c) == 1 && isspace(c), "Whitespace expected");

  // Allocate image
  img = AllocateImageHeader(w, h);

  // Read pixels
  int nbytes = (w + 8 - 1) / 8;  // number of bytes for each row
  // using VLAs...
  uint8 bytes[nbytes];
  uint8 raw_row[nbytes * 8];
  for (uint32 i = 0; i < img->height; i++) {
    check(fread(bytes, sizeof(uint8), nbytes, f) == (size_t)nbytes,
          "Reading pixels");
    unpackBits(nbytes, bytes, raw_row);
    img->row[i] = CompressRow(w, raw_row);
  }

  fclose(f);
  return img;
}
//DELIVERED

/// Save image to PBM file.
/// On success, returns unspecified integer. (No need to check!)
/// On failure, does not return, EXITS program!
int ImageSave(const Image img, const char* filename) {  ///
  assert(img != NULL);
  int w = img->width;
  int h = img->height;
  FILE* f = NULL;

  check((f = fopen(filename, "wb")) != NULL, "Open failed");
  check(fprintf(f, "P4\n%d %d\n", w, h) > 0, "Writing header failed");

  // Write pixels
  int nbytes = (w + 8 - 1) / 8;  // number of bytes for each row
  // using VLAs...
  uint8 bytes[nbytes];
  // unit8 raw_row[nbytes*8];
  for (uint32 i = 0; i < img->height; i++) {
    // UncompressRow...
    uint8* raw_row = UncompressRow(nbytes * 8, img->row[i]);
    // Fill padding pixels with WHITE
    memset(raw_row + w, WHITE, nbytes * 8 - w);
    packBits(nbytes, bytes, raw_row);
    size_t written = fwrite(bytes, sizeof(uint8), nbytes, f);
    check(written == (size_t)nbytes, "Writing pixels failed");
    free(raw_row);
  }

  // Cleanup
  fclose(f);
  return 0;
}
//DELIVERED

//------------------------------------------------------------------------------------------------------
/// Information queries
//------------------------------------------------------------------------------------------------------

/// Get image width
int ImageWidth(const Image img) {
  assert(img != NULL);
  return img->width;
}
//DELIVERED

/// Get image height
int ImageHeight(const Image img) {
  assert(img != NULL);
  return img->height;
}
//DELIVERED

//------------------------------------------------------------------------------------------------------
/// Image comparison
//------------------------------------------------------------------------------------------------------

int ImageIsEqual(const Image img1, const Image img2) {
  assert(img1 != NULL && img2 != NULL);
  // COMPLETE THE CODE
  int w1 = img1->width;
  int w2 = img2->width;
  int h1 = img1->height;
  int h2 = img2->height;
  //printf("w1 = %d ; w2 = %d ; h1 = %d ; h2 = %d\n", w1, w2, h1, h2);

  if((w1 != w2) || (h1 != h2)){ //If different widths or heights => returns 0 (not equal);
    //printf("Returned by difference in w or h...\n");
    return 0;
  }

  for(uint32 i=0; i<img1->height; i++){
    for(uint32 j=0; (img1->row[i][j]!=EOR && img2->row[i][j]!=EOR); j++){
      //printf("pxl img1 = %d ; pxl img2 = %d\n", img1->row[i][j], img2->row[i][j]);
      if(img1->row[i][j]!=img2->row[i][j]){
        return 0;
      }
    }
  }

  return 1;
}
//OK

int ImageIsDifferent(const Image img1, const Image img2) {
  assert(img1 != NULL && img2 != NULL);
  return !ImageIsEqual(img1, img2);
}
//DELIVERED

//------------------------------------------------------------------------------------------------------
/// Boolean Operations on image pixels
//------------------------------------------------------------------------------------------------------

/// These functions apply boolean operations to images,
/// returning a new image as a result.
/// Operand images are left untouched and must be of the same size.
/// On success, a new image is returned.
/// (The caller is responsible for destroying the returned image!)

Image ImageNEG(const Image img) {
  assert(img != NULL);

  uint32 width = img->width;
  uint32 height = img->height;

  Image newImage = AllocateImageHeader(width, height);

  // Directly copying the rows, one by one
  // And changing the value of row[i][0]

  for (uint32 i = 0; i < height; i++) {
    uint32 num_elems = GetSizeRLERowArray(img->row[i]);
    newImage->row[i] = AllocateRLERowArray(num_elems);
    memcpy(newImage->row[i], img->row[i], num_elems * sizeof(int));
    newImage->row[i][0] ^= 1;  // Just negate the value of the first pixel run
  }

  return newImage;
}
//DELIVERED

Image ImageAND(const Image img1, const Image img2) {
  InstrReset();
  HEIGHT = img1->height;
  WIDTH = img1->width;
  assert(img1 != NULL && img2 != NULL);
  TOTAL += 3; //CONTABILIZAÇÃO DOS PASSOS DE COMPARAÇÃO E CONJUNÇÃO NO ASSERT;
  // COMPLETE THE CODE
  // You might consider using the UncompressRow and CompressRow auxiliary files
  // Or devise a more efficient alternative

  //Verificação de condições:
  check((img1->height==img2->height)&&(img1->width==img2->width), "Image sizes are not equal!\n");
  TOTAL += 3;//CONTABILIZAÇÃO DOS PASSOS DE COMPARAÇÃO E CONJUNÇÃO NO CHECK;

  //Alocação de memória para nova imagem a ser criada:
  Image newImage = AllocateImageHeader(img1->width, img1->height);
  assert(newImage != NULL);
  TOTAL += 2; //CONTABILIZAÇÃO DOS PASSOS DE COMPARAÇÃO DO ASSERT E DO SALTO PARA A FUNÇÃO DE ALOCAÇÃO DE MEMÓRIA PARA O HEADER;

  //Tamanho da nova imagem gerada = tamanho das imagens originais:
  newImage->height = img1->height;
  newImage->width = img1->width;
  TOTAL += 2; //CONTABILIZAÇÃO DAS DUAS DEFINIÇÕES DAS VARIÁVEIS;

  //Arrays auxiliares
  uint8* raw1;
  uint8* raw2;
  uint8* nl = (uint8*) malloc(sizeof(uint8)*newImage->width);
  assert(nl != NULL);
  TOTAL += 2; //CONTABILIZAÇÃO DOS PASSOS DE ALOCAÇÃO DE MEMÓRIA E DE COMPARAÇÃO DO ASSERT;

  for(uint32 i=0; i<newImage->height; i++){ //Para todas as linhas da nova imagem
    TOTAL += 1; //CONTABILIZAÇÃO DA COMPARAÇÃO DO CICLO FOR;
    raw1 = UncompressRow(img1->width, img1->row[i]); //Descomprime a linha RLE imagem 1 <=> img1->raw_row
    raw2 = UncompressRow(img2->width, img2->row[i]); //Descomprime a linha RLE imagem 2 <=> img2->raw_row
    TOTAL += 2; //CONTABILIZAÇÃO DOS DOIS SALTOS PARA AS FUNÇÕES UNCOMPRESSROW();
    
    for(uint32 j=0; j<newImage->width; j++){ //Para todos os pixeis da nova linha
      nl[j] = raw1[j] & raw2[j]; //pixel nova linha = pixel linha img1 AND pixel linha img2
      ANDCOUNT += 1; //CONTABILIZAÇÃO DO NÚMERO DE ANDS EXECUTADOS;
      PIXMEM += 3; //CONTABILIZAÇÃO DO NÚMERO DE ACESSOS AOS ARRAYS DE PIXEIS;
    }
    newImage->row[i]=CompressRow(newImage->width, nl); //Compressão da nova linha para RLE na nova imagem
    TOTAL += 1; //CONTABILIZAÇÃO DO SALTO PARA A FUNÇÃO;
    free(raw1);
    free(raw2);
    TOTAL += 2; //CONTABILIZAÇÃO DOS PASSOS DE LIBERTAÇÃO DE MEMÓRIA;
  }
  free(nl);
  TOTAL += 2; //CONTABILIZAÇÃO DA LIBERTAÇÃO DE MEMÓRIA E DO SALTO DE RETORNO;
  InstrPrint();
  return newImage;
}

Image ImageOR(const Image img1, const Image img2) {
  assert(img1 != NULL && img2 != NULL);
  // COMPLETE THE CODE
  // You might consider using the UncompressRow and CompressRow auxiliary files
  // Or devise a more efficient alternative
  //Verificação de condições:
  check((img1->height==img2->height)&&(img1->width==img2->width), "Image sizes are not equal!\n");

  //Alocação de memória para nova imagem a ser criada:
  Image newImage = AllocateImageHeader(img1->width, img1->height);
  assert(newImage != NULL);

  //Tamanho da nova imagem gerada = tamanho das imagens originais:
  newImage->height = img1->height;
  newImage->width = img1->width;

  //Arrays auxiliares
  uint8* raw1;
  uint8* raw2;
  uint8* nl = (uint8*) malloc(sizeof(uint8)*newImage->width);
  assert(nl != NULL);

  for(uint32 i=0; i<newImage->height; i++){ //Para todas as linhas da nova imagem
    raw1 = UncompressRow(img1->width, img1->row[i]); //Descomprime a linha RLE imagem 1 <=> img1->raw_row
    raw2 = UncompressRow(img2->width, img2->row[i]); //Descomprime a linha RLE imagem 2 <=> img2->raw_row
    for(uint32 j=0; j<newImage->width; j++){ //Para todos os pixeis da nova linha
      nl[j] = raw1[j] | raw2[j]; //pixel nova linha = pixel linha img1 OR pixel linha img2
    }
    newImage->row[i]=CompressRow(newImage->width, nl); //Compressão da nova linha para RLE na nova imagem
    free(raw1);
    free(raw2);
  }
  free(nl);

  return newImage;
}

Image ImageXOR(Image img1, Image img2) {
  assert(img1 != NULL && img2 != NULL);
  // COMPLETE THE CODE
  // You might consider using the UncompressRow and CompressRow auxiliary files
  // Or devise a more efficient alternative
  //Verificação de condições:
  check((img1->height==img2->height)&&(img1->width==img2->width), "Image sizes are not equal!\n");

  //Alocação de memória para nova imagem a ser criada:
  Image newImage = AllocateImageHeader(img1->width, img1->height);
  assert(newImage != NULL);

  //Tamanho da nova imagem gerada = tamanho das imagens originais:
  newImage->height = img1->height;
  newImage->width = img1->width;

  //Arrays auxiliares
  uint8* raw1;
  uint8* raw2;
  uint8* nl = (uint8*) malloc(sizeof(uint8)*newImage->width);
  assert(nl != NULL);

  for(uint32 i=0; i<newImage->height; i++){ //Para todas as linhas da nova imagem
    raw1 = UncompressRow(img1->width, img1->row[i]); //Descomprime a linha RLE imagem 1 <=> img1->raw_row
    raw2 = UncompressRow(img2->width, img2->row[i]); //Descomprime a linha RLE imagem 2 <=> img2->raw_row
    for(uint32 j=0; j<newImage->width; j++){ //Para todos os pixeis da nova linha
      nl[j] = raw1[j] ^ raw2[j]; //pixel nova linha = pixel linha img1 OR pixel linha img2
    }
    newImage->row[i]=CompressRow(newImage->width, nl); //Compressão da nova linha para RLE na nova imagem
    free(raw1);
    free(raw2);
  }
  free(nl);

  return newImage;
}

//------------------------------------------------------------------------------------------------------
/// Geometric transformations
//------------------------------------------------------------------------------------------------------

/// These functions apply geometric transformations to an image,
/// returning a new image as a result.
///
/// On success, a new image is returned.
/// (The caller is responsible for destroying the returned image!)

/// Mirror an image = flip top-bottom.
/// Returns a mirrored version of the image.
/// Ensures: The original img is not modified.
///
/// On success, a new image is returned.
/// (The caller is responsible for destroying the returned image!)
Image ImageHorizontalMirror(const Image img) {
  assert(img != NULL);

  uint32 width = img->width;
  uint32 height = img->height;

  Image newImage = AllocateImageHeader(width, height);
  // COMPLETE THE CODE
  assert(newImage!=NULL);
  newImage->height = height;
  newImage->width = width;

  for(uint32 i=0; i<img->height; i++){
    uint32 row_size = GetNumRunsInRLERow(img->row[img->height-1-i]) + 2;  //Numero de runs (elementos do array de linha) + cor píxel inicial + EOR;
    newImage->row[i] = AllocateRLERowArray(row_size); //Alocação de memória para array da linha RLE newImage;
    assert(newImage->row[i] != NULL);
    newImage->row[i][0] = img->row[img->height-1-i][0]; //Cópia do valor da primeira cor do píxel;
    
    for(uint32 j=1; j<newImage->width; j++){  //Para toda a largura da imagem nova, excepto primeiro valor do píxel

      if(img->row[img->height-1-i][j] == EOR){  //Se elemento é o último elemento da linha da img
        newImage->row[i][j]=EOR;  //termina linha na nova imagem;
        break;
      }
      //printf("newImage->row[i=%d][j=%d] = img->row[img->height-1-i=%d][j=%d]\n", i, j, img->height-1-i, j); //DEBUG
      newImage->row[i][j] = img->row[img->height-1-i][j]; //cópia dos valores dos elemntos da linha RLE de img para newImage, desde ultima linha até primeira linha
    }
  }

  return newImage;
}
//OK

/// Mirror an image = flip left-right.
/// Returns a mirrored version of the image.
/// Ensures: The original img is not modified.
///
/// On success, a new image is returned.
/// (The caller is responsible for destroying the returned image!)
Image ImageVerticalMirror(const Image img) {
  assert(img != NULL);

  uint32 width = img->width;
  uint32 height = img->height;

  Image newImage = AllocateImageHeader(width, height);
  // COMPLETE THE CODE
  assert(newImage != NULL);

  newImage->height = height;
  newImage->width = width;

  for(uint32 i=0; i<height; i++){
    uint32 row_tot_elem = GetNumRunsInRLERow(img->row[i])+2;
    newImage->row[i] = AllocateRLERowArray(row_tot_elem);
    assert(newImage->row[i] != NULL);
    if(GetNumRunsInRLERow(img->row[i])%2!=0){
      newImage->row[i][0] = img->row[i][0];
    } else {
      newImage->row[i][0] = !img->row[i][0];
    }
    for(uint32 j=1; j<row_tot_elem; j++){  //para todos os runs da img <=> para toda a linha de img exceto primeiro píxel e EOR
      newImage->row[i][j] = img->row[i][row_tot_elem-1-j];
    }
    newImage->row[i][row_tot_elem-1] = EOR;
  }

  return newImage;
}
//OK

/// Replicate img2 at the bottom of img1, creating a larger image
/// Requires: the width of the two images must be the same.
/// Returns the new larger image.
/// Ensures: The original images are not modified.
///
/// On success, a new image is returned.
/// (The caller is responsible for destroying the returned image!)
Image ImageReplicateAtBottom(const Image img1, const Image img2) {
  assert(img1 != NULL && img2 != NULL);
  assert(img1->width == img2->width);

  uint32 new_width = img1->width;
  uint32 new_height = img1->height + img2->height;

  Image newImage = AllocateImageHeader(new_width, new_height);
  // COMPLETE THE CODE
  assert(newImage!=NULL);

  uint32 row_len;
  uint8 fst_pixel;
  uint32 i;
  uint32 j;

  for(i=0; i<newImage->height; i++){
    if(i<img1->height){
      row_len = GetNumRunsInRLERow(img1->row[i])+2;
      fst_pixel = img1->row[i][0];
    } else {
      row_len = GetNumRunsInRLERow(img2->row[i-img1->height])+2;
      fst_pixel = img2->row[i-img1->height][0];
    }
    newImage->row[i]=AllocateRLERowArray(row_len);
    newImage->row[i][0]=fst_pixel;
    for(j=1; j<row_len-1; j++){
      if(i<img1->height){
        newImage->row[i][j]=img1->row[i][j];
      } else {
        newImage->row[i][j]=img2->row[i-img1->height][j];
      }
    }
    newImage->row[i][j]=EOR;
  }

  return newImage;
}

/// Replicate img2 to the right of imag1, creating a larger image
/// Requires: the height of the two images must be the same.
/// Returns the new larger image.
/// Ensures: The original images are not modified.
///
/// On success, a new image is returned.
/// (The caller is responsible for destroying the returned image!)
Image ImageReplicateAtRight(const Image img1, const Image img2) {
  assert(img1 != NULL && img2 != NULL);
  assert(img1->height == img2->height);

  uint32 new_width = img1->width + img2->width;
  uint32 new_height = img1->height;

  Image newImage = AllocateImageHeader(new_width, new_height);
  // COMPLETE THE CODE
  assert(newImage != NULL);
  
  uint32 i;
  uint32 j;
  uint8 img1_fst_pxl;
  uint8 img2_fst_pxl;
  uint32 img1_rle_len;
  uint32 img2_rle_len;
  uint32 total_rle_len;

  for(i=0; i<new_height; i++){
    img1_rle_len = GetNumRunsInRLERow(img1->row[i]); //numero de runs na primeira imagem;
    img2_rle_len = GetNumRunsInRLERow(img2->row[i]); //numero de runs na segunda imagem;
    total_rle_len = img1_rle_len + img2_rle_len + 2; //total de runs na nova imagem mais primeiro pixel e EOR;
    img1_fst_pxl = img1->row[i][0]; //cor do 1º pixel da imagem 1;
    img2_fst_pxl = img2->row[i][0]; //cor do 1º pixel da imagem 2;

    if(((img1_rle_len%2)==0 && (img1_fst_pxl==img2_fst_pxl)) || ((img1_rle_len%2)!=0 && (img1_fst_pxl!=img2_fst_pxl))){
      newImage->row[i]=AllocateRLERowArray(total_rle_len);
      newImage->row[i][0] = img1_fst_pxl; //cor do pixel inicial = cor do pixel inicial da img1;
      for(j=1; j<total_rle_len-1; j++){
        if(j<=img1_rle_len){
          newImage->row[i][j] = img1->row[i][j];  //cópia do array RLE da img1 até EOR (exclusive);
        } else {
          newImage->row[i][j] = img2->row[i][j-img1_rle_len]; //cópia do array RLE da img2 até EOR (exclusive);
        }
      }
      newImage->row[i][j] = EOR;
    }
    else {
      total_rle_len = total_rle_len-1;  //eliminar uma run na linha, dado a 1ª run da img2 ser somada à última run da img1;
      newImage->row[i]=AllocateRLERowArray(total_rle_len);
      newImage->row[i][0] = img1_fst_pxl; //cor do pixel inicial = cor do pixel inicial da img1;
      for(j=1; j<total_rle_len-1; j++){
        if(j<img1_rle_len){
          newImage->row[i][j] = img1->row[i][j]; //cópia do array RLE da img1 até ao último elemento (exclusive);
        }
        else if(j==img1_rle_len){
          newImage->row[i][j] = img1->row[i][j]+img2->row[i][j-(img1_rle_len-1)]; //último elemento rle da img1 + primeiro elemento rle da img2;
        }
        else {
          newImage->row[i][j] = img2->row[i][j-(img1_rle_len-1)];
        }
      }
      newImage->row[i][j] = EOR;
    }
  }
  
  return newImage;
}
