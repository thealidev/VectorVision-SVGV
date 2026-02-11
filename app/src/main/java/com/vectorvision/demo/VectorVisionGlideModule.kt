package com.vectorvision.demo

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import java.io.File
import java.nio.ByteBuffer

@GlideModule
class VectorVisionGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.prepend(File::class.java, ByteBuffer::class.java, SvgcModelLoader.Factory())
        registry.prepend(ByteBuffer::class.java, Bitmap::class.java, SvgcDecoder())
        registry.prepend(File::class.java, ByteBuffer::class.java, SvgvModelLoader.Factory())
        registry.prepend(ByteBuffer::class.java, Bitmap::class.java, SvgvDecoder())
    }
}