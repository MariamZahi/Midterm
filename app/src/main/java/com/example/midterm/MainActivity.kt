package com.example.midterm

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    private val unitTypes: Array<String> by lazy {
        resources.getStringArray(R.array.unit_types)
    }

    private val conversionTypes: Array<String> by lazy {
        resources.getStringArray(R.array.conversion_types)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val unitTypeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unitTypes)
        unitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitTypeSpinner.adapter = unitTypeAdapter

        val conversionTypeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, conversionTypes)
        conversionTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        conversionTypeSpinner.adapter = conversionTypeAdapter

        convertButton.setOnClickListener(this)
        unitTypeSpinner.onItemSelectedListener = this
        conversionTypeSpinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // Handle spinner item selection here
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Do nothing
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.convertButton) {
            convert()
        }
    }

    private fun convert() {
        val valueStr = valueEditText.text.toString().trim()
        if (valueStr.isEmpty()) {
            resultTextView.text = "Please enter a value."
            return
        }

        val value = valueStr.toDouble()

        val unitTypePosition = unitTypeSpinner.selectedItemPosition
        val conversionTypePosition = conversionTypeSpinner.selectedItemPosition

        val result: Double

        // Perform the appropriate conversion based on the selected positions
        when (unitTypePosition) {
            0 -> { // Centimeters
                when (conversionTypePosition) {
                    0 -> { // Centimeters to Inches
                        result = value * 0.393701
                        resultTextView.text = "$value cm = $result in"
                    }
                    1 -> { // Feet to Centimeters
                        result = value * 30.48
                        resultTextView.text = "$value ft = $result cm"
                    }
                    2 -> { // Cups to Tablespoons
                        result = value * 16.0
                        resultTextView.text = "$value cup = $result tbsp"
                    }
                    3 -> { // Kilometers to Miles
                        result = value * 0.621371
                        resultTextView.text = "$value km = $result mi"
                    }
                    4 -> { // Grams to Ounces
                        result = value * 0.035274
                        resultTextView.text = "$value g = $result oz"
                    }
                    5 -> { // Milliliters to Ounces
                        result = value * 0.033814
                        resultTextView.text = "$value ml = $result oz"
                    }
                    else -> {
                        resultTextView.text = "Invalid conversion"
                    }
                }
            }
            // Handle other unit types in a similar manner
            else -> {
                resultTextView.text = "Invalid unit type"
            }
        }
    }
}

