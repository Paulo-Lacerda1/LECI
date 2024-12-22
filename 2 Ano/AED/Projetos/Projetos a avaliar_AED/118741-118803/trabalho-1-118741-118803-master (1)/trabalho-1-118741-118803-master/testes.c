#include <assert.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "imageBW.h"
#include "instrumentation.h"

int main(void) {
    ImageInit();

    Image catdog = ImageLoad("catdog.pbm");
    Image dog = ImageLoad("images.pbm");
    
    // Testes para o chessboard
    /* Image img1 = ImageCreateChessboard(1, 1, 1, BLACK); // 1x1, 1 square size
    Image img2 = ImageCreateChessboard(2, 2, 1, WHITE); // 2x2, 1 square size
    Image img3 = ImageCreateChessboard(5, 15, 1, BLACK); // 5x15, 1 square size
    Image img4 = ImageCreateChessboard(25, 30, 5, WHITE); // 25x30, 5 square size
    Image img5 = ImageCreateChessboard(50, 50, 5, BLACK); // 50x50, 5 square size
    Image img6 = ImageCreateChessboard(75, 90, 5, WHITE); // 75x90, 5 square size
    Image img7 = ImageCreateChessboard(100, 350, 10, BLACK); // 100x350, 10 square size
    Image img8 = ImageCreateChessboard(100, 350, 1, WHITE); // 100x350, 1 square size
    Image img9 = ImageCreateChessboard(200, 400, 20, BLACK); // 200x400, 20 square size
    Image img10 = ImageCreateChessboard(800, 800, 20, WHITE); // 800x800, 20 square size
    Image img11 = ImageCreateChessboard(1000, 1000, 1, BLACK); // 1000x1000, 1 square size
    Image img12 = ImageCreateChessboard(1000, 1000, 100, WHITE); // 1000x1000, 100 square size */

    // Para o AND e restantes testes
    Image img1 = ImageCreateChessboard(300, 150, 1, BLACK);
    Image img2 = ImageCreateChessboard(300, 150, 5, WHITE);
    Image img3 = ImageCreateChessboard(300, 150, 10, BLACK);
    Image img4 = ImageCreateChessboard(2500, 3500, 5, WHITE);
    Image img5 = ImageCreateChessboard(2500, 3500, 10, WHITE);
    Image img6 = ImageCreateChessboard(10000, 5000, 1, WHITE);
    Image img7 = ImageCreateChessboard(10000, 5000, 100, BLACK);

    Image imgx = ImageCreateChessboard(80, 100, 5, WHITE);
    Image imgy = ImageCreateChessboard(80, 100, 10, BLACK);
    Image imgrepR = ImageReplicateAtRight(imgx, imgy);
    printf("Replicate at right\n");
    ImageRAWPrint(imgrepR);
    ImageDestroy(&imgrepR);
    Image imgrepB = ImageReplicateAtBottom(imgx, imgy);
    printf("Replicate at bottom\n");
    ImageRAWPrint(imgrepB);
    ImageDestroy(&imgrepB);
    printf("Horizontal mirror\n");
    Image imgMRH = ImageHorizontalMirror(imgx);
    printf("---------------------------\n");
    ImageRAWPrint(imgMRH);
    ImageDestroy(&imgMRH);
    printf("Vertical mirror\n");
    Image imgMRV = ImageVerticalMirror(imgy);
    printf("---------------------------\n");
    ImageRAWPrint(imgMRV);
    ImageDestroy(&imgMRV);

    int equal = ImageIsEqual(imgx, imgy);
    if (equal) {
        printf("Images are equal!\n");
    } else {
        printf("Images are NOT equal!\n");
    }

    int equal1 = ImageIsEqual(imgx, imgx);
    if (equal1) {
        printf("Images are equal!\n");
    } else {
        printf("Images are NOT equal!\n");
    }

    ImageRLEPrint(dog);
    ImageRLEPrint(catdog);

    Image imgANDxy = ImageAND(dog, catdog);
    ImageRLEPrint(imgANDxy);
    ImageDestroy(&imgANDxy);
    ImageDestroy(&dog);
    ImageDestroy(&catdog);

    printf("-------------------------------------------------------------------- Testes AND ------------------------------------------------------------------\n");
    // Perform AND, OR, XOR operations on the images
    Image imgAND1 = ImageAND(img1, img1);
    ImageDestroy(&imgAND1);
    Image imgAND2 = ImageOR(img2, img3);
    ImageDestroy(&imgAND2);

    Image imgAND3 = ImageXOR(img4, img4);
    ImageDestroy(&imgAND3);
    Image imgAND4 = ImageAND(img5, img5);
    ImageDestroy(&imgAND4);

    Image imgAND5 = ImageOR(img6, img6);
    ImageDestroy(&imgAND5);
    Image imgAND6 = ImageAND(img7, img7);
    ImageDestroy(&imgAND6);

    
    // Libertar memória das imagens de teste
    ImageDestroy(&img1);
    ImageDestroy(&img2);
    ImageDestroy(&img3);
    ImageDestroy(&img4);
    ImageDestroy(&img5);
    ImageDestroy(&img6);
    ImageDestroy(&img7);
/*     ImageDestroy(&img8);
    ImageDestroy(&img9);
    ImageDestroy(&img10);
    ImageDestroy(&img11);
    ImageDestroy(&img12); */
    ImageDestroy(&imgx);
    ImageDestroy(&imgy);

    return 0;
}
