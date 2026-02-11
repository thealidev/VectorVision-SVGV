package com.vectorvision.demo

import android.graphics.Bitmap
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.caverock.androidsvg.SVG
import java.io.ByteArrayInputStream
import java.nio.ByteBuffer

class SvgcDecoder : ResourceDecoder<ByteBuffer, Bitmap> {
    override fun handles(source: ByteBuffer, options: Options): Boolean = true

    override fun decode(source: ByteBuffer, width: Int, height: Int, options: Options): Resource<Bitmap>? {
        val bytes = ByteArray(source.remaining())
        source.get(bytes)

        val decompressed = decompress(bytes)

        val svg = SVG.getFromInputStream(ByteArrayInputStream(decompressed))
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(bitmap)
        svg.renderToCanvas(canvas)

        val bitmapPool = BitmapPoolAdapter()
        return BitmapResource.obtain(bitmap, bitmapPool)
    }

    private external fun decompress(data: ByteArray): ByteArray

    companion object {
        init {
            System.loadLibrary("vectorvision-native")
        }
    }
}
