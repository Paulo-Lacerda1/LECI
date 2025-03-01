// imageTool - A simple and versatile image processor.
//
// This program is an example use of the imageBW module,
// a programming project for the course AED, DETI / UA.PT
//
// You may freely use and modify this code, NO WARRANTY, blah blah,
// as long as you give proper credit to the original and subsequent authors.
//
// The AED Team <jmadeira@ua.pt, jmr@ua.pt, ...>
// 2024

#include <assert.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "imageBW.h"
#include "instrumentation.h"

static const char* USAGE =
    "USAGE: imageTool [FILE...] [OPERATION [OPERAND]]...\n"
    "  Apply pipeline of image processing operations to PBM files.\n"
    "  Arguments are processed from left to right and may be\n"
    "  FILES, OPERATIONS, or OPERANDS to operations.\n"
    "  Some operations create images, which are appended to an internal buffer:\n"
    "      I0, I1, ..., PRED, CURR\n"
    "  The last image in the buffer is called the current image CURR and its\n"
    "  predecessor is PRED.\n"
    "  Most operations apply to CURR and some also use PRED.\n"
    "\n"
    "FILES:\n"
    "  Currently, only image files in binary PBM format are accepted.\n"
    "  Input file names must be distinct from operation names.\n"
    "\n"
    "OPERATIONS:\n"
    "  FILE            Load image from PBM file named FILE.\n"
    "  save FILE       Save CURR to PBM file named FILE.\n"
    "  info            Show information on CURR (size).\n"
    "  tic             Reset instrumentation counters and times.\n"
    "  toc             Print instrumentation counters and times.\n"
    "\n"              
    "  create W,H,C    Create new image with WxH pixels, color C.\n"
    "  chess W,H,E,C   Create new chessboard image with WxH pixels,"
    "                  squares with edge E, first color C.\n"
    "\n"              
    "  raw             Print RAW representation of CURR.\n"
    "  rle             Print RLE representation of CURR.\n"
    "\n"              
    "  equal           PREV == CURR?\n"
    "\n"              
    "  neg             Neg CURR.\n"
    "  and             PREV and CURR.\n"
    "  or              PREV or CURR.\n"
    "  xor             PREV xor CURR.\n"
    "\n"              
    "  hmirror         Horizontal mirror CURR (flip top-bottom).\n"
    "  vmirror         Vertical mirror CURR (flip left-right).\n"
    "  repb            Replicate CURR at the bottom of PREV.\n"
    "  repr            Replicate CURR at the right of PREV.\n"
    "\n"              
    "OPERANDS:\n"
    "  FILE            A filename\n"
    "  W,H             Width and height of image or rectangular region.\n"
    "  C               Color (0 = WHITE, 1 = BLACK).\n"
    "  E               Edge length.\n"
    "\n"
    ;

static char* errors[] = {
  "Success",
  "Insufficient operands",
  "Insufficient images",
  "Insufficient space in buffer",
  "Invalid operand",
};


// This program strives for correctness and robustness.
// You may want to temporarily comment out operand validation, namely
// precondition checks, so that you can force precondition violations,
// and observe the effect of assertions.
//
// Also, the program does not test every module function, but you may easily
// add new operations for that purpose.

int main(int ac, char* av[]) {
  if (ac <= 1) {
    fprintf(stderr, "\n%s", USAGE);
    return 1;
  }
  
  FILE *log = stdout;   // where to send log messages

  ImageInit();

  int err = 0;
  uint32 w, h;

  // The image buffer
  const int N = 10;   // buffer capacity
  Image img[N];       // the images
  int n = 0;          // number of images created

  int k = 1;
  while (k < ac) {
    if (strcmp(av[k], "info") == 0) {
      if (n < 1) { err = 2; break; }  // enough input images?
      fprintf(log, "Info on I%d\n", n-1);
      w = ImageWidth(img[n-1]);
      h = ImageHeight(img[n-1]);
      fprintf(log, "# Size: %ux%u\n", w, h);
    } else if (strcmp(av[k], "tic") == 0) {
      InstrReset();
    } else if (strcmp(av[k], "toc") == 0) {
      InstrPrint();
    } else if (strcmp(av[k], "create") == 0) {
      if (++k >= ac) { err = 1; break; }  // enough arguments?
      if (n >= N) { err = 3; break; } // enough space for output?
      uint32 c;  // color
      if (sscanf(av[k], "%u,%u,%u", &w, &h, &c) != 3) { err = 4; break; }
      if (c > 1) { err = 4; break; }   // precondition check!
      fprintf(log, "ImageCreate(%u, %u, %u) -> I%d\n", w, h, c, n);
      img[n] = ImageCreate(w, h, (uint8)c);
      //x if (img[n] == NULL) { err = 999; break; }
      n++;
    } else if (strcmp(av[k], "chess") == 0) {
      if (++k >= ac) { err = 1; break; }  // enough arguments?
      if (n >= N) { err = 3; break; } // enough space for output?
      uint32 edge;  // square edge length
      uint32 c;  // color
      if (sscanf(av[k], "%u,%u,%u,%u", &w, &h, &edge, &c) != 4) { err = 4; break; }
      if (c > 1) { err = 4; break; }   // precondition check!
      fprintf(log, "ImageCreateChessBoard(%u, %u, %u, %u) -> I%d\n", w, h, edge, c, n);
      img[n] = ImageCreateChessboard(w, h, edge, (uint8)c);;
      n++;
    } else if (strcmp(av[k], "raw") == 0) {
      if (n < 1) { err = 2; break; }  // enough input images?
      fprintf(log, "ImageRAWPrint(I%d)\n", n-1);
      ImageRAWPrint(img[n-1]);
    } else if (strcmp(av[k], "rle") == 0) {
      if (n < 1) { err = 2; break; }  // enough input images?
      fprintf(log, "ImageRLEPrint(I%d)\n", n-1);
      ImageRLEPrint(img[n-1]);
    } else if (strcmp(av[k], "equal") == 0) {
      if (n < 2) { err = 2; break; }  // enough input images?
      fprintf(log, "ImageIsEqual(I%d, I%d) -> ", n-2, n-1);
      int eq = ImageIsEqual(img[n-2], img[n-1]);
      fprintf(log, "%d\n", eq);
    } else if (strcmp(av[k], "neg") == 0) {
      if (n < 1) { err = 2; break; }  // enough input images?
      if (n >= N) { err = 3; break; } // enough space for output?
      fprintf(log, "ImageNEG(I%d) -> I%d\n", n-1, n);
      img[n] = ImageNEG(img[n-1]);
      n++;
    } else if (strcmp(av[k], "and") == 0) {
      if (n < 2) { err = 2; break; }  // enough input images?
      if (n >= N) { err = 3; break; } // enough space for output?
      fprintf(log, "ImageAND(I%d, I%d) -> I%d\n", n-2, n-1, n);
      img[n] = ImageAND(img[n-2], img[n-1]);
      n++;
    } else if (strcmp(av[k], "or") == 0) {
      if (n < 2) { err = 2; break; }  // enough input images?
      if (n >= N) { err = 3; break; } // enough space for output?
      fprintf(log, "ImageOR(I%d, I%d) -> I%d\n", n-2, n-1, n);
      img[n] = ImageOR(img[n-2], img[n-1]);
      n++;
    } else if (strcmp(av[k], "xor") == 0) {
      if (n < 2) { err = 2; break; }  // enough input images?
      if (n >= N) { err = 3; break; } // enough space for output?
      fprintf(log, "ImageXOR(I%d, I%d) -> I%d\n", n-2, n-1, n);
      img[n] = ImageXOR(img[n-2], img[n-1]);
      n++;
    } else if (strcmp(av[k], "hmirror") == 0) {
      if (n < 1) { err = 2; break; }  // enough input images?
      if (n >= N) { err = 3; break; } // enough space for output?
      fprintf(log, "ImageHorizontalMirror(I%d) -> I%d\n", n-1, n);
      img[n] = ImageHorizontalMirror(img[n-1]);
      n++;
    } else if (strcmp(av[k], "vmirror") == 0) {
      if (n < 1) { err = 2; break; }  // enough input images?
      if (n >= N) { err = 3; break; } // enough space for output?
      fprintf(log, "ImageVerticalMirror(I%d) -> I%d\n", n-1, n);
      img[n] = ImageVerticalMirror(img[n-1]);
      n++;
    } else if (strcmp(av[k], "repb") == 0) {
      if (n < 2) { err = 2; break; }  // enough input images?
      if (n >= N) { err = 3; break; } // enough space for output?
      fprintf(log, "ImageReplicateAtBottom(I%d, I%d) -> I%d\n", n-2, n-1, n);
      img[n] = ImageReplicateAtBottom(img[n-2], img[n-1]);
      n++;
    } else if (strcmp(av[k], "repr") == 0) {
      if (n < 2) { err = 2; break; }  // enough input images?
      if (n >= N) { err = 3; break; } // enough space for output?
      fprintf(log, "ImageReplicateAtRight(I%d, I%d) -> I%d\n", n-2, n-1, n);
      img[n] = ImageReplicateAtRight(img[n-2], img[n-1]);
      n++;
    } else if (strcmp(av[k], "save") == 0) {
      if (++k >= ac) { err = 1; break; }
      if (n < 1) { err = 2; break; }  // enough input images?
      fprintf(log, "ImageSave(I%d, \"%s\")\n", n-1, av[k]);
      ImageSave(img[n-1], av[k]);
    } else {  // image file
      if (n >= N) { err = 3; break; }
      fprintf(log, "ImageLoad(\"%s\") -> I%d\n", av[k], n);
      img[n] = ImageLoad(av[k]);
      //x if (img[n] == NULL) { err = 999; break; }
      n++;
    }
    k++;
  }
  
  // Destroy remaining images
  while (n > 0) {
    fprintf(log, "ImageDestroy(I%d)\n", n-1);
    ImageDestroy(&img[--n]);
  }

  if (err > 0) {
    fprintf(stderr, "%s\n", errors[err]);
    exit(100 + err);
  }
  return 0;
}

