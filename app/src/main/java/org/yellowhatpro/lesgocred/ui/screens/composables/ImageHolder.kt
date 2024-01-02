package org.yellowhatpro.lesgocred.ui.screens.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageHolder(
    modifier : Modifier = Modifier,
    imageUrl : String,
    contentDescription : String
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier
            .size(70.dp)
            .border(width = Dp.Hairline, color = Color.DarkGray)
            .padding(12.dp)
    )
}