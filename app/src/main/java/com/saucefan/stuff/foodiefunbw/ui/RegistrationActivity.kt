package com.saucefan.stuff.foodiefunbw.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saucefan.stuff.foodiefunbw.R
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        textView_signIn.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
            finish()
        }
    }
}
