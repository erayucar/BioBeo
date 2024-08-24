package com.erayucar.biobeo.ui.maps

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.erayucar.biobeo.R
import com.erayucar.biobeo.ui.theme.Orange
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GoogleMapScreen(
    viewModel: GoogleMapViewModel = hiltViewModel(),
    baseNavigator: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.getPOI()
        viewModel.getCurrentLocation()
        viewModel.testLocations()

    }

    var showDialog by remember { mutableStateOf(false) }
    val currentLocationState by viewModel.currentLocationState
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Standard", "Satellite", "Hybrid")
    val properties = remember {
        mutableStateOf(
            MapProperties(
                isMyLocationEnabled = true,
                mapType = MapType.NORMAL
            )
        )
    }




    when (tabIndex) {
        0 -> properties.value = MapProperties(
            isMyLocationEnabled = true,
            mapType = MapType.NORMAL
        )

        1 -> properties.value = MapProperties(
            isMyLocationEnabled = true,
            mapType = MapType.SATELLITE
        )

        2 -> properties.value = MapProperties(
            isMyLocationEnabled = true,
            mapType = MapType.HYBRID
        )
    }



    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .wrapContentSize()

            ) {

                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    title = {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {

                            Text(
                                modifier = Modifier.padding(top = 20.dp, end = 20.dp),
                                text = "Target Map",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Center

                            )
                            TabRow(
                                selectedTabIndex = tabIndex,
                                containerColor = Color.Transparent
                            ) {
                                tabs.forEachIndexed { index, title ->
                                    Tab(
                                        modifier = Modifier.background(Color.Transparent),
                                        text = { Text(title) },
                                        selected = tabIndex == index,
                                        onClick = { tabIndex = index },
                                        selectedContentColor = Color.White,
                                        unselectedContentColor = Color.Gray,
                                    )
                                }
                            }
                        }

                    },
                    navigationIcon = {

                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = { baseNavigator.popBackStack() }) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                                    contentDescription = "back",
                                )
                            }

                        }

                    },
                    colors = TopAppBarColors(
                        containerColor = Orange,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        scrolledContainerColor = Orange,
                        titleContentColor = Color.White
                    )
                )
            }

        },
        modifier = Modifier.fillMaxSize()

    ) { paddingValues ->

        if (showDialog) {
            QuizDialog(onShow = { showDialog = false }) {
                baseNavigator.navigate("quiz")

            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {


            if (viewModel.poiListUiState.value.isLoading && currentLocationState.isLoading) {
                CircularProgressIndicator(

                )
            } else if (viewModel.poiListUiState.value.isError || currentLocationState.isError) {

                Text(text = "Error", color = Color.Red)
            } else {

                MapScreen(properties = properties.value) {
                    showDialog = true
                }


                /* viewModel.poiListUiState.value.POIs.forEach { POI ->

                  Circle(
                      center = LatLng(
                          POI.latitude?.toDouble() ?: 0.0,
                          POI.longitude?.toDouble() ?: 0.0
                      ),
                      radius = CIRCLE_RADIUS,
                      onClick = {
                          if (viewModel.isLocationInsideCircle(
                                  POI.latitude?.toDouble() ?: 0.0,
                                  POI.longitude?.toDouble() ?: 0.0,
                                  currentLocation.latitude,
                                  currentLocation.longitude,
                                  CIRCLE_RADIUS
                              )
                          ) {
                              showdialog = true
                          }
                      },
                      clickable = true
                  )


                }

                Polyline(points = viewModel.poiListUiState.value.POIs.map {
                  LatLng(it.latitude?.toDouble() ?: 0.0, it.longitude?.toDouble() ?: 0.0)
                })*/
            }
        }
    }
}


@Composable
fun MapScreen(
    viewModel: GoogleMapViewModel = hiltViewModel(),
    properties: MapProperties,
    onClick: () -> Unit
) {
    val currentLocationState by viewModel.currentLocationState

    val cameraState = rememberCameraPositionState()
    LaunchedEffect(Unit) {

        cameraState.centerOnLocation(
            /* LatLng(
                 poiListUiState.POIs.get(0).latitude?.toDouble() ?: 0.0,
                 poiListUiState.POIs.get(0).longitude?.toDouble() ?: 0.0
             )*/
            viewModel.testLocations.value[0]
        )
    }
    GoogleMap(
        modifier = Modifier.padding(),
        properties = properties,
        cameraPositionState = cameraState,

        )
    {


        Polygon(
            points = viewModel.calculateTrianglePoint(
                viewModel.testLocations.value[0],
                radius = TRIANGLE_RADIUS
            ),
            fillColor = Color.Transparent,
            strokeColor = Color.Black,
            clickable = true,
            onClick = {
                if (viewModel.isLocationInsideTriangle(

                        location = currentLocationState.currentLocation,
                        center = viewModel.testLocations.value[0],
                        radius = TRIANGLE_RADIUS
                    )
                ) {

                    onClick()
                }


            }
        )
        for (i in 1 until viewModel.testLocations.value.size) {
            if (i == viewModel.testLocations.value.size - 1) {

                Circle(
                    center = viewModel.testLocations.value[i],
                    radius = MINI_CIRCLE_RADIUS,

                    )
                Circle(
                    center = viewModel.testLocations.value[i],
                    radius = CIRCLE_RADIUS,
                    clickable = true,
                    onClick = {
                        if (viewModel.isLocationInsideCircle(
                                viewModel.testLocations.value[i],
                                currentLocationState.currentLocation,
                                CIRCLE_RADIUS
                            )
                        ) {

                            onClick()
                        }


                    }
                )


            } else {

                Circle(
                    center = viewModel.testLocations.value[i],
                    radius = CIRCLE_RADIUS,
                    clickable = true,
                    onClick = {
                        if (viewModel.isLocationInsideCircle(
                                viewModel.testLocations.value[i],
                                currentLocationState.currentLocation,
                                CIRCLE_RADIUS
                            )
                        ) {

                            onClick()
                        }


                    })
            }
        }


        Polyline(
            points = viewModel.testLocations.value,
        )

    }
}

@Composable
fun QuizDialog(
    onShow: () -> Unit,
    onComplete: () -> Unit

) {


    Dialog(onDismissRequest = { onShow() }) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "You have a quiz in this point of interest.Good Luck!",
                modifier = Modifier.padding(top = 20.dp),
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "POI Details:", modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                Text(text = "Title: ", modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                Text(text = "Location: ", modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(colors = ButtonColors(
                containerColor = Orange,
                contentColor = Color.White,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Gray,
            ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .padding(start = 30.dp, end = 30.dp)
                    .background(Color.Transparent),
                onClick = {
                    onComplete()
                }) {
                Text(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    text = "SOLVE THE QUIZ",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Default,
                )


            }

        }
    }


}


private suspend fun CameraPositionState.centerOnLocation(
    location: LatLng
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        location,
        18f
    ),
    durationMs = 1500
)


const val MINI_CIRCLE_RADIUS = 28.0
const val CIRCLE_RADIUS = 35.0
const val TRIANGLE_RADIUS = 0.00060

