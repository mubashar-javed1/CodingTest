package com.mobi.codingtest.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import java.util.regex.Pattern

@Suppress("DEPRECATION")
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, if (message.length > 30) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
    }

    protected fun isValid(editText: EditText): Boolean {
        editText.error = null
        if (getString(editText).isEmpty()) {
            editText.error = "Field Required"
            return false
        }
        return true
    }

    protected fun isValid(vararg editTexts: EditText): Boolean {
        var valid = true
        for (editText in editTexts) {
            if (isValid(editText)) {
                valid = false
            }
        }
        return valid
    }

    protected fun isValidInput(editText: EditText): Boolean {
        return Pattern.compile("[a-zA-Z0-9]+").matcher(getString(editText)).matches()
    }

    protected fun getString(editText: EditText): String {
        return editText.text.toString().trim { it <= ' ' }
    }

    protected fun checkNetworkAvailability(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}