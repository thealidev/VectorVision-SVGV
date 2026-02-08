package com.vectorvision

import android.content.ContentValues
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.vectorvision.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        init {
            System.loadLibrary("vectorvision") // Loading the Anti-Trash Engine [cite: 2026-02-07]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCamera()
        
        binding.captureBtn.setOnClickListener {
            // Trigger processing and save as .svgc [cite: 2026-02-07]
        }
    }

    private fun saveGeometricMedia(data: ByteArray, isVideo: Boolean) {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val extension = if (isVideo) ".svgv" else ".svgc"
        val mime = if (isVideo) "video/svgv" else "image/svgc"

        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "VV_$timestamp$extension")
            put(MediaStore.MediaColumns.MIME_TYPE, mime)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/VectorVision")
        }

        contentResolver.insert(MediaStore.Files.getContentUri("external"), values)?.let { uri ->
            contentResolver.openOutputStream(uri)?.use { it.write(data) }
        }
    }
}
