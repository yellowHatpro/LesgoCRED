package org.yellowhatpro.lesgocred.ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.yellowhatpro.lesgocred.data.Item
import org.yellowhatpro.lesgocred.ui.theme.Typography

@Composable
fun ColumnCell(
    modifier: Modifier = Modifier,
    item: Item
) {
    Row(
        modifier = modifier
            .padding(horizontal = 5.dp)
       ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        ImageHolder(
            imageUrl = item.display_data.icon_url,
            contentDescription = "Category item in Column layout"
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Text(text = item.display_data.name, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.display_data.description,
                fontSize = 11.sp,
                color = Color.Gray,
                style = Typography.labelSmall,
            )
        }
    }
}
