package edu.temple.assignment3

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_activity)

        val itemNew = intent.getParcelableExtra<Item>("signItem")

        findViewById<TextView>(R.id.textView).text = itemNew?.description
        findViewById<ImageView>(R.id.imageView).setImageResource(itemNew?.resourceId!!)

        val resultIntent = Intent().putExtra("result", "Task completed")
        setResult(RESULT_OK,resultIntent)
    }
}