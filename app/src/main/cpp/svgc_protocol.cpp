
#include "svgc_header.h"
#include <string>

/**
 * @brief Encodes a packet of geometric data into the final .svgc byte stream.
 * 
 * This function takes the structured SVGC_Packet (containing a header and a list
 * of path strings) and serializes it into a flat byte array according to the
 * SVGC specification.
 * 
 * The current implementation is a PLACEHOLDER. It correctly appends the header
 * and the dummy path, but a real implementation would need a more robust
 * serialization method (e.g., specifying path lengths, etc.).
 * 
 * @param packet The SVGC_Packet to be encoded.
 * @return A vector of characters (bytes) representing the final .svgc file.
 */
std::vector<char> encodeToSvgcFormat(const SVGC_Packet& packet) {
    std::vector<char> fileBytes;

    // 1. Write the header/magic number to the byte stream
    fileBytes.insert(fileBytes.end(), packet.header.begin(), packet.header.end());

    // 2. Write the path data
    //    A real implementation would need to handle multiple paths and delimiters.
    for (const auto& path_str : packet.paths) {
        fileBytes.insert(fileBytes.end(), path_str.begin(), path_str.end());
    }

    return fileBytes;
}
