package com.example.tiptime

import android.os.Bundle
import android.widget.NumberPicker.OnValueChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TiptimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiptimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TiptimeScreen()
                }
            }
        }
    }
}

@Composable
fun TiptimeScreen(){
    var inputString by  remember { mutableStateOf("") }
    val amount = inputString.toDoubleOrNull() ?: 0.0
    val tip = calculateTip((amount))

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.app_title),
            fontSize = 24.sp,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        InputNumberField(inputString , onValueChange = {inputString = it})
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(id = R.string.calculator_result, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun InputNumberField(
    inputString: String,
    onValueChange: (String) -> Unit
){



    TextField(
        value = inputString,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = {
                    Text(text = stringResource(R.string.field_placeholder), modifier = Modifier.fillMaxWidth())
                },
        singleLine = true,
    )
}

private fun calculateTip(tipAmount: Double, tipPercent: Double = 15.0):String{
    val tip = tipPercent / 100 * tipAmount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TiptimeTheme {
        TiptimeScreen()
    }
}