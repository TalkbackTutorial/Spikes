package com.example.reusablebinding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.reusablebinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.button.setOnClickListener {
            val name = binding.nameEntry.editText.text.toString()
            val age = if (binding.ageEntry.editText.text.toString().isEmpty()) 0 else binding.ageEntry.editText.text.toString().toInt()
            val message = this.getString(R.string.toast).format(name, age)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}