package com.erayucar.biobeo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.erayucar.biobeo.ui.SplashScreen
import com.erayucar.biobeo.ui.home.HomeScreen
import com.erayucar.biobeo.ui.login.LoginScreen
import com.erayucar.biobeo.ui.maps.GoogleMapScreen
import com.erayucar.biobeo.ui.register.RegisterScreen
import com.erayucar.biobeo.utils.Utils

@Composable
fun App() {
        val baseNavigator = rememberNavController()

    NavHost(navController = baseNavigator,
        startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(baseNavigator)
        }
        composable("login") {
            LoginScreen(onComplete = {
                baseNavigator.navigate("home",navOptions = NavOptions.Builder().setPopUpTo(baseNavigator.graph.id, true).build())
            }){

                baseNavigator.navigate("register",navOptions = NavOptions.Builder().setPopUpTo("login", true).build())

            }
        }

        composable("register") {
            RegisterScreen(){

                baseNavigator.navigate("login",navOptions = NavOptions.Builder().setPopUpTo("register", true).build())

            }
        }
        composable("home") {
            HomeScreen(baseNavigator)
        }

        composable("map_screen"){
            GoogleMapScreen(baseNavigator = baseNavigator)
        }




    }

}
