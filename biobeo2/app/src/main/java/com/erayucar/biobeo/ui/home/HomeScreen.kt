package com.erayucar.biobeo.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.erayucar.biobeo.R
import com.erayucar.biobeo.ui.account.AccountScreen
import com.erayucar.biobeo.ui.leaderboard.LeaderBoardScreen
import com.erayucar.biobeo.ui.routes.RoutesScreen
import com.erayucar.biobeo.ui.statistic.StatisticScreen
import com.erayucar.biobeo.ui.theme.Orange
import com.erayucar.biobeo.ui.theme.Pink80
import com.erayucar.biobeo.ui.theme.Purple
import com.erayucar.biobeo.ui.theme.Turquoise

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(baseNavigator :NavController) {
    val navigator = rememberNavController()
    val items = listOf<BottomNavItem>(
        BottomNavItem(
            "Routes",
            selectedIcon = painterResource(R.drawable.distance_orange_filled),
            unSelectedIcon = painterResource(R.drawable.distance_turquas_bounded),
            "routes"
        ),
        BottomNavItem(
            "LeaderBoard",
            selectedIcon = painterResource(R.drawable.podium_orange_filled),
            unSelectedIcon = painterResource(R.drawable.podium_turquas_bound),
            "leaderboard"
        ),
        BottomNavItem(
            "Account", selectedIcon = painterResource(R.drawable.user_orange_filled),
            unSelectedIcon = painterResource(R.drawable.account_turquas_bound),
            "account"
        ),
        BottomNavItem(
            "Statistic", selectedIcon = painterResource(R.drawable.statistics_orange_filled),
            unSelectedIcon = painterResource(R.drawable.statistics_turquas_bounded),
            "statistic"
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),

                containerColor = Color.White,
                tonalElevation = 10.dp,
            ) {

                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                items.forEachIndexed { index, it ->
                    NavigationRailItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            if (selectedItemIndex != index) {
                                selectedItemIndex = index
                                navigator.navigate(it.screen_route)
                            }

                        },
                        icon = {
                            Image(
                                painter = if (selectedItemIndex == index) it.selectedIcon else it.unSelectedIcon,
                                contentDescription = null
                            )
                        },
                        label = { Text(text = it.title, color = Orange) },
                        alwaysShowLabel = false,
                        colors = NavigationRailItemDefaults.colors(
                            selectedIconColor = Orange,
                            unselectedIconColor = Purple,
                            selectedTextColor = Turquoise,
                            unselectedTextColor = Turquoise,
                            indicatorColor = Color.Transparent

                        ),
                        modifier = Modifier.padding(start = 10.dp),

                        )
                }

            }


        },
        topBar = {
            CenterAlignedTopAppBar (
                title = {
                    Image(painter = painterResource(id = R.drawable.mini_logo),
                        contentDescription ="mini_logo",
                        modifier = Modifier.width(80.dp).height(80.dp),
                        contentScale = ContentScale.Fit
                    )
                },
                colors = TopAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White.copy(alpha = 0.8f),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White,
                ),
            )
        },
        containerColor = Pink80,
        content = {
            NavHost(
                modifier = Modifier.padding(paddingValues = it ).padding(10.dp),
                navController = navigator,
                startDestination = "routes"
            ) {
                composable("routes") {
                    RoutesScreen(baseNavigator = baseNavigator)
                }
                composable("leaderboard") {
                    LeaderBoardScreen()
                }
                composable("account") {
                    AccountScreen()
                }
                composable("statistic") {
                    StatisticScreen()
                }


            }
        })
}

