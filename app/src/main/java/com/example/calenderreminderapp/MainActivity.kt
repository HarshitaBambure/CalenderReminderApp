package com.example.calenderreminderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.calenderreminderapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding.btnAddEvent.setOnClickListener(){
            if (!binding.etTitle.getText().toString().isEmpty() && !binding.etLoc.getText().toString().isEmpty() && !binding.etDesc
                    .getText().toString().isEmpty()) {
                val intent = Intent(Intent.ACTION_INSERT)
                intent.setData(CalendarContract.Events.CONTENT_URI)
                intent.putExtra(CalendarContract.Events.TITLE,binding.etTitle.getText().toString())
                intent.putExtra(CalendarContract.Events.DESCRIPTION,binding.etDesc.getText().toString())
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION,binding.etLoc.getText().toString())
                intent.putExtra(CalendarContract.Events.ALL_DAY,"true")
                intent.putExtra(Intent.EXTRA_EMAIL,"")
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "There is no app that support this action", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "Please fill all the fields",
                    Toast.LENGTH_SHORT).show();
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}