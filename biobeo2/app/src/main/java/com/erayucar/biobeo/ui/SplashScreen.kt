package com.erayucar.biobeo.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.erayucar.biobeo.R
import com.erayucar.biobeo.ui.theme.Pink80
import com.erayucar.biobeo.utils.Utils
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(baseNavigator: NavController) {
    val context = LocalContext.current

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 1500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        if (Utils.isLogin(context)) {
            baseNavigator.navigate(
                "home",
                navOptions = NavOptions.Builder().setPopUpTo(baseNavigator.graph.id, true).build()
            )
        } else {
            baseNavigator.navigate(
                "login",
                navOptions = NavOptions.Builder().setPopUpTo(baseNavigator.graph.id, true).build()
            )

        }
    }


    Box(
        modifier = Modifier.fillMaxSize().background(Pink80),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)        )
    }
}


