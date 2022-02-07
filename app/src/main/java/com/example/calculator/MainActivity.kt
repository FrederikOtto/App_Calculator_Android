package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import org.mariuszgromada.math.mxparser.*

class MainActivity : AppCompatActivity() {

    lateinit var display: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.input)
        display.setShowSoftInputOnFocus(false)

        display.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("")
                }
            }


        })
    }

    private fun updateText(strToAdd: String) {
        var oldStr : String = display.getText().toString()
        var cursorPos : Int = display.getSelectionStart()
        var leftStr : String = oldStr.substring(0, cursorPos)
        var rightStr: String = oldStr.substring(cursorPos)

        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd)
            display.setSelection(cursorPos + 1)
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr))
            display.setSelection(cursorPos + 1)
        }
    }


    public fun zeroBtn(view:View) {
        updateText(strToAdd = "0")
    }

    public fun oneBtn(view:View) {
        updateText(strToAdd = "1")
    }

    public fun twoBtn(view:View) {
        updateText(strToAdd = "2")
    }

    public fun threeBtn(view:View) {
        updateText(strToAdd = "3")

    }

    public fun fourBtn(view:View) {
        updateText(strToAdd = "4")

    }

    public fun fiveBtn(view:View) {
        updateText(strToAdd = "5")

    }

    public fun sixBtn(view:View) {
        updateText(strToAdd = "6")
    }

    public fun sevenBtn(view:View) {
        updateText(strToAdd = "7")
    }

    public fun eightBtn(view:View) {
        updateText(strToAdd = "8")
    }

    public fun nineBtn(view:View) {
        updateText(strToAdd = "9")
    }



    public fun addBtn(view:View) {
        updateText(strToAdd = "+")
    }

    public fun subtractBtn(view:View) {
        updateText(strToAdd = "-")
    }

    public fun clearBtn(view:View) {
        display.setText("")
    }

    public fun parenthesesBtn(view:View) {
        var cursorPos : Int = display.getSelectionStart()
        var openPar : Int = 0
        var closedPar : Int = 0
        var textLen : Int = display.getText().length

        for (i in 0..cursorPos - 1) {
            if (display.getText().toString().substring(i, i+1).equals("(")) {
                openPar += 1
            }
            if (display.getText().toString().substring(i, i+1).equals(")")) {
                closedPar += 1
            }
        }

        if (openPar == closedPar || display.getText().toString().substring(textLen -1, textLen).equals("(")) {
            updateText(strToAdd = "(")
        }

        else if (closedPar < openPar || !display.getText().toString().substring(textLen -1, textLen).equals("(")) {
            updateText(strToAdd = ")")
        }
        display.setSelection(cursorPos + 1)

    }

    public fun divideBtn(view:View) {
        updateText(strToAdd = "÷")

    }

    public fun multiplyBtn(view:View) {
        updateText(strToAdd = "×")

    }

    public fun plusMinusBtn(view:View) {

    }

    public fun exponentBtn(view:View) {
        updateText(strToAdd = "^")

    }

    public fun pointBtn(view:View) {
        updateText(strToAdd = ".")

    }

    public fun equalsBtn(view:View) {
        var userExp : String = display.getText().toString()

        userExp = userExp.replace("÷", "/")
        userExp = userExp.replace("×", "*")

        val exp : Expression = Expression(userExp)

        var result : String = java.lang.String.valueOf(exp.calculate())

        display.setText(result)
        display.setSelection(result.length)
    }

    public fun backspaceBtn(view:View) {
        var cursorPos : Int = display.getSelectionStart()
        var textLen : Int = display.getText().length

        if (cursorPos != 0 && textLen != 0) {
            var selection: SpannableStringBuilder = display.getText() as SpannableStringBuilder
            selection.replace(cursorPos - 1, cursorPos, "")
            display.setText(selection)
            display.setSelection(cursorPos - 1)
        }
    }

}