package org.yellowhatpro.lesgocred.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.yellowhatpro.lesgocred.data.CategoryData
import org.yellowhatpro.lesgocred.data.Data
import org.yellowhatpro.lesgocred.ui.MainViewModel
import org.yellowhatpro.lesgocred.ui.screens.composables.ColumnCell
import org.yellowhatpro.lesgocred.ui.screens.composables.GridCell
import org.yellowhatpro.lesgocred.ui.screens.composables.NoInternetScreen
import org.yellowhatpro.lesgocred.ui.screens.composables.TopBar
import org.yellowhatpro.lesgocred.utils.Response

@Composable
fun CategoriesPage(
    apiData: Response<CategoryData>,
    viewModel: MainViewModel,
    navController: NavHostController,
    isColumnLayout: Boolean,
    handleColumnLayoutChange : () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (apiData.status) {
            Response.Status.LOADING -> CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .padding(40.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Response.Status.SUCCESS -> {
                val apiDataFetched = apiData.data
                apiDataFetched?.let { categoryData ->
                    CategoriesScreen(
                        data = categoryData.data,
                        isColumnLayout,
                        navController,
                        handleColumnLayoutChange
                    ) { itemId ->
                        viewModel.setCategory(itemId ?: "mint")
                        navController.navigate("home") {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            Response.Status.FAILED -> NoInternetScreen(viewModel)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriesScreen(
    data: List<Data>,
    isColumnLayout: Boolean,
    navController: NavController,
    handleColumnLayoutChange: () -> Unit,
    handleNavigateToHome: (itemId: String?) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        item {
            TopBar(
                subtitle = "explore",
                title = "CRED",
                isColumnLayout = isColumnLayout,
                handleColumnLayoutChange = handleColumnLayoutChange,
                navController = navController,
            )
        }
        items(data) { section ->
            Text(
                text = section.section_properties.title,
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            AnimatedContent(
                targetState = isColumnLayout,
                label = "transitioning between column and grid layout",
                transitionSpec = {
                    slideIn(
                        initialOffset = {
                            if (isColumnLayout) IntOffset(
                                it.width / 2,
                                -it.height / 2
                            ) else IntOffset(-it.width / 2, it.height / 2)
                        },
                        animationSpec = tween(
                            durationMillis = 350,
                            easing = LinearEasing
                        )
                    ) + fadeIn() togetherWith slideOut(
                        targetOffset = {
                            if (isColumnLayout) IntOffset(
                                -it.width / 2,
                                it.height / 2
                            ) else IntOffset(it.width / 2, -it.height / 2)
                        }
                    ) + fadeOut()

                }
            ) { isColumnLayout ->

                if (isColumnLayout) {
                    Column {
                        section.section_properties.items.forEach { item ->
                            ColumnCell(
                                modifier = Modifier
                                    .padding(bottom = 20.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        handleNavigateToHome(item.id)
                                    },
                                item = item
                            )
                        }
                    }
                } else {
                    FlowRow(
                        maxItemsInEachRow = 4,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        section.section_properties.items.forEach { item ->
                            GridCell(
                                modifier = Modifier
                                    .padding(bottom = 12.dp)
                                    .wrapContentSize()
                                    .width(80.dp)
                                    .clickable {
                                        handleNavigateToHome(item.id)
                                    },
                                item = item
                            )
                        }
                    }
                }
            }
        }
    }
}