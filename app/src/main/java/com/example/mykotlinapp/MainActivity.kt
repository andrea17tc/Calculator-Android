package com.example.mykotlinapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var buttonAdd: Button
    lateinit var buttonSubtract: Button
    lateinit var buttonMultiply: Button
    lateinit var buttonDivide: Button
    lateinit var button1 : Button
    lateinit var button2 : Button
    lateinit var button3 : Button
    lateinit var button4 : Button
    lateinit var button5 : Button
    lateinit var button6 : Button
    lateinit var button7 : Button
    lateinit var button8 : Button
    lateinit var button9 : Button
    lateinit var button0 : Button
    lateinit var buttonDot : Button
    lateinit var buttonEqual : Button
    lateinit var buttonClear : Button
    lateinit var buttonDelete : Button
    //var result = 0.0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSubtract = findViewById(R.id.buttonSubtract)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        button0 = findViewById(R.id.button0)
        buttonDot = findViewById(R.id.buttonDot)
        buttonEqual = findViewById(R.id.buttonEqual)
        buttonClear = findViewById(R.id.buttonClear)
        buttonDelete = findViewById(R.id.buttonDelete)

        buttonAdd.setOnClickListener(this)
        buttonSubtract.setOnClickListener(this)
        buttonMultiply.setOnClickListener(this)
        buttonDivide.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button0.setOnClickListener(this)
        buttonDot.setOnClickListener(this)
        buttonEqual.setOnClickListener(this)
        buttonClear.setOnClickListener(this)
        buttonDelete.setOnClickListener(this)


    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        try {
            var string = findViewById<TextView>(R.id.display).text.toString()
            if (string == "0" && (v?.id == R.id.buttonAdd || v?.id == R.id.buttonSubtract || v?.id == R.id.buttonMultiply || v?.id == R.id.buttonDivide)) {
                throw IllegalArgumentException("Cannot start with an operation")
            }
            if(string == "0")
                string = ""
            //if the string is too long to fit in the display, show an error message
            if(string.length == 33) {
                //allow only delete and clear
                if(v?.id != R.id.buttonDelete && v?.id != R.id.buttonClear && v?.id != R.id.buttonEqual) {
                    throw IllegalArgumentException("Input too long")
                }
            }
            when (v?.id) {
                R.id.buttonAdd -> {
                    //if last character is an operator, replace it with the new operator
                    if(string.isNotEmpty() && (string.last() == '+' || string.last() == '-' || string.last() == '*' || string.last() == '/')) {
                        string = string.substring(0, string.length - 1)
                    }
                    string = string + "+"
                }

                R.id.buttonSubtract -> {
                    if(string.isNotEmpty() && (string.last() == '+' || string.last() == '-' )) {
                        string = string.substring(0, string.length - 1)
                    }
                    string = string + "-"
                }

                R.id.buttonMultiply -> {
                    if(string.isNotEmpty() && (string.last() == '+' || string.last() == '*' || string.last() == '/')) {
                        string = string.substring(0, string.length - 1)
                    }
                    string = string + "*"
                }

                R.id.buttonDivide -> {
                    if(string.isNotEmpty() && (string.last() == '+' || string.last() == '*' || string.last() == '/')) {
                        string = string.substring(0, string.length - 1)
                    }
                    string = string + "/"
                }

                R.id.button1 -> {
                    string = string + "1"
                }

                R.id.button2 -> {
                    string = string + "2"
                }

                R.id.button3 -> {
                    string = string + "3"
                }

                R.id.button4 -> {
                    string = string + "4"
                }

                R.id.button5 -> {
                    string = string + "5"
                }

                R.id.button6 -> {
                    string = string + "6"
                }

                R.id.button7 -> {
                    string = string + "7"
                }

                R.id.button8 -> {
                    string = string + "8"
                }

                R.id.button9 -> {
                    string = string + "9"
                }

                R.id.button0 -> {
                    string = string + "0"
                }

                R.id.buttonDot -> {
                    string = string + "."
                }

                R.id.buttonClear -> {
                    string = "0"
                }

                R.id.buttonDelete -> {
                    string = string.substring(0, string.length - 1)
                    if(string.isEmpty()) {
                        string = "0"
                    }
                }

                R.id.buttonEqual -> {
                    var result = eval(string).toString()
                    if(result.endsWith(".0")) {
                        result = result.substring(0, result.length - 2)
                    }
                    string = result
                }
            }
            findViewById<TextView>(R.id.display).text = string
        } catch (e: Exception) {
            val errorMessage = "Invalid input"
            val toast = Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun eval(string: String): Double {
        val expression = ExpressionBuilder(string).build()
        return expression.evaluate()
    }
}