package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.tiptime.databinding.ActivityMainBinding
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            try {
                val serviceAmount = binding.costOfService.text.toString().toDouble()
                if (binding.roundUpSwitch.isChecked) {
                    val tipResult =
                        TipCalculator.calculateRoundedTip(binding.tipOptions, serviceAmount)
                    binding.tipResult.visibility = View.VISIBLE
                    binding.dollar.visibility = View.VISIBLE
                    binding.tipResult2.text = tipResult.toString()
                    binding.tipResult2.contentDescription = tipResult.toString()
                    val secondToast =
                        Toast.makeText(this, "Tip should be: $tipResult", Toast.LENGTH_SHORT)
                    secondToast.show()
                } else {
                    val tipResult = TipCalculator.calculateTip(binding.tipOptions, serviceAmount)
                    binding.tipResult2.text = tipResult.toString()
                    binding.tipResult.visibility = View.VISIBLE
                    binding.dollar.visibility = View.VISIBLE
                    binding.tipResult2.contentDescription = tipResult.toString()
                    val secondToast =
                        Toast.makeText(this, "Tip should be: $tipResult", Toast.LENGTH_SHORT)
                    secondToast.show()
                }
            } catch (e: java.lang.NumberFormatException) {//handles scenario when user doesn't add a value
                val toast =
                    Toast.makeText(this, "Please add the service amount", Toast.LENGTH_SHORT)
                toast.show()
                binding.tipResult2.text = "" //handles scenario when input is deleted after the first try
                binding.tipResult.visibility = View.INVISIBLE
                binding.dollar.visibility = View.INVISIBLE
            }
        }
    }
}

class TipCalculator {
    companion object {
        fun calculateTip(radioGroup: RadioGroup, serviceAmount: Double): Int {
            return when (radioGroup.checkedRadioButtonId) {
                R.id.tip_first_option -> round(serviceAmount * 15 / 100).toInt()
                R.id.tip_second_option -> round(serviceAmount * 12 / 100).toInt()
                else -> round(serviceAmount * 10 / 100).toInt()
            }
        }

        fun calculateRoundedTip(radioGroup: RadioGroup, serviceAmount: Double): Int {
            return when (radioGroup.checkedRadioButtonId) {
                R.id.tip_first_option -> round(serviceAmount * 15 / 100 / 5).toInt() * 5
                R.id.tip_second_option -> round(serviceAmount * 12 / 100 / 5).toInt() * 5
                else -> round(serviceAmount * 10 / 100 / 5).toInt() * 5
            }
        }
    }
}
