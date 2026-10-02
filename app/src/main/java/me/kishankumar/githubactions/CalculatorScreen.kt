package me.kishankumar.githubactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorApp(
    logic: CalculatorLogic = remember { CalculatorLogic() }
) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Display Area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 16.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(24.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        text = logic.display,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("calculator_display")
                    )
                }

                // Buttons Grid
                val buttons = listOf(
                    listOf("C", "/", "*", "-"),
                    listOf("7", "8", "9", "+"),
                    listOf("4", "5", "6", "="),
                    listOf("1", "2", "3", "0")
                )

                for (row in buttons) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (symbol in row) {
                            CalculatorButton(
                                symbol = symbol,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    when (symbol) {
                                        "C" -> logic.onClear()
                                        "=" -> logic.onEquals()
                                        "+", "-", "*", "/" -> logic.onOperationInput(symbol)
                                        else -> logic.onDigitInput(symbol)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val isOperation = symbol in listOf("+", "-", "*", "/", "=")
    val isClear = symbol == "C"

    val containerColor = when {
        isClear -> MaterialTheme.colorScheme.errorContainer
        isOperation -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.secondaryContainer
    }

    val contentColor = when {
        isClear -> MaterialTheme.colorScheme.onErrorContainer
        isOperation -> MaterialTheme.colorScheme.onPrimaryContainer
        else -> MaterialTheme.colorScheme.onSecondaryContainer
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)
            .testTag("btn_$symbol"),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = symbol,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
