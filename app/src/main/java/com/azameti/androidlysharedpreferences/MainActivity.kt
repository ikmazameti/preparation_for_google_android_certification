package com.azameti.androidlysharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.azameti.androidlysharedpreferences.PreferenceHelper.clearValues
import com.azameti.androidlysharedpreferences.PreferenceHelper.customPreference
import com.azameti.androidlysharedpreferences.PreferenceHelper.defaultPreference
import com.azameti.androidlysharedpreferences.PreferenceHelper.password
import com.azameti.androidlysharedpreferences.PreferenceHelper.userId


class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btnSave: Button
    lateinit var btnShow: Button
    lateinit var btnClear: Button
    lateinit var btnShowDefault: Button
    lateinit var inPassword: EditText
    lateinit var inUserId: EditText
    val CUSTOM_PREF_NAME = "User_Pref_Data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSave = findViewById(R.id.btnSave)
        btnShow = findViewById(R.id.btnShow)
        btnClear = findViewById(R.id.btnClear)
        btnShowDefault = findViewById(R.id.btnShowDefault)
        inUserId = findViewById(R.id.inUserId)
        inPassword = findViewById(R.id.inPassword)


        btnSave.setOnClickListener(this)
        btnClear.setOnClickListener(this)
        btnShow.setOnClickListener(this)
        btnShowDefault.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val prefs = customPreference(this, CUSTOM_PREF_NAME)

        when (v?.id) {
            R.id.btnSave -> {
                prefs.password = inPassword.text.toString()
                prefs.userId = inUserId.text.toString().toInt()
            }
            R.id.btnClear -> {
                prefs.clearValues
            }
            R.id.btnShow -> {
                inUserId.setText(prefs.userId.toString())
                inPassword.setText(prefs.password)
            }
            R.id.btnShowDefault -> {
                val defaultPrefs = defaultPreference(this)
                inUserId.setText(defaultPrefs.userId.toString())
                inPassword.setText(defaultPrefs.password)
            }
        }
    }


}

object PreferenceHelper {
    val USER_ID = "USER_ID"
    val USER_PASSWORD = "PASSWORD"

    fun defaultPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun customPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.userId
        get() = getInt(USER_ID, 0)
        set(value) {
            editMe {
                it.putInt(USER_ID, value)
            }
        }

    var SharedPreferences.password
        get() = getString(USER_PASSWORD, "")
        set(value) {
            editMe {
                it.putString(USER_PASSWORD, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}