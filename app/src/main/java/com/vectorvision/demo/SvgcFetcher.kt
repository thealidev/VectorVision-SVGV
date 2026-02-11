package com.vectorvision.demo

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import java.io.File
import java.io.FileInputStream
import java.nio.ByteBuffer

class SvgcFetcher(private val model: File) : DataFetcher<ByteBuffer> {
    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in ByteBuffer>) {
        try {
            val inputStream = FileInputStream(model)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            callback.onDataReady(ByteBuffer.wrap(buffer))
        } catch (e: Exception) {
            callback.onLoadFailed(e)
        }
    }

    override fun cleanup() {
        // Do nothing
    }

    override fun cancel() {
        // Do nothing
    }

    override fun getDataClass(): Class<ByteBuffer> = ByteBuffer::class.java

    override fun getDataSource(): DataSource = DataSource.LOCAL
}
