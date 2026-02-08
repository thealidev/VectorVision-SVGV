cmake_minimum_required(VERSION 3.22.1)

project("vectorvision")

add_library(
    vectorvision
    SHARED
    native-lib.cpp
)

find_library(log-lib log)

target_link_libraries(
    vectorvision
    ${log-lib}
)
