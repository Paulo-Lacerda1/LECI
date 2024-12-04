# make              # to compile files and create the executables
# make clean        # to cleanup object files and executables
# make cleanobj     # to cleanup object files only

CFLAGS = -Wall -Wextra -O2 -g

PROGS = imageBWTest

# Default rule: make all programs
all: $(PROGS)

imageBWTest: imageBWTest.o imageBW.o instrumentation.o

imageBWTest.o: imageBW.h instrumentation.h

# Rule to make any .o file dependent upon corresponding .h file
%.o: %.h

# Make uses builtin rule to create .o from .c files.

cleanobj:
	rm -f *.o

clean: cleanobj
	rm -f $(PROGS)
