package com.example.thecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorApp()
        }
    }
}

@Composable
fun CalculatorApp() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun add(a: Int, b: Int) = (a + b).toString()
    fun subtract(a: Int, b: Int) = (a - b).toString()
    fun multiply(a: Int, b: Int) = (a * b).toString()
    fun divide(a: Int, b: Int) = if (b != 0) (a / b).toString() else "Cannot divide by zero"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Simple Calculator", fontSize = 24.sp) // the name of the app

        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Enter first number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Enter second number") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                result = add(num1.toIntOrNull() ?: 0, num2.toIntOrNull() ?: 0)
            }) {
                Text("Add")
            }

            Button(onClick = {
                result = subtract(num1.toIntOrNull() ?: 0, num2.toIntOrNull() ?: 0)
            }) {
                Text("Subtract")
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                result = multiply(num1.toIntOrNull() ?: 0, num2.toIntOrNull() ?: 0)
            }) {
                Text("Multiply")
            }

            Button(onClick = {
                result = divide(num1.toIntOrNull() ?: 0, num2.toIntOrNull() ?: 0)
            }) {
                Text("Divide")
            }
        }

        Text("Result: $result", fontSize = 20.sp)
    }
}

