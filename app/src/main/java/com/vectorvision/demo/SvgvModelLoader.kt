package com.vectorvision.demo

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import java.io.File
import java.nio.ByteBuffer

class SvgvModelLoader : ModelLoader<File, ByteBuffer> {
    override fun buildLoadData(model: File, width: Int, height: Int, options: Options): ModelLoader.LoadData<ByteBuffer>? {
        return ModelLoader.LoadData(ObjectKey(model), SvgvFetcher(model))
    }

    override fun handles(model: File): Boolean = model.extension == "svgv"

    class Factory : ModelLoaderFactory<File, ByteBuffer> {
        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<File, ByteBuffer> {
            return SvgvModelLoader()
        }

        override fun teardown() {
            // Do nothing
        }
    }
}
