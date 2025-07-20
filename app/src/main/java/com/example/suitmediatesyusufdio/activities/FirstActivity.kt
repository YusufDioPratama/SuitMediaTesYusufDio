package com.example.suitmediatesyusufdio.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatesyusufdio.R

class FirstActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etPalindrome: EditText
    private lateinit var btnCheck: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        etName = findViewById(R.id.etName)
        etPalindrome = findViewById(R.id.etPalindrome)
        btnCheck = findViewById(R.id.btnCheck)
        btnNext = findViewById(R.id.btnNext)

        btnCheck.setOnClickListener {
            val input = etPalindrome.text.toString()
            val message = if (isPalindrome(input)) "isPalindrome" else "not palindrome"
            AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }

        btnNext.setOnClickListener {
            val name = etName.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val cleaned = text.replace("\\s".toRegex(), "").lowercase()
        return cleaned == cleaned.reversed()
    }
}
