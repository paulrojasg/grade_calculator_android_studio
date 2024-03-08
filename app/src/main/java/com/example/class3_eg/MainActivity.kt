package com.example.class3_eg

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.class3_eg.databinding.ActivityMainBinding
import java.lang.Exception
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calculateBtnAutoEnable()
        calculateBtn()
    }

    private fun calculateBtnAutoEnable() {
        fun enableCalculateButton() {
            val studentNotEmpty = binding.etStudentNameInput.text.isNotEmpty()
            val courseNotEmpty = binding.etCourseNameInput.text.isNotEmpty()
            val grade1NotEmpty = binding.etGrade1Input.text.isNotEmpty()
            val grade2NotEmpty = binding.etGrade2Input.text.isNotEmpty()

            val calculateButton = binding.btnCalculateGrade

            calculateButton.isEnabled = studentNotEmpty && courseNotEmpty && grade1NotEmpty && grade2NotEmpty
        }

        binding.etStudentNameInput.addTextChangedListener { enableCalculateButton() }
        binding.etCourseNameInput.addTextChangedListener { enableCalculateButton() }
        binding.etGrade1Input.addTextChangedListener { enableCalculateButton() }
        binding.etGrade2Input.addTextChangedListener { enableCalculateButton() }
    }

    private fun calculateBtn() {
        binding.btnCalculateGrade.setOnClickListener {
            val student = binding.etStudentNameInput.text.toString()
            val course = binding.etCourseNameInput.text.toString()
            val grade1 = binding.etGrade1Input.text.toString()
            val grade2 = binding.etGrade2Input.text.toString()

            // Get average

            val grade1Num = grade1.toFloat()
            val grade2Num = grade2.toFloat()
            val repeatPenalty = if (binding.cbRepeatedCourse.isChecked) 0.5f else 0.0f

            val finalGradeNum = (((grade1Num + grade2Num) / 2.0f) - repeatPenalty)

            // Show if student approved or disapproved

            val resultMessage: String = if (finalGradeNum >= 3.0) {
                "Aprobaste"
            } else {
                "Desaprobaste"
            }

            Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show()


            val finalGrade = finalGradeNum.toString()


            // Show result in textviews
            binding.tvResultStudentName.text = "Nombre: $student"
            binding.tvResultCourseName.text = "Curso: $course"
            binding.tvResultGrade1.text = "Nota 1: $grade1"
            binding.tvResultGrade2.text = "Nota 2: $grade2"
            binding.tvResultFinalGrade.text = "Nota final: $finalGrade"

            //Clear inputs
            binding.etStudentNameInput.text.clear()
            binding.etCourseNameInput.text.clear()
            binding.etGrade1Input.text.clear()
            binding.etGrade2Input.text.clear()
        }
    }


}