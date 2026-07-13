# somm25nm
  Implementing a multi-processor scheduler of a simulated multiprogrammed batch system, 
  based on an unequal-size fixed partitioning memory allocation approach

******
******

## Prerequisites

On Ubuntu you need the following packages installed: 
`build-essential`, `cmake`, `doxygen`.

```
sudo apt install build-essential cmake doxygen
```
In other Linux distributions you need equivalent packages installed.

******

## Preparing the compilation environment

In the code provided, the main program is considered a softlink to the test program you actually want to run.
A test program for the JOB module is provided and it will be used to illustrate the approach.

In a terminal,

```
cd «your-repo-directory»
cd somm25nm
cd src
ln -sf job_test.cpp main.cpp
```

The previous step is mandatory considering the main CMakeLists.txt provided, the one in the `src` directory.

Now we are in position to build the code.
In a terminal, enter the base directory of your project, create the `build` directory,
and use `cmake` to prepare `make` or `ninja`

```
cd «your-repo-directory»
cd somm25nm
mkdir build
cd build
cmake ../src
```

If you prefer `ninja`, instead of `make`,

```
cd «your-repo-directory»
cd somm25nm-grp
mkdir build
cd build
cmake -G Ninja ../src
```

******

## Compiling the code

In a terminal, enter the `build` directory of your project and run `make` or `ninja`

```
cd «your-repo-directory»
cd somm25nm/build
make
```
or, if you are using `ninja`

```
cd «your-repo-directory»
cd somm25nm/build
ninja
```
******

## Generating documentation

The code is documented in `doxygen`. So, you can easily generate **html** documentation pages.

```
cd «your-repo-directory»
cd somm25nm/doc
doxygen
```
Then, you can display the pages running (inside the `doc` directory)

```
firefox html/index.html &
```

Of course, you can replace `firefox` with your favourite browser.

******

## Testing the code

After building the code, a program will be put in the `somm25nm-grp/bin` directory.
It accepts the `-h` option to display a help menu.
To run the provided test for the job module

```
cd «your-repo-directory»
cd somm25nm/bin
./main -b 
```

Of course you can/must write your own testing programs. 
If you want to use the approach referred to in this script, 
do not forget to create your test source code file and change the softlink of `main.cpp`

```
cd «your-repo-directory»
cd somm25nm/src
ln -sf «your-test-program».cpp main.cpp
```


