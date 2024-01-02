package org.yellowhatpro.lesgocred

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.yellowhatpro.lesgocred.ui.MainViewModel
import org.yellowhatpro.lesgocred.ui.screens.CategoriesPage
import org.yellowhatpro.lesgocred.ui.screens.HomePage
import org.yellowhatpro.lesgocred.ui.theme.BlackBg
import org.yellowhatpro.lesgocred.ui.theme.LesgoCREDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: MainViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()
            val apiData by viewModel.apiResponse.collectAsState()
            val navController = rememberNavController()
            var isColumnLayout by rememberSaveable {
                mutableStateOf(true)
            }

            LaunchedEffect(Unit) {
                scope.launch {
                    viewModel.fetchApiData()
                    viewModel.setCategory()
                }
            }

            LesgoCREDTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BlackBg),
                    color = BlackBg
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomePage(viewModel, navController) }
                        composable("categories") {
                            CategoriesPage(apiData, viewModel, navController, isColumnLayout) {
                                isColumnLayout = !isColumnLayout
                            }
                        }
                    }
                }
            }
        }
    }
}