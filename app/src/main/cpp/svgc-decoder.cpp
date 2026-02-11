#include <jni.h>
#include <zlib.h>

extern "C" JNIEXPORT jbyteArray JNICALL
Java_com_vectorvision_demo_SvgcDecoder_decompress(JNIEnv *env, jobject thiz, jbyteArray data) {
    jbyte *compressed_data = env->GetByteArrayElements(data, nullptr);
    jsize compressed_size = env->GetArrayLength(data);

    // This is a placeholder for the actual decompressed size. You will need to determine this yourself.
    uLong decompressed_size = 1024 * 1024; // 1MB
    auto *decompressed_data = new Bytef[decompressed_size];

    int result = uncompress(decompressed_data, &decompressed_size, (const Bytef *) compressed_data, compressed_size);

    env->ReleaseByteArrayElements(data, compressed_data, JNI_ABORT);

    if (result != Z_OK) {
        // Handle error
        return nullptr;
    }

    jbyteArray decompressed_array = env->NewByteArray(decompressed_size);
    env->SetByteArrayRegion(decompressed_array, 0, decompressed_size, (const jbyte *) decompressed_data);

    delete[] decompressed_data;

    return decompressed_array;
}
