package com.onurerdem.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onurerdem.learnkotlin.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private var userName: String = ""
    private var userSurname: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            userName = intent.getStringExtra(Constant.USER_NAME_KEY).toString()
            userSurname = intent.getStringExtra(Constant.USER_SURNAME_KEY).toString()
            tvWelcome.text = getString(R.string.welcome_text, userName, userSurname)
        }
    }
}