package org.yellowhatpro.lesgocred.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import org.yellowhatpro.lesgocred.R
import org.yellowhatpro.lesgocred.ui.MainViewModel
import org.yellowhatpro.lesgocred.ui.screens.composables.HomeButton
import org.yellowhatpro.lesgocred.ui.screens.composables.NoInternetScreen
import org.yellowhatpro.lesgocred.ui.theme.Circa
import org.yellowhatpro.lesgocred.ui.theme.Gilroy
import org.yellowhatpro.lesgocred.utils.Response

@Composable
fun HomePage(viewModel: MainViewModel,
             navController: NavHostController) {

    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Surface {
        Column(
            modifier = Modifier
                .padding(40.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (selectedCategory.status) {
                Response.Status.LOADING -> CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(40.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Response.Status.FAILED -> NoInternetScreen(viewModel)

                Response.Status.SUCCESS -> {
                    val selectedCategoryData = selectedCategory.data?.display_data
                    selectedCategoryData?.let { displayData ->
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            AsyncImage(
                                model = displayData.icon_url,
                                contentDescription = "category image",
                                modifier = Modifier
                                    .padding(vertical = 90.dp)
                                    .size(120.dp)
                            )
                            Text(text = "CRED " + displayData.name,
                                fontSize = 20.sp)
                            Text(
                                text = displayData.description,
                                fontFamily = Circa,
                                fontSize = 22.sp,
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                            )

                            HomeButton(onClick = {
                                navController.navigate("categories")
                            }) {
                                Text(
                                    text = "Go to Category ",
                                    fontFamily = Gilroy,
                                    fontSize = 12.sp
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_right),
                                    contentDescription = "Go To Categories"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}