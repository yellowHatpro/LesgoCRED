package org.yellowhatpro.lesgocred.ui.screens.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.yellowhatpro.lesgocred.R

@Composable
fun AnimatedToggleSwitch(
    isColumnLayout: Boolean,
    handleColumnLayoutChange: () -> Unit) {

    val painterRes = if (isColumnLayout) R.drawable.column_on else R.drawable.grid_on

    AnimatedContent(
        targetState = painterRes,
        transitionSpec = {
            fadeIn(animationSpec = tween(250)) togetherWith
                    fadeOut(animationSpec = tween(250))
        },
        label = "toggle switch for grid and column layouts",
        modifier = Modifier
            .padding(horizontal = 10.dp)) { targetState ->
        Image(
            painter = painterResource(id = targetState),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .clickable { handleColumnLayoutChange() },
        )
    }
}