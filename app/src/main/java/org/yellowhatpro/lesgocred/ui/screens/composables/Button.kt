package org.yellowhatpro.lesgocred.ui.screens.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun HomeButton(
    modifier : Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit

) {
    Button(
        shape = RectangleShape,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .drawBehind {
                drawLine(
                    start = Offset(0f, size.height - 6f),
                    end = Offset(size.width + 6f, size.height - 6f),
                    color = Color.DarkGray,
                    strokeWidth = 4.dp.toPx(),
                    blendMode = BlendMode.Lighten
                )
                drawLine(
                    start = Offset(size.width + 1f, 8f),
                    end = Offset(size.width + 1f, size.height - 8f),
                    color = Color.Gray,
                    strokeWidth = 4.dp.toPx(),
                    blendMode = BlendMode.Lighten,
                )
            },
        onClick = onClick
    ) {
        content()
    }
}