# Compare two image files
# Show number of pixels that are equal, differ by ±1 or differ by more.
#
# JMR 2024

# Uses the Pillow library to read images.
# To install (in Ubuntu):  sudo apt install python3-pil
# To install (using pip), see:
#    https://pillow.readthedocs.io/en/stable/installation.html#

import sys
from PIL import Image
from collections import Counter

def main(args):
    if len(args) != 3:
        print(f"python3 {args[0]} img1.pgm img2.pgm")
        exit(1)

    difCounter = Counter({0: 0, 1: 0})
    difName = {0: "IGUAIS", 1: "DIFFERENTES"}

    with Image.open(args[1]) as img1, Image.open(args[2]) as img2:
        if img1.size != img2.size:
            print("DIFFERENT SIZES:", img1.size, img2.size)
            return 1
        difCounter.update(min(abs(a-b), 1) for a, b in
                          zip(img1.getdata(), img2.getdata()))

    w, h = img1.size
    tot = w * h
    
    assert difCounter.total() == tot

    for dif in sorted(difCounter.keys()):
        c = difCounter[dif]
        print(f"{c:8} pixels\t{c/tot:4.0%}\t{difName[dif]:<12}")

    return difCounter[0] != tot


if __name__ == "__main__":
    exit(main(sys.argv))

