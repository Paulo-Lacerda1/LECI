# make              # to compile files and create the executables
# make clean        # to cleanup object files and executables
# make cleanobj     # to cleanup object files only
# make pbm          # to download example images to the pbm/ dir
# make setup        # to setup the test files in pbmt/ dir
# make tests        # to run basic tests

CFLAGS = -Wall -Wextra -O2 -g

PROGS = imageBWTest imageBWTool

# Default rule: make all programs
all: $(PROGS)

imageBWTest: imageBWTest.o imageBW.o instrumentation.o

imageBWTest.o: imageBW.h instrumentation.h

imageBWTool: imageBWTool.o imageBW.o instrumentation.o

imageBWTool.o: imageBW.h instrumentation.h

# Rule to make any .o file dependent upon corresponding .h file
%.o: %.h

# Make uses builtin rule to create .o from .c files.

pbm:
	wget -O- https://sweet.ua.pt/jmr/aed/pbm.tgz | tar xzf -

# 2024-11-26: pbmt/* is now managed by git,
# so pbmt/ target is no longer required, but was kept anyway.
pbmt/:
	wget -O- https://sweet.ua.pt/jmr/aed/pbmt.tgz | tar xzf -

.PHONY: setup
setup: $(PROGS) pbmt/

test1: setup	# neg (given)
	@echo "==== $@ ===="
	INSTRCTU=1 ./imageBWTool pbmt/chess9821.pbm raw neg raw save chess9820.pbm
	cmp chess9820.pbm pbmt/chess9820.pbm

test2: setup	# chess
	@echo "==== $@ ===="
	INSTRCTU=1 ./imageBWTool chess 12,6,3,0 raw rle save chess12630.pbm
	cmp chess12630.pbm pbmt/chess12630.pbm
	INSTRCTU=1 ./imageBWTool chess 12,6,2,1 raw rle save chess12621.pbm
	cmp chess12621.pbm pbmt/chess12621.pbm

test3: setup	# equal
	@echo "==== $@ ===="
	INSTRCTU=1 ./imageBWTool pbmt/chess9830.pbm pbmt/chess9830.pbm equal \
	| grep "ImageIsEqual(I0, I1) -> 1"
	INSTRCTU=1 ./imageBWTool pbmt/chess9830.pbm pbmt/chess9830x.pbm equal \
	| grep "ImageIsEqual(I0, I1) -> 0"

test4: setup    # AND
	@echo "==== $@ ===="
	INSTRCTU=1 ./imageBWTool pbmt/chess12630.pbm pbmt/chess12621.pbm and \
	raw save imgAND.pbm
	cmp imgAND.pbm pbmt/imgAND.pbm

test5: setup    # OR
	@echo "==== $@ ===="
	INSTRCTU=1 ./imageBWTool pbmt/chess12630.pbm pbmt/chess12621.pbm or \
	raw save imgOR.pbm
	cmp imgOR.pbm pbmt/imgOR.pbm

test6: setup    # XOR
	@echo "==== $@ ===="
	INSTRCTU=1 ./imageBWTool pbmt/chess12630.pbm pbmt/chess12621.pbm xor \
	raw save imgXOR.pbm
	cmp imgXOR.pbm pbmt/imgXOR.pbm

test7: setup    # hmirror
	@echo "==== $@ ===="
	INSTRCTU=1 ./imageBWTool pbmt/imgAND.pbm raw hmirror raw \
    save imgHMIRROR.pbm
	cmp imgHMIRROR.pbm pbmt/imgHMIRROR.pbm

test8: setup    # vmirror
	@echo "==== $@ ===="
	INSTRCTU=1 ./imageBWTool pbmt/imgAND.pbm raw vmirror raw \
    save imgVMIRROR.pbm
	cmp imgVMIRROR.pbm pbmt/imgVMIRROR.pbm

test9: setup    # repb
	@echo "==== $@ ===="
	INSTRCTU=1 ./imageBWTool pbmt/chess12630.pbm pbmt/chess12320.pbm repb \
	raw save imgREPB.pbm
	cmp imgREPB.pbm pbmt/imgREPB.pbm

test10: setup    # repr
	@echo "==== $@ ===="
	INSTRCTU=1 ./imageBWTool pbmt/chess12621.pbm pbmt/chess5631.pbm repr \
	raw save imgREPR.pbm
	cmp imgREPR.pbm pbmt/imgREPR.pbm

TESTS = test1 test2 test3 test4 test5 test6 test7 test8 test9 test10
.PHONY: tests
tests: $(TESTS)

cleanobj:
	rm -f *.o

clean: cleanobj
	rm -f $(PROGS)

