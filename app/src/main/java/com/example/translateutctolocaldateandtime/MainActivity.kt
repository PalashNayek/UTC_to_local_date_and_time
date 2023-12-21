package com.example.translateutctolocaldateandtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val utcDateTimeStr = "2023-12-21T15:39:39Z" // Example UTC DateTime => 2023-12-15T15:00:00Z To convert 2023-12-21T15:39:39.5193744Z
        val localDateTimeStr = convertUtcToLocal(utcDateTimeStr)

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = localDateTimeStr
    }

    private fun convertUtcToLocal(utcDateTimeStr: String): String {
        val utcFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val localFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        val utcDateTime = LocalDateTime.parse(utcDateTimeStr, utcFormatter)
            .atZone(ZoneId.of("UTC"))

        val localDateTime = utcDateTime.withZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime()

        return localFormatter.format(localDateTime)
    }
}