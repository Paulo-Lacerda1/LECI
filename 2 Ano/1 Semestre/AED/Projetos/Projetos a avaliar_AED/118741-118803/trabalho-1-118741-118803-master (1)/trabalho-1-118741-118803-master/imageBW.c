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
// NMec: 118741
// Name: Paulo Cunha
// NMec: 118803
// Name: Rafael Ferreira
//
// Date: 29/11/2024
//

#include "imageBW.h"

#include <time.h>

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

// In this module, only functions dealing with memory allocation or
// file (I/O) operations use defensive techniques.
// When one of these functions fails,
// it immediately prints an error and exits the program.
// This fail-fast approach to error handling is simpler for the programmer.

// Use the following function to check a condition
// and exit if it fails.

// Check a condition and if false, print failmsg and exit.
static void check(int condition, const char* failmsg) {
  if (!condition) {
    perror(failmsg);
    exit(errno || 255);
  }
}

/// Init Image library.  (Call once!)
/// Currently, simply calibrate instrumentation and set names of counters.
void ImageInit(void) {  ///
  InstrCalibrate();
  InstrName[0] = "pixmem";  // InstrCount[0] will count pixel array acesses
  InstrName[1] = "memoryUsage_Total";
}

// Macros to simplify accessing instrumentation counters:
#define PIXMEM InstrCount[0]
#define MEMORY_USAGE InstrCount[1]
// Add more macros here...

// TIP: Search for PIXMEM or InstrCount to see where it is incremented!

/// Auxiliary (static) functions

/// Create the header of an image data structure
/// And allocate the array of pointers to RLE rows
static Image AllocateImageHeader(uint32 width, uint32 height) {
  assert(width > 0 && height > 0);
  Image newHeader = malloc(sizeof(struct image));
  check(newHeader != NULL, "malloc");

  newHeader->width = width;
  newHeader->height = height;

  // Allocating the array of pointers to RLE rows
  newHeader->row = malloc(height * sizeof(int*));
  check(newHeader->row != NULL, "malloc");

  return newHeader;
}

/// Allocate an array to store a RLE row with n elements
static int* AllocateRLERowArray(uint32 n) {
  assert(n > 2);
  int* newArray = malloc(n * sizeof(int));
  check(newArray != NULL, "malloc");

  return newArray;
}

/// Compute the number of runs of a non-compressed (RAW) image row
static uint32 GetNumRunsInRAWRow(uint32 image_width, const uint8* RAW_row) {
  assert(image_width > 0);
  assert(RAW_row != NULL);

  // How many runs?
  uint32 num_runs = 1;
  for (uint32 i = 1; i < image_width; i++) {
    if (RAW_row[i] != RAW_row[i - 1]) {
      num_runs++;
    }
  }

  return num_runs;
}

/// Get the number of runs of a compressed RLE image row
static uint32 GetNumRunsInRLERow(const int* RLE_row) {
  assert(RLE_row != NULL);

  // Go through the RLE_row until EOR is found
  // Discard RLE_row[0], since it is a pixel color

  uint32 num_runs = 0;
  uint32 i = 1;
  while (RLE_row[i] != EOR) {
    num_runs++;
    i++;
  }

  return num_runs;
}

/// Get the number of elements of an array storing a compressed RLE image row
static uint32 GetSizeRLERowArray(const int* RLE_row) {
  assert(RLE_row != NULL);

  // Go through the array until EOR is found
  uint32 i = 0;
  while (RLE_row[i] != EOR) {
    i++;
  }

  return (i + 1);
}

/// Compress into RLE format a RAW image row
/// Allocates and returns the array storing the image row in RLE format
static int* CompressRow(uint32 image_width, const uint8* RAW_row) {
  assert(image_width > 0);
  assert(RAW_row != NULL);

  // How many runs?
  uint32 num_runs = GetNumRunsInRAWRow(image_width, RAW_row);

  // Allocate the RLE row array
  int* RLE_row = malloc((num_runs + 2) * sizeof(int));
  check(RLE_row != NULL, "malloc");

  // Go through the RAW_row
  RLE_row[0] = (int)RAW_row[0];  // Initial pixel value
  PIXMEM += 2; //Acessos ao raw_row[0] e escrita no RLE_row[0]
  uint32 index = 1;
  int num_pixels = 1;
  for (uint32 i = 1; i < image_width; i++) {
    PIXMEM += 2; // Acesso ao raw_row[i] e ao raw_row[i-1]
    if (RAW_row[i] != RAW_row[i - 1]) {
      RLE_row[index++] = num_pixels;
      num_pixels = 0;
      PIXMEM++; // Escrita no RLE_row
    }
    num_pixels++;
  }
  RLE_row[index++] = num_pixels;
  RLE_row[index] = EOR;  // Reached the end of the row
  PIXMEM += 2; // Escrita no RLE_row do num_pixels e do EOR
  return RLE_row;
}

static uint8* UncompressRow(uint32 image_width, const int* RLE_row) {
  assert(image_width > 0);
  assert(RLE_row != NULL);

  // The uncompressed row
  uint8* row = (uint8*)malloc(image_width * sizeof(uint8));
  check(row != NULL, "malloc");

  // Go through the RLE_row until EOR is found
  int pixel_value = RLE_row[0];
  PIXMEM++;

  uint32 i = 1;
  uint32 dest_i = 0;

  while (RLE_row[i] != EOR) {
    PIXMEM++;
    // For each run
    for (int aux = 0; aux < RLE_row[i]; aux++) {
      row[dest_i++] = (uint8)pixel_value;
      PIXMEM++;
    }
    // Next run
    i++;
    pixel_value ^= 1;
  }

  return row;
}

// Add your auxiliary functions here...

// Allocar espaço para um array descomprimido (raw) vazio
static uint8* AllocateRAWRowArray(int n) {
  uint8* newArray = (uint8*) malloc(n * sizeof(uint8));
  check(newArray != NULL, "malloc");

  return newArray;
}

// Calcular memória ocupada pela imagem chessboard
static void CalculateTotalMem(const Image img) {

  MEMORY_USAGE = 0; //Resetar
  if (img == NULL) {
    return;
  }

  // Para cada linha da imagem
  for (uint32 i = 0; i < img->height; i++) {
      // Calcula o tamanho da linha RLE, que a cor do pixel, o número de runs e o EOR através da função dada GetsizeRLE
      uint32 row_size = GetSizeRLERowArray(img->row[i]);
      
      // Soma ao total
      MEMORY_USAGE += row_size * sizeof(int); // Tamanho de cada linha RLE somado ao total
  }
  MEMORY_USAGE += sizeof(int*) * img->height + 2 * sizeof(uint32) + sizeof(int **); // + Tamanho do array de ponteiros + Tamanho da estrutura da imagem  
}

/// Image management functions

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
Image ImageCreateChessboard(uint32 width, uint32 height, uint32 square_edge,
                            uint8 first_value) {
  // Verificar valores dos argumento  s
  if (width <= 0) {
    printf("Invalid chessboard dimensions [width]");
    return NULL;
  }
  if (height <= 0) {
    printf("Invalid chessboard dimensions [height]");
    return NULL;
  }
  if (square_edge <= 0) {
    printf("Invalid square edge");
    return NULL;
  }
  if (first_value != WHITE && first_value != BLACK) {
    printf("Invalid first value");
    return NULL;
  }

  // Verificar se o chessboard tem o Dimensão certa
  if (width % square_edge != 0 || height % square_edge != 0) {
    printf("Invalid chessboard dimensions");
    return NULL;
  }

  // Criar espaço da imagem Dimensão(width x height)
  Image img = AllocateImageHeader(width, height);

  // Runs (quadrados por linha (alternados))
  uint32 runs = width / square_edge;

  // Tamanho da sequência RLE
  uint32 rle_size = 2 + runs; // Tamanho do quadrado

  //Primeira cor da row
  int first_color = first_value;

  // Preencher imagem com padrão chessboard
  for (uint32 row = 0; row < height; row++) {

    // Definir a primeira linha com a sequência de RLE: [cor, tamanho do quadrado, EOR]
    img->row[row] = AllocateRLERowArray(rle_size);  // Array para armazenar a RLE da linha
    img->row[row][0] = first_color;
    img->row[row][rle_size-1] = EOR;

    // Determinar se linha começa com branco(0) ou preto(1)
    first_color = ((row+1) % square_edge) == 0 ? (1-first_color) : first_color; //Se a linha for par recebe como primeiro valor o primeiro valor se for ímpar recebe o valor oposto
    
    // Loop para preencher a linha
    for(uint32 col = 1; col < (rle_size - 1); col++) {
      img->row[row][col] = square_edge;
    }
  }

  // Calcular a memória ocupada em bytes
  CalculateTotalMem(img);

  // Calculo do numer total de pixels e do numero total de quadrados
  unsigned long num_squares = (img->width / square_edge) * (img->height / square_edge);
  unsigned long num_pixels = img->width * img->height;

  // Exibir a memória ocupada em bits e bytes
  printf("Img(%u, %u, %u) Número Runs [%u] | Tamanho Linha RLE [%u] | Total pixels: [%lu] | Total quadrados [%lu] | Memória Ocupada (Bytes): %lu | Memória Ocupada (bits): %lu |\n", 
  img->width, img->height, square_edge, runs, GetSizeRLERowArray(img->row[0]), num_pixels, num_squares, MEMORY_USAGE, MEMORY_USAGE * 8);

  return img;
}

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

/// Printing on the console

/// Output the raw BW image
void ImageRAWPrint(const Image img) {
  assert(img != NULL);

  printf("width = %d height = %d\n", img->width, img->height);
  printf("RAW image:\n");

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

/// Output the compressed RLE image
void ImageRLEPrint(const Image img) {
  assert(img != NULL);

  printf("width = %d height = %d\n", img->width, img->height);
  printf("RLE encoding:\n");

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

/// PBM BW file operations

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

// Auxiliary function
static void packBits(int nbytes, uint8 bytes[], const uint8 raw_row[]) {
  // bitmask starts at top bit
  int offset = 0;
  uint8 mask = 1 << (7 - offset);
  while (offset < 8) {  // or (mask > 0)
    for (int b = 0; b < nbytes; b++) {
      if (offset == 0) bytes[b] = 0;
      bytes[b] |= raw_row[8 * b + offset] ? mask : 0;
    }
    mask >>= 1;
    offset++;
  }
}

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

/// Information queries

/// Get image width
int ImageWidth(const Image img) {
  assert(img != NULL);
  return img->width;
}

/// Get image height
int ImageHeight(const Image img) {
  assert(img != NULL);
  return img->height;
}

/// Image comparison

int ImageIsEqual(const Image img1, const Image img2) {
  assert(img1 != NULL && img2 != NULL);

  // Verificar se as imagens têm a mesma Dimensão
  if (img1->width != img2->width || img1->height != img2->height) {
    return 0; // Diferentes
  }

  // Comparar as linhas das imagens
  for (uint32 i = 0; i < img1->height; i++) {
    // Verificar se as linhas têm o mesmo tamanho
    if (GetNumRunsInRLERow(img1->row[i]) != GetNumRunsInRLERow(img2->row[i])) {
      return 0; //Diferença
    }

    // Comparar os elementos das linhas
    for (uint32 j = 0; img1->row[i][j] != EOR; j++) {
      if (img1->row[i][j] != img2->row[i][j]) {
        return 0; //Diferença
      }
    }
  }

  return 1; // Imagens iguais
}

int ImageIsDifferent(const Image img1, const Image img2) {
  assert(img1 != NULL && img2 != NULL);
  return !ImageIsEqual(img1, img2);
}

/// Boolean Operations on image pixels

/// These functions apply boolean operations to images,
/// returning a new image as a result.
///
/// Operand images are left untouched and must be of the same size.
///
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

/* Image ImageAND(const Image img1, const Image img2) {
  // Medir o tempo antes e depois da operação
  PIXMEM = 0;
  clock_t start = clock();

  assert(img1 != NULL && img2 != NULL);
  // You might consider using the UncompressRow and CompressRow auxiliary files
  // Or devise a more efficient alternative
  // Imagens de Tamanhos diferentes

  if (img1->width != img2->width || img1->height != img2->height) {
    printf("Failed to compute AND operation [Diferent dimensions]");
    return NULL;
  }

  // Nova imagem (img1 AND img2) com o tamanho das imagens originais
  Image result_img = AllocateImageHeader(img1->width, img1->height);

  // Iterar pelas linhas das imagens
  for(uint32 i = 0; i < img1->height; i++) {
    // Obter as linhas das imagens
    uint8* row1 = UncompressRow(img1->width, img1->row[i]);
    uint8* row2 = UncompressRow(img2->width, img2->row[i]);
    PIXMEM += 2; // Para as chamadas de UncompressRow

    // Nova linha
    uint8* new_row = AllocateRAWRowArray(img1->width);
    PIXMEM++; // Para a alocação de memória

    for(uint32 j = 0; j < img1->width; j++) {
      // Aplicar operação AND
      new_row[j] = row1[j] & row2[j];
      PIXMEM++; // Para o operação AND
    }
    
    // Comprimir a linha
    result_img->row[i] = CompressRow(img1->width, new_row);
    PIXMEM++; // Para a chamada de CompressRow

    // Libertar memória
    free(row1);
    free(row2);
    free(new_row);
    PIXMEM+=3; // Para free
  }

  clock_t end = clock();

  // Calcular tempo em milissegundos
  double result_time_ms = (double)(end - start) * 1000.0 / CLOCKS_PER_SEC;

  // Exibir resultados
  printf("Dimension: %ux%u, Time to Execute: %.5f ms | PIXMEM:%lu\n", img1->width, img1->height, result_time_ms, PIXMEM);

  return result_img;
}
 */

Image ImageAND(const Image img1, const Image img2) {
  assert(img1 != NULL && img2 != NULL);
  // Versão otimizada

  PIXMEM = 0; // Para o contador
  clock_t start = clock(); // Marcar hora de inicio de processo
  // Verifica se as imagens são de tamanhos diferentes
  if (img1->width != img2->width || img1->height != img2->height) {
    printf("Failed to compute AND operation [Diferent dimensions]");
    return NULL;
  }
  
  // Nova imagem (img1 AND img2) com o tamanho das imagens originais
  Image result_img = AllocateImageHeader(img1->width, img1->height);

  // Iterar pelas linhas das imagens
  for(uint32 i = 0; i < img1->height; i++) {
    // Guarda o primeiro valor das linhas das imagens (RLE)
    uint32 value1 = img1->row[i][0];
    uint32 value2 = img2->row[i][0];
    uint32 new_init_value = value1 & value2; // linha resultante com o "AND" dos dois primeiros valores
    PIXMEM++; // Para o cálculo do AND

    // Tamanho das linhas comprimidas em RLE (descontando os valores de primeira cor e EOR)
    uint32 length1 = GetSizeRLERowArray(img1->row[i])-2;
    uint32 length2 = GetSizeRLERowArray(img2->row[i])-2;
    PIXMEM += 2; // Para chamadas de GetSizeRLERowArray

    // Calcula o tamanho máximo possível para a nova linha comprimida resultante
    uint32 max_new_length = length1 + length2;
    // Aloca memória para o novo array RLE que armazenará os resultados da operação AND
    int* RLE_array = (int*) malloc(sizeof(int) * (max_new_length + 1));
    assert(RLE_array != NULL);
    PIXMEM++; // Para malloc

    // Primeiro valor do RLE com o valor "AND" dos primeiros elementos das duas linhas
    RLE_array[0] = new_init_value;

    // Índices temporários para iterar pelas duas linhas comprimidas
    uint32 temp_index1 = 1;
    uint32 temp_index2 = 1;
    uint32 new_length = 1;

    // Variáveis temporárias para manter o tamanho das "runs"
    uint32 new_size_run = 0;
    uint32 next_size_run = 0;
    uint32 next_size_run1 = 0;
    uint32 next_size_run2 = 0;

    // Processa as linhas RLE até que todas as runs sejam processadas
    while ((temp_index1 < length1+2) || (temp_index2 < length2+2)) {
      // Operação lógica AND entre os dois valores atuais
      uint32 current_value = value1 & value2;
      PIXMEM++; // Para o cálculo do AND

      // Se já existir uma próxima "run", acumula o tamanho na nova "run"
      if (next_size_run != 0) {
        new_size_run += next_size_run;
        next_size_run = 0;
      } else {
        new_size_run = 0;
      }

      // Obtém o tamanho da próxima "run" da linha
      uint32 size_run1 = next_size_run1 ? (uint32)next_size_run1 : (uint32)img1->row[i][temp_index1];
      uint32 size_run2 = next_size_run2 ? (uint32)next_size_run2 : (uint32)img2->row[i][temp_index2];
      PIXMEM += 2; // Para o cálculo dos tamanhos de run

      next_size_run1 = 0;
      next_size_run2 = 0;

      // Caso os tamanhos das runs sejam iguais, realiza o "AND" de ambos os valores
      if (size_run1 == size_run2) {
        // Inverte os valores
        value1 = value1 == 0 ? 1 : 0;
        value2 = value2 == 0 ? 1 : 0;

        // Se o valor da operação for diferente do valor atual, adiciona ao resultado
        if ((value1 & value2) != current_value) {
          new_size_run += size_run1;
          RLE_array[new_length] = new_size_run; // Guarda nova run
          new_length++; // Aumenta o tamanho da nova run
          new_size_run = 0; // Resetar tamanho da nova run
          PIXMEM++; // Para a escrita do valor
        } else {
          // Caso contrário, acumula o tamanho da próxima run
          next_size_run += size_run1;
        }

        // Avança os índices
        temp_index1++;
        temp_index2++;
      } else if (size_run1 > size_run2) {
        value2 = value2 == 0 ? 1 : 0;
        // Se o tamanho da run da primeira imagem for maior então adiciona o tamanho da run ao resultado
        if ((value1 & value2) != current_value) {
          new_size_run += size_run2;
          RLE_array[new_length] = new_size_run;
          new_length++;
          new_size_run = 0;
          PIXMEM++; // Para a escrita do valor
        } else {
          next_size_run += size_run2;
        }
        // Ajusta o próximo tamanho da run para a imagem 1
        next_size_run1 += size_run1 - size_run2;
        // Avança o índice da segunda linha
        temp_index2++;
      } else {
        // Se o tamanho da run da segunda imagem for maior
        value1 = value1 == 0 ? 1 : 0;

        if ((value1 & value2) != current_value) {
          new_size_run += size_run1;
          RLE_array[new_length] = new_size_run;
          new_length++;
          new_size_run = 0;
          PIXMEM++; // Para a escrita do valor
        } else {
          next_size_run += size_run1;
        }
        // Ajusta o próximo tamanho da run para a imagem 2
        next_size_run2 += size_run2 - size_run1;
        // Avança o índice da primeira linha
        temp_index1++;
      }
    }

    if ((new_size_run != 0) || new_length <= 2) {
      // Caso todos os runs sejam de tamanho iguais
      RLE_array[new_length] = new_size_run;
      new_length++;
      new_size_run = 0;
      PIXMEM++; // Para a escrita do último valor
    }

    // Aloca memória para a nova linha comprimida e copia o conteúdo do RLE resultante
    result_img->row[i] = AllocateRLERowArray(new_length + 1);
    PIXMEM++; // Para AllocateRLERowArray

    for (uint32 j = 0; j < new_length; j++) {
      result_img->row[i][j] = RLE_array[j];
      PIXMEM++; // Para a cópia dos valores de RLE
    }

    // Marca o final da linha comprimida EOR
    result_img->row[i][new_length] = EOR;

    // Liberta a memória alocada para a linha RLE temporária
    free(RLE_array);
    PIXMEM++; // Para free
  }

  clock_t end = clock();

  // Calcular tempo em milissegundos
  double result_time_ms = (double)(end - start) * 1000.0 / CLOCKS_PER_SEC;
  printf("Dimension: %ux%u, Time to Execute: %.5f | PIXMEM: %lu\n",img1->width, img1->height, result_time_ms, PIXMEM);

  return result_img;
}

Image ImageOR(const Image img1, const Image img2) {
  assert(img1 != NULL && img2 != NULL);

  // You might consider using the UncompressRow and CompressRow auxiliary files
  // Or devise a more efficient alternative

  // Imagens de Tamanhos diferentes
  if (img1->width != img2->width || img1->height != img2->height) {
    printf("Failed to compute OR operation [Diferent dimensions]");
    return NULL;
  }


  // Nova imagem (img1 OR img2) com o tamanho das imagens originais
  Image result_img = AllocateImageHeader(img1->width, img1->height);

  // Iterar pelas linhas das imagens
  for(uint32 i = 0; i < img1->height; i++) {
    // Obter as linhas das imagens
    uint8* row1 = UncompressRow(img1->width, img1->row[i]);
    uint8* row2 = UncompressRow(img2->width, img2->row[i]);

    // Nova linha
    uint8* new_row = AllocateRAWRowArray(img1->width);

    for(uint32 j = 0; j < img1->width; j++) {
      // Aplicar operação OR
      new_row[j] = row1[j] | row2[j];
    }

    // Comprimir a linha
    result_img->row[i] = CompressRow(img1->width, new_row);

    // Libertar memória
    free(row1);
    free(row2);
    free(new_row);
  }

  return result_img;
}

Image ImageXOR(Image img1, Image img2) {
  assert(img1 != NULL && img2 != NULL);

  // You might consider using the UncompressRow and CompressRow auxiliary files
  // Or devise a more efficient alternative

  // Imagens de Tamanhos diferentes
  if (img1->width != img2->width || img1->height != img2->height) {
    printf("Failed to compute XOR operation [Diferent dimensions]");
    return NULL;
  }

  // Nova imagem (img1 XOR img2) com o tamanho das imagens originais
  Image result_img = AllocateImageHeader(img1->width, img1->height);

  // Iterar pelas linhas das imagens
  for(uint32 i = 0; i < img1->height; i++) {
    // Obter as linhas das imagens
    uint8* row1 = UncompressRow(img1->width, img1->row[i]);
    uint8* row2 = UncompressRow(img2->width, img2->row[i]);
    
    // Nova linha
    uint8* new_row = AllocateRAWRowArray(img1->width);

    for(uint32 j = 0; j < img1->width; j++) {
      // Aplicar operação XOR
      new_row[j] = row1[j] ^ row2[j];
    }

    // Comprimir a linha
    result_img->row[i] = CompressRow(img1->width, new_row);

    // Libertar memória
    free(row1);
    free(row2);
    free(new_row);
  }

  return result_img;
}

/// Geometric transformations

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

  // Largura e altura da imagem original
  uint32 width = img->width;
  uint32 height = img->height;

  Image newImage = AllocateImageHeader(width, height);

  for (uint32 i = 0; i < height; i++) {
    //Index linha ivertido
    uint32 invertedIndex = height - 1 - i;

    // Descompactar a linha de index invertido da imagem original
    uint8* original_row = UncompressRow(width, img->row[invertedIndex]);
    uint8* mirror_row = AllocateRAWRowArray(width);

    // Copiar os valores diretamente
    for (uint32 j = 0; j < width; j++) {
      mirror_row[j] = original_row[j];
    }

    // Comprimir e salvar a linha invertida
    newImage->row[i] = CompressRow(width, mirror_row);

    // Libertar a memória temporária usada
    free(original_row);
    free(mirror_row);
  }

  return newImage;
}

/// Mirror an image = flip left-right.
/// Returns a mirrored version of the image.
/// Ensures: The original img is not modified.
///
/// On success, a new image is returned.
/// (The caller is responsible for destroying the returned image!)
Image ImageVerticalMirror(const Image img) {
  assert(img != NULL);

  // Largura e altura da imagem original
  uint32 width = img->width;
  uint32 height = img->height;

  Image newImage = AllocateImageHeader(width, height);

  for (uint32 i = 0; i < height; i++) {

    uint8* row = UncompressRow(width, img->row[i]);
    uint8* mirror_row = AllocateRAWRowArray(width);

    // Espelhar a linha
    for (uint32 j = 0; j < width; j++) {
      mirror_row[j] = row[width - 1 - j];
    }

    // Guardar a linha comprimida
    newImage->row[i] = CompressRow(width, mirror_row);

    // Libertar a memória temporária usada
    free(row);
    free(mirror_row);
  }

  return newImage;
}

/// Replicate img2 at the bottom of imag1, creating a larger image
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

  // Copiar img1 para newImage
  for (uint32 i = 0; i < img1->height; i++) {
    // Alocar espaço para linhas das newImage
    uint32 row_size = GetSizeRLERowArray(img1->row[i]); // Tamanho da linha
    newImage->row[i] = AllocateRLERowArray(row_size);

    // Copiar para a linha do newImage o conteúdo da linha do img1
    for (uint32 j = 0; j < row_size; j++) {
      newImage->row[i][j] = img1->row[i][j]; // Colocar na lina de index invertido os valores da linha original
    }
  }

  // Copiar img2 para a parte de baixo de im1 na newImage
  for (uint32 i = 0; i < img2->height; i++) {
    // Alocar espaço para linhas das newImage
    uint32 row_size = GetSizeRLERowArray(img2->row[i]); // Tamanho da linha
    newImage->row[i + img1->height] = AllocateRLERowArray(row_size);

    // Copiar para a linha pretendida do newImage o conteúdo da linha do img2
    for (uint32 j = 0; j < row_size; j++) {
      newImage->row[i + img1->height][j] = img2->row[i][j]; // Colocar na lina de index invertido os valores da linha original
    }
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

  for (uint32 i = 0; i < new_height; i++) {

    uint8* row1 = UncompressRow(img1->width, img1->row[i]);
    uint8* row2 = UncompressRow(img2->width, img2->row[i]);
    uint8* new_row= AllocateRAWRowArray(new_width);

    // Atribuir os valores da linha 1 à nova linha concatenada
    for (uint32 j = 0; j < img1->width; j++) {
      new_row[j] = row1[j];
    }

    // Atribuir os valores da linha 1 à nova linha concatenada
    for (uint32 j = 0; j < img2->width; j++) {
      new_row[j+img1->width] = row2[j];
    }

    // Guardar a linha comprimida
    newImage->row[i] = CompressRow(new_width, new_row);

    // Libertar a memória temporária usada
    free(row1);
    free(row2);
    free(new_row);
  }

  return newImage;
}
