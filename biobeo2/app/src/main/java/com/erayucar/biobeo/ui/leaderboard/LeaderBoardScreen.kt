package com.erayucar.biobeo.ui.leaderboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.erayucar.biobeo.R
import com.erayucar.biobeo.core.data.model.LeaderBoardItem
import com.erayucar.biobeo.ui.theme.Orange
import com.erayucar.biobeo.ui.theme.Turquoise

@Composable
fun LeaderBoardScreen(
    viewModel: LeaderBoardViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {


        viewModel.getLeaderBoard()
    }

    val state = viewModel.leaderBoardList.value



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                color = Orange
            )
        } else if (state.isError) {
            Text(text = state.errorMessage ?: "An error occurred")
        } else {



            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                /*item {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .fillMaxSize()
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxHeight()
                                .wrapContentWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ){
                            Image(
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp),
                                painter = painterResource(id = R.drawable.leadeboard_user_img),
                                contentDescription = "username_image"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.wrapContentSize()) {
                                Text(text = "Username : " + state.leaderBoardList[0].username)
                                Text(text = "Score : " + state.leaderBoardList[0].score.toString())
                            }
                        }


                        Image(
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            painter = painterResource(id = R.drawable.first_user_badge),
                            contentDescription = "first user"
                        )


                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }*/
                /*item {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .fillMaxSize()
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row (modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start){
                            Image(
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp),
                                painter = painterResource(id = R.drawable.leadeboard_user_img),
                                contentDescription = "username_image",
                                contentScale = ContentScale.Inside
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.wrapContentSize()) {
                                Text(text = "Username : " + state.leaderBoardList[1].username)
                                Text(text = "Score : " + state.leaderBoardList[1].score.toString())
                            }
                        }

                        Image(
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            painter = painterResource(id = R.drawable.second_user_badge),
                            contentDescription = "second user"
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }*/
                /*item {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .fillMaxSize()
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row (modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentWidth(),
                            verticalAlignment = Alignment.CenterVertically,){
                            Image(
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp),
                                painter = painterResource(id = R.drawable.leadeboard_user_img),
                                contentDescription = "username_image"
                            )

                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.wrapContentSize()) {
                                Text(text = "Username : " + state.leaderBoardList[2].username)
                                Text(text = "Score : " + state.leaderBoardList[2].score.toString())
                            }
                        }

                        Image(
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            painter = painterResource(id = R.drawable.third_user_badge),
                            contentDescription = "third user"
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }*/

                item {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                            .fillMaxWidth()
                            .height(400.dp)
                            .background(Orange),
                        contentAlignment = Alignment.Center
                    ) {
                        TopThreeSection(
                            modifier = Modifier.wrapContentSize(),
                            first = state.leaderBoardList[0],
                            second = state.leaderBoardList[1],
                            third = state.leaderBoardList[2]
                        )


                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                items((4 until state.leaderBoardList.size).toList()) {

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .fillMaxSize()
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {

                        Image(
                            modifier = Modifier
                                .height(80.dp)
                                .width(80.dp),
                            painter = painterResource(id = R.drawable.leadeboard_user_img),
                            contentDescription = "username_image"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.wrapContentSize()) {
                            Text(text = state.leaderBoardList[it].username,
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
                                color = Turquoise)
                            Text(text = "Score : " + state.leaderBoardList[it].score.toString(),
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                                color = Turquoise)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        }
    }
}

@Composable
private fun TopThreeSection(
    modifier: Modifier,
    first: LeaderBoardItem,
    second: LeaderBoardItem,
    third: LeaderBoardItem
) {
    val userImage = painterResource(id = R.drawable.leadeboard_user_img)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Second(
            modifier = modifier,
            userImage = userImage,
            userName = second.username,
            userScore = second.score
        )
        First(
            modifier = modifier,
            userImage = userImage,
            userName = first.username,
            userScore = first.score
        )
        Third(
            modifier = modifier,
            userImage = userImage,
            userName = third.username,
            userScore = third.score
        )
    }
}

// Created for TopThree
@Composable
private fun First(modifier: Modifier, userImage: Painter, userScore: Int, userName: String) {
    Column(
        modifier = modifier
            .padding(bottom = 32.dp)
            .zIndex(2f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier
                .size(48.dp)
                .padding(bottom = 8.dp),
            painter = painterResource(id = R.drawable.first_user_badge),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = modifier.padding(bottom = 8.dp),
            text = "1",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
        ProfileImage(
            modifier = modifier,
            userImage = userImage,
            imageSize = 136.dp
        )
        Text(
            modifier = modifier.padding(top = 16.dp),
            text = userName,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Text(
            text = "Score : $userScore",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
    }
}

// Created for TopThree
@Composable
private fun Second(modifier: Modifier, userImage: Painter, userName: String, userScore: Int) {
    Column(
        modifier = modifier
            .offset(x = 32.dp)
            .zIndex(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier
                .size(48.dp)
                .padding(bottom = 8.dp),
            painter = painterResource(id = R.drawable.second_user_badge),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = modifier.padding(bottom = 8.dp),
            text = "2",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )

        ProfileImage(modifier = modifier, userImage = userImage)
        Text(
            modifier = modifier.padding(top = 16.dp),
            text = userName,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Text(
            text = "Score : $userScore",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
    }
}

// Created for TopThree
@Composable
private fun Third(modifier: Modifier, userImage: Painter, userName: String, userScore: Int) {
    Column(
        modifier = modifier
            .offset(x = (-32).dp)
            .zIndex(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier
                .size(48.dp)
                .padding(bottom = 8.dp),
            painter = painterResource(id = R.drawable.third_user_badge),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = modifier.padding(bottom = 8.dp),
            text = "3",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
        ProfileImage(modifier = modifier, userImage = userImage)
        Text(
            modifier = modifier.padding(top = 16.dp),
            text = userName,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Text(
            text = "Score : $userScore",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )
    }
}

@Composable
private fun ProfileImage(modifier: Modifier, userImage: Painter, imageSize: Dp = 112.dp) {
    Card(
        modifier = modifier
            .size(imageSize)
            .wrapContentSize()
            .shadow(
                elevation = 32.dp,
                shape = CircleShape
            ),
        shape = CircleShape,
        border = BorderStroke(4.dp, Color.White)
    ) {

        Image(painter = userImage, contentDescription = "user_image")

    }
}