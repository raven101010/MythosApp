package com.example.geeeeek

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button
    private lateinit var retrieveButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText = findViewById(R.id.editText)
        saveButton = findViewById(R.id.saveButton)
        clearButton = findViewById(R.id.clearButton)
        retrieveButton = findViewById(R.id.retrieveButton)

        sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE)

        saveButton.setOnClickListener {
            val inputText = editText.text.toString()
            val editor = sharedPreferences.edit()
            editor.putString("text", inputText)
            editor.apply()
        }

        clearButton.setOnClickListener {
            editText.text.clear()
        }

        retrieveButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


    }
}