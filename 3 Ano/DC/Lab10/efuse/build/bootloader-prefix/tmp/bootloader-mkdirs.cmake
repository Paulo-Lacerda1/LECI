# Distributed under the OSI-approved BSD 3-Clause License.  See accompanying
# file Copyright.txt or https://cmake.org/licensing for details.

cmake_minimum_required(VERSION 3.5)

file(MAKE_DIRECTORY
  "/home/paulo/esp/v5.5.1/esp-idf/components/bootloader/subproject"
  "/home/paulo/Desktop/DC/Lab10/efuse/build/bootloader"
  "/home/paulo/Desktop/DC/Lab10/efuse/build/bootloader-prefix"
  "/home/paulo/Desktop/DC/Lab10/efuse/build/bootloader-prefix/tmp"
  "/home/paulo/Desktop/DC/Lab10/efuse/build/bootloader-prefix/src/bootloader-stamp"
  "/home/paulo/Desktop/DC/Lab10/efuse/build/bootloader-prefix/src"
  "/home/paulo/Desktop/DC/Lab10/efuse/build/bootloader-prefix/src/bootloader-stamp"
)

set(configSubDirs )
foreach(subDir IN LISTS configSubDirs)
    file(MAKE_DIRECTORY "/home/paulo/Desktop/DC/Lab10/efuse/build/bootloader-prefix/src/bootloader-stamp/${subDir}")
endforeach()
if(cfgdir)
  file(MAKE_DIRECTORY "/home/paulo/Desktop/DC/Lab10/efuse/build/bootloader-prefix/src/bootloader-stamp${cfgdir}") # cfgdir has leading slash
endif()
