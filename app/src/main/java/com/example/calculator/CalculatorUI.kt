package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.BttnDarkGrey
import com.example.calculator.ui.theme.BttnGreen
import com.example.calculator.ui.theme.BttnRed
import com.example.calculator.ui.theme.DividerColor
import com.example.calculator.ui.theme.TextGreen
import com.example.calculator.ui.theme.TextGrey
import com.example.calculator.ui.theme.TextGreyOnBttn
import com.example.calculator.ui.theme.TextWhite

@Composable
fun CalculatorUI(
    state: State,
    buttonSpacing: Dp = 8.dp,
    modifier: Modifier = Modifier,
    onAction: (Action) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        )
        {
            Column(
                verticalArrangement = Arrangement.spacedBy(buttonSpacing),

                ) {
//                Text(
//                    text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
//                    textAlign = TextAlign.End,
//                    modifier = Modifier
//                        .height(160.dp)
//                        .fillMaxWidth()
//                        .padding(vertical = 32.dp),
//                    fontWeight = FontWeight.Light,
//                    fontSize = 70.sp,
//                    color = TextWhite,
//                    maxLines = 2,
//                    overflow = TextOverflow.Visible,
//                    lineHeight = 80.sp
//                )
                AutoResizableText(
                    text = state.number1.toString() +
                            (state.operation?.symbol ?: "") +
                            state.number2.toString(),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Light,
                    color = TextWhite,
                    lineHeight = 80.sp
                )
                AutoResizableText(
                    text = state.result,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth(),
                    fontWeight = FontWeight.Light,
                    fontSize = 60.sp,
                    color = TextGrey,
                    lineHeight = 70.sp
                )
                if (state.number2.toString().isNotEmpty()) {
                    onAction(Action.CalculateTemporalResult)
                }
            }
            HorizontalDivider(color = DividerColor, thickness = 2.dp)
            Column(
                verticalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    Button(
                        symbol = "C",
                        textColor = TextGreyOnBttn,
                        modifier = Modifier
                            .background(BttnRed)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Delete)
                        }
                    )
                    Button(
                        symbol = "+/-",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.ChangeSign)
                        }
                    )
                    Button(
                        symbol = "%",
                        textColor = TextGreen,
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.FindPercentage)
                        }
                    )
                    Button(
                        symbol = "/",
                        textColor = TextGreen,
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Operation(Operation.Divide))
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    Button(
                        symbol = "7",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Number(7))
                        }
                    )
                    Button(
                        symbol = "8",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Number(8))
                        }
                    )
                    Button(
                        symbol = "9",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Number(9))
                        }
                    )
                    Button(
                        symbol = "x",
                        textColor = TextGreen,
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Operation(Operation.Multiply))
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    Button(
                        symbol = "4",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Number(4))
                        }
                    )
                    Button(
                        symbol = "5",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Number(5))
                        }
                    )
                    Button(
                        symbol = "6",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Number(6))
                        }
                    )
                    Button(
                        symbol = "-",
                        textColor = TextGreen,
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Operation(Operation.Subtract))
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    Button(
                        symbol = "1",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Number(1))
                        }
                    )
                    Button(
                        symbol = "2",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Number(2))
                        }
                    )
                    Button(
                        symbol = "3",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Number(3))
                        }
                    )
                    Button(
                        symbol = "+",
                        textColor = TextGreen,
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Operation(Operation.Add))
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    Button(
                        symbol = "0",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Number(0))
                        }
                    )
                    Button(
                        symbol = ".",
                        modifier = Modifier
                            .background(BttnDarkGrey)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(Action.Decimal)
                        }
                    )
                    Button(
                        symbol = "=",
                        textColor = TextGreyOnBttn,
                        modifier = Modifier
                            .background(BttnGreen)
                            .aspectRatio(2f)
                            .weight(2f),
                        onClick = {
                            onAction(Action.PerformCalculation)
                        }
                    )
                }
            }
        }
    }
}