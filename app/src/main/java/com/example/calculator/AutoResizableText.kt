package com.example.calculator

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AutoResizableText(
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    textAlign: TextAlign = TextAlign.End,
    modifier: Modifier = Modifier,
    color: Color = style.color,
    fontWeight: FontWeight = FontWeight.Light,
    fontSize: TextUnit = 70.sp,
    lineHeight: TextUnit = 80.sp
) {
    var resizedTextStyle by remember {
        mutableStateOf(style.copy(fontSize = fontSize))
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }

//    val defaultFontSize = MaterialTheme.typography.bodyMedium.fontSize

    Text(
        text = text,
        color = color,
//        fontSize = fontSize,
        textAlign = textAlign,
        fontWeight = fontWeight,
        lineHeight = lineHeight,
        modifier = modifier
            .drawWithContent {
                if (shouldDraw) {
                    drawContent()
                }
            }
            .wrapContentHeight(Alignment.Bottom),
        softWrap = false,
        style = resizedTextStyle,
        onTextLayout = { result ->
            if (result.didOverflowWidth && resizedTextStyle.fontSize > 5.sp) {
//                if (style.fontSize.isUnspecified) {
//                    resizedTextStyle = resizedTextStyle.copy(
//                        fontSize = defaultFontSize
//                    )
//                }
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.95
                )
            } else {
                shouldDraw = true
            }
        }
    )
}