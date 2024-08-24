package com.erayucar.biobeo.ui.routes

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.erayucar.biobeo.R
import com.erayucar.biobeo.ui.theme.Orange
import com.erayucar.biobeo.ui.theme.Purple
import com.erayucar.biobeo.ui.theme.Turquoise

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RoutesScreen(
    viewModel: RoutesViewModel = hiltViewModel(),
    baseNavigator : NavController
) {
    val route = remember { mutableStateOf("") }
    LaunchedEffect(Unit) {

        viewModel.getAllRoutes()
    }
    Box (modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        if (viewModel.routesScreenUiState.value.isLoading) {

            CircularProgressIndicator(
                color = Orange, modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )

        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(viewModel.routesScreenUiState.value.routes) {


                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .clickable { baseNavigator.navigate("map_screen") },
                        shape = RoundedCornerShape(20.dp),
                        colors = CardColors(
                            containerColor = Color.White,
                            contentColor = Turquoise,
                            disabledContainerColor = Color.White,
                            disabledContentColor = Purple,
                        )
                    ) {

                        Row (
                            Modifier
                                .fillMaxSize()
                                .padding(40.dp)){
                            Image(
                                painter = painterResource(id = R.drawable.distance_orange_filled),
                                contentDescription = "route",
                            )
                            Text(text = it.name)

                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

}