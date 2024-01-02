package org.yellowhatpro.lesgocred.ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.yellowhatpro.lesgocred.data.Item
import org.yellowhatpro.lesgocred.ui.theme.Typography

@Composable
fun GridCell(
    modifier: Modifier = Modifier,
    item: Item,
) {
    Column(modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {

        ImageHolder(
            imageUrl = item.display_data.icon_url,
            contentDescription = "Categories in Grid Layout"
        )

        Text(
            text = item.display_data.name,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            style = Typography.labelSmall
        )
    }
}