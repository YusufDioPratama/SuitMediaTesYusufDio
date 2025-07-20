package com.example.suitmediatesyusufdio.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatesyusufdio.R

class SecondActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvSelectedUser: TextView
    private lateinit var btnChooseUser: Button
    private lateinit var ivBack: ImageView

    companion object {
        const val REQUEST_USER = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tvName = findViewById(R.id.tvName)
        tvSelectedUser = findViewById(R.id.tvSelectedUser)
        btnChooseUser = findViewById(R.id.btnChooseUser)
        ivBack = findViewById(R.id.ivBack)

        val name = intent.getStringExtra("name") ?: ""
        tvName.text = name

        btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivityForResult(intent, REQUEST_USER)
        }

        ivBack.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_USER && resultCode == RESULT_OK) {
            val selectedName = data?.getStringExtra("selected_name")
            tvSelectedUser.text = selectedName ?: "Selected User Name"
        }
    }
}
