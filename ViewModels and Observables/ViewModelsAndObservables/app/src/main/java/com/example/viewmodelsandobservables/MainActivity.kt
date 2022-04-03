package com.example.viewmodelsandobservables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.viewmodelsandobservables.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: AnimalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.binding.animalViewModel = this.model

        val animalNameObserver = Observer<String> { newName ->
            // Update UI
            binding.textView.text = newName
        }
        model.animalName.observe(this, animalNameObserver)

        binding.toastButton.setOnClickListener {
            Toast.makeText(this, model.animalName.value, Toast.LENGTH_SHORT).show()
        }
        binding.duplicateButton.setOnClickListener {
            this.model.duplicateName()
        }
    }
}