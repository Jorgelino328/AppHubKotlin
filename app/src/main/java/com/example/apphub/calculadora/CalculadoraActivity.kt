package com.example.apphub.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.apphub.R

class CalculadoraActivity : AppCompatActivity() {
    private lateinit var tvDisplay: TextView
    private var currentInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        tvDisplay = findViewById(R.id.txtResultado)

        // Botões de Entrada Simples (Dígitos e Operadores)
        val simpleInputs = listOf(
            "0" to R.id.btn0, "1" to R.id.btn1, "2" to R.id.btn2, "3" to R.id.btn3,
            "4" to R.id.btn4, "5" to R.id.btn5, "6" to R.id.btn6, "7" to R.id.btn7,
            "8" to R.id.btn8, "9" to R.id.btn9, "." to R.id.btnPonto,
            "+" to R.id.btnSomar, "-" to R.id.btnSubtrair, "×" to R.id.btnMultiplicar, "÷" to R.id.btnDividir,
            "(" to R.id.btnOpenParen, ")" to R.id.btnCloseParen, "^" to R.id.btnPower, "π" to R.id.btnPi
        )
        simpleInputs.forEach { (str, id) ->
            findViewById<Button>(id).setOnClickListener { appendString(str) }
        }

        // Botões de Funções (anexam o parêntese inicial)
        val functionInputs = listOf(
            "sin(" to R.id.btnSin, "cos(" to R.id.btnCos, "tan(" to R.id.btnTan,
            "ln(" to R.id.btnLn, "log(" to R.id.btnLog, "√(" to R.id.btnSqrt
        )
        functionInputs.forEach { (str, id) ->
            findViewById<Button>(id).setOnClickListener { appendString(str) }
        }

        // Ações de Controle
        findViewById<Button>(R.id.btnIgual).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btnBackspace).setOnClickListener { backspace() }

        updateDisplay()
    }

    private fun appendString(str: String) {
        if (currentInput == "Erro") currentInput = ""
        currentInput += str
        updateDisplay()
    }

    private fun clearAll() {
        currentInput = ""
        updateDisplay()
    }

    private fun backspace() {
        if (currentInput == "Erro") {
            clearAll()
            return
        }
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            updateDisplay()
        }
    }

    private fun updateDisplay() {
        tvDisplay.text = if (currentInput.isNotEmpty()) currentInput else "0"
    }

    private fun calculateResult() {
        if (currentInput.isEmpty() || currentInput == "Erro") return
        try {
            val result = evaluateExpression(currentInput)
            // Remove o ".0" no final de números inteiros
            currentInput = if (result % 1.0 == 0.0) {
                result.toLong().toString()
            } else {
                result.toString()
            }
        } catch (e: Exception) {
            currentInput = "Erro"
        }
        updateDisplay()
    }

    // --- Expression Evaluator (Parser Matemático) ---
    private fun evaluateExpression(str: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < str.length) str[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.code)) x += parseTerm() // Adição
                    else if (eat('-'.code)) x -= parseTerm() // Subtração
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.code) || eat('×'.code)) x *= parseFactor() // Multiplicação
                    else if (eat('/'.code) || eat('÷'.code)) x /= parseFactor() // Divisão
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.code)) return parseFactor() // Positivo Unário
                if (eat('-'.code)) return -parseFactor() // Negativo Unário

                var x: Double
                val startPos = pos
                if (eat('('.code)) {
                    x = parseExpression()
                    eat(')'.code)
                } else if (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) { // Números
                    while (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) nextChar()
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch == 'π'.code) { // Pi
                    nextChar()
                    x = Math.PI
                } else if (ch >= 'a'.code && ch <= 'z'.code || ch == '√'.code) { // Funções
                    while (ch >= 'a'.code && ch <= 'z'.code || ch == '√'.code) nextChar()
                    val func = str.substring(startPos, pos)
                    x = parseFactor()
                    x = when (func) {
                        "√" -> Math.sqrt(x)
                        "sin" -> Math.sin(Math.toRadians(x)) // Graus
                        "cos" -> Math.cos(Math.toRadians(x)) // Graus
                        "tan" -> Math.tan(Math.toRadians(x)) // Graus
                        "log" -> Math.log10(x)
                        "ln" -> Math.log(x)
                        else -> throw RuntimeException("Função desconhecida: $func")
                    }
                } else {
                    throw RuntimeException("Caractere inesperado: " + ch.toChar())
                }

                if (eat('^'.code)) x = Math.pow(x, parseFactor()) // Potência

                return x
            }
        }.parse()
    }

    // Salvar e Restaurar Estado
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currentInput", currentInput)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentInput = savedInstanceState.getString("currentInput", "")
        updateDisplay()
    }
}