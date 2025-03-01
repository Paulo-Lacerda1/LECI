
#include <math.h>
#include <assert.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "imageBW.h"
#include "instrumentation.h"


int main(void){

    uint32 min_height = 1;
    uint32 max_height = 4096;
    uint32 width;
    uint32 square_edge;
    uint8 fst_pixel = 1;

    for(uint32 height = min_height; height<=max_height; height*=2){
        width = height;
        for(square_edge=1; square_edge<=width; square_edge*=2){
            if((width%square_edge!=0)||(height%square_edge!=0)){
                continue;
            }
            else{
                Image newImage1 = ImageCreateChessboard(width, height, square_edge, fst_pixel);
                Image newImage2 = ImageCreateChessboard(width, height, square_edge, fst_pixel);
                ImageInit();
                Image newAnd = ImageAND(newImage1, newImage2);
                ImageDestroy(&newImage1);
                ImageDestroy(&newImage2);
                ImageDestroy(&newAnd);
            }
        }

    }

/*
    for(uint32 height = min_height; height<=max_height; height*=2){
        for(uint32 width = min_width; width<=height; width=width+2){
            for(square_edge=1; square_edge<=width; square_edge++){
                if((width%square_edge!=0)||(height%square_edge!=0)){
                    continue;
                }
                else{
                    ImageCreateChessboard(width, height, square_edge, fst_pixel);
                }
            }
        }
    }
*/

    return 0;
}

