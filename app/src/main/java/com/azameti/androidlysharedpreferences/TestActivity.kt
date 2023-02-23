package com.azameti.androidlysharedpreferences

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class TestActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private val sharedPrefFile = "com.azameti.androidlysharedpreferences"

    // Current count
    private var count = 0

    // Current background color
    private var colour = 0

    // Text view to display both count and color
    private lateinit var showCountTextView: TextView

    // Key for current count
    private val COUNT_KEY = "count"

    // Key for current color
    private val COLOR_KEY = "color"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // Initialize views, color, prefs
        showCountTextView = findViewById(R.id.count_textview)
        colour = ContextCompat.getColor(this, R.color.default_background)
        prefs = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        count = prefs.getInt(COUNT_KEY, 0)
        showCountTextView.text = String.format("%s", count)

        colour = prefs.getInt(COLOR_KEY, colour)
        showCountTextView.setBackgroundColor(colour)

        // Restore the saved instance state.
//        if (savedInstanceState != null) {
//            count = savedInstanceState.getInt(COUNT_KEY)
//            if (count != 0) {
//                showCountTextView.text = String.format("%s", count)
//            }
//            colour = savedInstanceState.getInt(COLOR_KEY)
//            showCountTextView.setBackgroundColor(colour)
//        }
    }

    /**
     * Handles the onClick for the background color buttons. Gets background
     * color of the button that was clicked, and sets the TextView background
     * to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    fun changeBackground(view: View) {
        val color = (view.background as ColorDrawable).color
        showCountTextView.setBackgroundColor(color)
        colour = color
    }

    /**
     * Handles the onClick for the Count button. Increments the value of the
     * mCount global and updates the TextView.
     *
     * @param view The view (Button) that was clicked.
     */
    fun countUp(view: View?) {
        count++
        showCountTextView.text = String.format("%s", count)
    }

    /**
     * Saves the instance state if the activity is restarted (for example,
     * on device rotation.) Here you save the values for the count and the
     * background color.
     *
     * @param outState The state data.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNT_KEY, count)
        outState.putInt(COLOR_KEY, colour)
    }

    /**
     * Handles the onClick for the Reset button. Resets the global count and
     * background variables to the defaults and resets the views to those
     * default values.
     *
     * @param view The view (Button) that was clicked.
     */
    fun reset(view: View?) {
        // Reset count
        count = 0
        showCountTextView.text = String.format("%s", count)

        // Reset color
        colour = ContextCompat.getColor(this, R.color.default_background)
        showCountTextView.setBackgroundColor(colour)

        // Clear preferences
        val preferencesEditor: SharedPreferences.Editor = prefs.edit()
        preferencesEditor.clear()
        preferencesEditor.apply()

    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor: SharedPreferences.Editor = prefs.edit()

        preferencesEditor.putInt(COUNT_KEY, count)
        preferencesEditor.putInt(COLOR_KEY, colour)
        preferencesEditor.apply()


    }
}