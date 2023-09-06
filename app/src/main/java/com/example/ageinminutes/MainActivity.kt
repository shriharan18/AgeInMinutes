package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var btnDatePicker: Button
    lateinit var tvSelectedDate: TextView
    lateinit var tvSelectedDateInMinutes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dPD = DatePickerDialog(this, {_, selectedYear, selectedMonth, selectedDayOfMonth ->

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.text = selectedDate

            val sDF = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sDF.parse(selectedDate)

            theDate?.let{

                val selectedDateInMinutes = theDate.time/60000
                val currentDate = sDF.parse(sDF.format(System.currentTimeMillis()))

                currentDate?.let{

                    val currentDateToMinutes = currentDate.time/60000
                    val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                    tvSelectedDateInMinutes.text = differenceInMinutes.toString()
                }
            }

        },
            year, month, day)
        dPD.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dPD.show()
    }
}