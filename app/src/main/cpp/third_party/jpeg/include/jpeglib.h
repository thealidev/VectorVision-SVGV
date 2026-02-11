/*
 * This is a corrected, more complete, yet still simplified, version of the jpeglib.h header file.
 * It contains the necessary struct definitions for native-lib.cpp to compile.
 * The declaration order has been fixed.
 */

#ifndef JPEGLIB_H
#define JPEGLIB_H

#include <stdio.h>
#include <setjmp.h>

// Basic data types
typedef unsigned char JOCTET;
typedef unsigned int JDIMENSION;
typedef int boolean;

#ifndef TRUE
#define TRUE 1
#endif
#ifndef FALSE
#define FALSE 0
#endif

// --- Forward Declarations and Typedefs ---

// Forward declare the common struct
struct jpeg_common_struct;
// Define the pointer type that is used in the error manager
typedef struct jpeg_common_struct * j_common_ptr;

// Forward declare the decompress struct
struct jpeg_decompress_struct;
// Define the pointer type for the decompress struct
typedef struct jpeg_decompress_struct * j_decompress_ptr;


// --- Struct Definitions ---

// This is a minimal definition of the error manager struct.
// It can now use j_common_ptr because it has been defined.
struct jpeg_error_mgr {
  void (*error_exit) (j_common_ptr cinfo);
  void (*output_message) (j_common_ptr cinfo);
  jmp_buf setjmp_buffer; 
};

// This is the common struct.
struct jpeg_common_struct {
  struct jpeg_error_mgr * err;
};

// This is a more complete definition of the decompression struct.
struct jpeg_decompress_struct {
  struct jpeg_error_mgr * err;
  JDIMENSION image_width;
  JDIMENSION image_height;
  int output_components;
  JDIMENSION output_width;
  JDIMENSION output_height;
  JDIMENSION output_scanline;
  boolean raw_data_out; /* TRUE=downsampled data returned */
};


// --- Public API Functions ---

#ifdef __cplusplus
extern "C" {
#endif

struct jpeg_error_mgr * jpeg_std_error(struct jpeg_error_mgr * err);
void jpeg_create_decompress(j_decompress_ptr cinfo);
void jpeg_mem_src(j_decompress_ptr cinfo, const unsigned char * inbuffer, unsigned long insize);
int jpeg_read_header(j_decompress_ptr cinfo, boolean require_image);
boolean jpeg_start_decompress(j_decompress_ptr cinfo);
JDIMENSION jpeg_read_scanlines(j_decompress_ptr cinfo, JOCTET ** scanlines, JDIMENSION max_lines);
boolean jpeg_finish_decompress(j_decompress_ptr cinfo);
void jpeg_destroy_decompress(j_decompress_ptr cinfo);

#ifdef __cplusplus
}
#endif

#endif // JPEGLIB_H
