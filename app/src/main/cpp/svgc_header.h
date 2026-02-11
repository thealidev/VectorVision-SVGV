
#ifndef VECTORVISION_SVGC_HEADER_H
#define VECTORVISION_SVGC_HEADER_H

#include <vector>
#include <string>

// --- SVGC Protocol Definition ---
// This represents the structure of the geometric data after conversion.
struct SVGC_Packet {
    std::vector<char> header;
    std::vector<std::string> paths;
};


// --- Core Engine Function ---
// This is the main function that will be implemented in svgv_engine.cpp
// It takes image data and is supposed to return a vector of geometric paths.
// NOTE: It currently expects raw pixel data, not a compressed JPEG.
SVGC_Packet extractGeometricPaths(const unsigned char* imageData, int length);


// --- Encoding Function ---
// This will be implemented in svgc_protocol.cpp
// It takes the geometric paths and encodes them into the final .svgc byte format.
std::vector<char> encodeToSvgcFormat(const SVGC_Packet& packet);


#endif //VECTORVISION_SVGC_HEADER_H
