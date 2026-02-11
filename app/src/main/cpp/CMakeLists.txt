
# Sets the minimum version of CMake required to build the native library.
cmake_minimum_required(VERSION 3.22.1)

project("vectorvision")

# --- Define the path to the third-party library ---
# This points to the location where we assume libjpeg-turbo is located.
set(JPEG_LIBRARY_PATH ${CMAKE_SOURCE_DIR}/third_party/jpeg)


# --- Add the main native library ---
add_library(
    vectorvision-core
    SHARED
    native-lib.cpp
    svgc_protocol.cpp
    svgv_engine.cpp
)

add_library(
    vectorvision-native
    SHARED
    svgc-decoder.cpp
)


# --- Add the prebuilt libjpeg-turbo library ---
# This tells CMake about the pre-compiled .so or .a file.
add_library(jpeg_lib SHARED IMPORTED)
set_target_properties(jpeg_lib PROPERTIES IMPORTED_LOCATION
    ${JPEG_LIBRARY_PATH}/lib/${ANDROID_ABI}/libjpeg.so
)


# --- Find system libraries ---
find_library(log-lib log)
find_library(z-lib z)


# --- Link Libraries ---
# Link vectorvision against the Android logging library and our jpeg library.
target_link_libraries(
    vectorvision-core
    ${log-lib}
    jpeg_lib
)

target_link_libraries(
    vectorvision-native
    ${log-lib}
    ${z-lib}
)


# --- Include Directories ---
# This tells the compiler where to find the jpeglib.h header file.
target_include_directories(vectorvision-core PRIVATE ${JPEG_LIBRARY_PATH}/include)

