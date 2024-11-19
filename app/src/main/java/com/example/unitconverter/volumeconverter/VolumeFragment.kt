package com.example.unitconverter.volumeconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unitconverter.databinding.FragmentVolumeBinding
import java.text.DecimalFormat

class VolumeFragment : Fragment() {

    private lateinit var binding: FragmentVolumeBinding
    // Declaring ignoreTextChanges to prevent infinite loops
    private var ignoreTextChanges = false
    // Declaring a decimalFormat to set formatting of the numbers
    private val decimalFormat = DecimalFormat("#.##")

    // Only setup the root binding in the onCreateView method
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVolumeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.radioGroup.check(binding.radioButtonUk.id)
        //setupAllTextWatchers()
    }

    /*private fun setupAllTextWatchers() {
        // Set up TextWatchers for each EditText
        setupTextWatcher(binding.editTextKilometer, ConversionUnitLength.KILOMETER)
        setupTextWatcher(binding.editTextMeter, ConversionUnitLength.METER)
        setupTextWatcher(binding.editTextCentimeter, ConversionUnitLength.CENTIMETER)
        setupTextWatcher(binding.editTextMillimeter, ConversionUnitLength.MILLIMETER)
        setupTextWatcher(binding.editTextMile, ConversionUnitLength.MILE)
        setupTextWatcher(binding.editTextYard, ConversionUnitLength.YARD)
        setupTextWatcher(binding.editTextFoot, ConversionUnitLength.FOOT)
        setupTextWatcher(binding.editTextInch, ConversionUnitLength.INCH)
    }


    // A function to set up TextWatchers for each EditText
    private fun setupTextWatcher(editText: EditText, unit: ConversionUnitLength) {
        editText.addTextChangedListener(object : TextWatcher {
            // We don't need these functions, but they need to be overridden for this abstract class of TextWatcher
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            // This function is called after the text in a given EditText has been changed (after every keystroke)
            // The provided variable s represents the text that was entered (the whole text in the EditText)
            override fun afterTextChanged(s: Editable?) {
                // If ignoreTextChanges is true, don't update the fields, otherwise this would create an infinite loop
                if (ignoreTextChanges) return

                // If the EditText is empty or null (e.g. if the user deletes all characters in a field), clear all fields
                if (s.isNullOrEmpty()) {
                    clearAllFields()
                    return
                }

                // If there is a user input in the EditText, try to first convert it to a String (because the default
                // type is an Editable), then to a double which we can calculate with. Set ignoreTextChanges to true so
                // that no other TextChangedListeners get called when updating the other fields. Then call the updateFields
                // function which takes the inputValue (as a double) and the unit that inputValue represents (aka the unit of
                // the field the user typed in).
                // If this throws a NumberFormatException (e.g. when the user types something else than a number (with the provided
                // keypad this only happens when typing a '.'), let the user know they should enter a number via Toast.
                // Finally set the ignoreTextChanges back to false
                try {
                    val inputValue = s.toString().toDouble()
                    ignoreTextChanges = true
                    updateFields(inputValue, unit)
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "Please enter a number", Toast.LENGTH_SHORT)
                        .show()
                } finally {
                    ignoreTextChanges = false
                }
            }
        })
    }

    // This function takes the inputValue and its unit and updates all the other fields in the layout
    private fun updateFields(value: Double, unit: ConversionUnitLength) {
        // Convert the input value to meters
        val baseValue = unit.convertToBase(value)

        // Update all fields based on the base value
        // The first line calls the convertFromBase() method of the ConversionUnit class for the given unit,
        // and formats the result using the decimalFormat.
        // The second line sets the selection of the editText (aka the cursor) to the end of the text
        binding.editTextKilometer.setText(
            decimalFormat.format(
                ConversionUnitLength.KILOMETER.convertFromBase(
                    baseValue
                )
            )
        )
        binding.editTextKilometer.setSelection(binding.editTextKilometer.text!!.length)

        // Because meter is the baseValue, we don't have to convert the value for this one
        binding.editTextMeter.setText(decimalFormat.format(baseValue))
        binding.editTextMeter.setSelection(binding.editTextMeter.text!!.length)

        binding.editTextCentimeter.setText(
            decimalFormat.format(
                ConversionUnitLength.CENTIMETER.convertFromBase(
                    baseValue
                )
            )
        )
        binding.editTextCentimeter.setSelection(binding.editTextCentimeter.text!!.length)

        binding.editTextMillimeter.setText(
            decimalFormat.format(
                ConversionUnitLength.MILLIMETER.convertFromBase(
                    baseValue
                )
            )
        )
        binding.editTextMillimeter.setSelection(binding.editTextMillimeter.text!!.length)

        binding.editTextMile.setText(
            decimalFormat.format(
                ConversionUnitLength.MILE.convertFromBase(
                    baseValue
                )
            )
        )
        binding.editTextMile.setSelection(binding.editTextMile.text!!.length)

        binding.editTextYard.setText(
            decimalFormat.format(
                ConversionUnitLength.YARD.convertFromBase(
                    baseValue
                )
            )
        )
        binding.editTextYard.setSelection(binding.editTextYard.text!!.length)

        binding.editTextFoot.setText(
            decimalFormat.format(
                ConversionUnitLength.FOOT.convertFromBase(
                    baseValue
                )
            )
        )
        binding.editTextFoot.setSelection(binding.editTextFoot.text!!.length)

        binding.editTextInch.setText(
            decimalFormat.format(
                ConversionUnitLength.INCH.convertFromBase(
                    baseValue
                )
            )
        )
        binding.editTextInch.setSelection(binding.editTextInch.text!!.length)
    }

    // This function clears all the fields in the layout. To prevent an infinite loop, we set
    // ignoreTextChanges to true when changing the fields and set it back to false after the changes
    private fun clearAllFields() {
        ignoreTextChanges = true
        try {
            binding.editTextKilometer.setText("")
            binding.editTextMeter.setText("")
            binding.editTextCentimeter.setText("")
            binding.editTextMillimeter.setText("")
            binding.editTextMile.setText("")
            binding.editTextYard.setText("")
            binding.editTextFoot.setText("")
            binding.editTextInch.setText("")
        } finally {
            ignoreTextChanges = false
        }
    }*/
}