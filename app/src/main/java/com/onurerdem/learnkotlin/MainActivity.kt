package com.onurerdem.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.onurerdem.learnkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSingUp.setOnClickListener {
                if(edtFirstName.text.toString().isEmpty() || edtLastName.text.toString().isEmpty() || edtPhone.text.toString().isEmpty() || edtPassword.text.toString().isEmpty() || edtConfirmPassword.text.toString().isEmpty())
                {
                    tvAlert.text="Alan boş bırakılamaz!"
                    tvAlert.setTextColor(ContextCompat.getColor(this@MainActivity,R.color.red))
                    tvAlert.visibility= View.VISIBLE
                }
                else if(edtPassword.text.toString()!=edtConfirmPassword.text.toString())
                {
                    tvAlert.text="Şifreler Uyuşmuyor!"
                    tvAlert.setTextColor(ContextCompat.getColor(this@MainActivity,R.color.red))
                    tvAlert.visibility= View.VISIBLE
                }
                else
                {
                    tvAlert.text="Başarıyla kayıt oldunuz!"
                    tvAlert.setTextColor(ContextCompat.getColor(this@MainActivity,R.color.green))
                    tvAlert.visibility= View.VISIBLE
                }
            }
        }
    }
}