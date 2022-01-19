package com.example.hw8_maktab67_part1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateBase = getSharedPreferences("info", Context.MODE_PRIVATE)

        val user: TextInputLayout = findViewById(R.id.user)
        val username: TextInputLayout = findViewById(R.id.username)
        val email: TextInputLayout = findViewById(R.id.email)
        val pass: TextInputLayout = findViewById(R.id.pass)
        val reType: TextInputLayout = findViewById(R.id.re_type)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        val register = findViewById<Button>(R.id.register)
        val info = findViewById<Button>(R.id.info)
        val hide = findViewById<Button>(R.id.hide_btn)

        val showName = findViewById<Button>(R.id.show_name)
        val showUsername = findViewById<Button>(R.id.show_username)
        val showEmail = findViewById<Button>(R.id.show_email)
        val showPass = findViewById<Button>(R.id.show_pass)
        val showGender = findViewById<Button>(R.id.show_gender)

        var check = false

        register.setOnClickListener {

            if (user.editText?.text.toString().isBlank() ||
                username.editText?.text.toString().isBlank() ||
                email.editText?.text.toString().isBlank() ||
                pass.editText?.text.toString().isBlank()
            ) {
                Toast.makeText(this, "please enter all value !!!", Toast.LENGTH_LONG).show()
            } else if (pass.editText?.text.toString() != reType.editText?.text.toString()) {
                Toast.makeText(this, "password not match !!!", Toast.LENGTH_LONG).show()
            } else {
                val saveInfo = dateBase.edit()
                val radioId = radioGroup.checkedRadioButtonId
                val radioButton = findViewById<RadioButton>(radioId)
                saveInfo.putString("name", user.editText?.text.toString())
                saveInfo.putString("userName", username.editText?.text.toString())
                saveInfo.putString("email", email.editText?.text.toString())
                saveInfo.putString("pass", pass.editText?.text.toString())
                saveInfo.putString("gender", radioButton.text.toString())
                saveInfo.apply()

                check = true
            }
        }

        info.setOnClickListener {

            if (check){
                showName.visibility = View.VISIBLE
                showUsername.visibility = View.VISIBLE
                showEmail.visibility = View.VISIBLE
                showPass.visibility = View.VISIBLE
                showGender.visibility = View.VISIBLE
                hide.visibility = View.VISIBLE

                showName.text = dateBase.getString("name", null)
                showUsername.text = dateBase.getString("userName", null)
                showEmail.text = dateBase.getString("email", null)
                showPass.text = dateBase.getString("pass", null)
                showGender.text = dateBase.getString("gender", null)
            }
        }

        hide.setOnClickListener {
            showName.visibility = View.GONE
            showUsername.visibility = View.GONE
            showEmail.visibility = View.GONE
            showPass.visibility = View.GONE
            showGender.visibility = View.GONE
            hide.visibility = View.GONE
        }
    }
}