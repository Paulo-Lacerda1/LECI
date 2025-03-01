// imageBWTest - A program that performs some image processing.
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

int main(int argc, char* argv[]) {
  if (argc != 1) {
    fprintf(stderr, "Usage: %s  # no arguments required (for now)\n", argv[0]);
    exit(1);
  }

  // To initalize operation counters
  ImageInit();

  // Creating and displaying some images
  printf("Teste Imagem Branca 8x8:\n");
  Image white_image = ImageCreate(8, 8, WHITE);
  ImageRAWPrint(white_image);                       //PASS

  printf("Teste Imagem Preta 8x8:\n");
  Image black_image = ImageCreate(8, 8, BLACK);
  ImageRAWPrint(black_image);                       //PASS

  printf("image 1 = Branca NEG:\n");
  Image image_1 = ImageNEG(white_image);
  ImageRAWPrint(image_1);                            //PASS


//UNCOMMENT TO TEST THE NEXT FUNCTIONS
  printf("image 2 = Branca REPB Preta\n");
  Image image_2 = ImageReplicateAtBottom(white_image, black_image);
  ImageRAWPrint(image_2);                           //PASS

  printf("image 3 = image_1 AND image_1 <=> (Branca NEG) AND (Branca NEG)\n");
  Image image_3 = ImageAND(image_1, image_1);
  ImageRAWPrint(image_3);                           //PASS

  printf("image 4 = image_1 AND image_2 <=> (Branca NEG) AND (Branca Preta REPB)\n");
  Image image_4 = ImageAND(image_1, image_2);
  ImageRAWPrint(image_4);                           //PASS

  printf("image 5 = image_1 OR image_2 <=> (Branca NEG) OR (Branca Preta REPB)\n");
  Image image_5 = ImageOR(image_1, image_2);
  ImageRAWPrint(image_5);                           //PASS

  printf("image 6 = image_1 XOR image_1\n");
  Image image_6 = ImageXOR(image_1, image_1);
  ImageRAWPrint(image_6);                           //PASS

  printf("image 7 = image_1 XOR image_2\n");
  Image image_7 = ImageXOR(image_1, image_2);
  ImageRAWPrint(image_7);                           //PASS

  printf("image 8 = image_6 REPR image_7\n");
  Image image_8 = ImageReplicateAtRight(image_6, image_7);
  ImageRAWPrint(image_8);                           //PASS

  printf("image 9 = image_6 REPR image_6\n");
  Image image_9 = ImageReplicateAtRight(image_6, image_6);
  ImageRAWPrint(image_9);                           //PASS

  printf("image_10 = HMIRROR image 1\n");
  Image image_10 = ImageHorizontalMirror(image_1);
  ImageRAWPrint(image_10);                           //PASS

  printf("image_11 = VMIRROR image 8\n");
  Image image_11 = ImageVerticalMirror(image_8);
  ImageRAWPrint(image_11);                           //PASS

  // Saving in PBM format
  ImageSave(image_7, "image7.pbm");
  ImageSave(image_8, "image8.pbm");

  // Housekeeping
  ImageDestroy(&white_image);
  ImageDestroy(&black_image);
  ImageDestroy(&image_1);


// UNCOMMENT IF YOU CREATE THOSE IMAGES

  ImageDestroy(&image_2);
  ImageDestroy(&image_3);
  ImageDestroy(&image_4);
  ImageDestroy(&image_5);
  ImageDestroy(&image_6);
  ImageDestroy(&image_7);
  ImageDestroy(&image_8);
  ImageDestroy(&image_9);
  ImageDestroy(&image_10);
  ImageDestroy(&image_11);

  return 0;
}
