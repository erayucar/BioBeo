package com.erayucar.biobeo.ui.onboarding

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.erayucar.biobeo.R
import com.erayucar.biobeo.ui.theme.Orange
import com.erayucar.biobeo.ui.theme.Pink80
import com.erayucar.biobeo.ui.theme.Turquoise
import com.erayucar.biobeo.utils.Utils
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {

    val animations = listOf(
        R.raw.welcome,
        R.raw.explore,
        R.raw.quiz,
        R.raw.leaderboard

    )
    val titles = listOf(
        "Welcome to BioBeo",
        "Explore",
        "Quiz",
        "Leaderboard"
    )

    val description = listOf(
        "Discover the world of bio economy! Engage in collaborative tasks, solve quizzes, and explore challenges in multiple languages. Perfect for students, parents, guardians, and visitors eager to learn.",
        "Explore the environmental and cultural insights at these destinations.",
        "Test your environmental knowledge with our quiz and discover how much you really know!",
        "See how you rank against other users in our leaderboard."
    )
    val pagerState = rememberPagerState(
        pageCount = { titles.size },
    )

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Pink80),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        HorizontalPager(modifier = Modifier.fillMaxSize(),state = pagerState, userScrollEnabled = true) { currentPage ->

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animations[currentPage]))

            Column(
                Modifier
                    .wrapContentSize()
                    .padding(26.dp)
                    ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                LottieAnimation(enableMergePaths = true,composition = composition,iterations = LottieConstants.IterateForever, modifier = Modifier.size(300.dp))

             /*   val context = LocalContext.current
                val imageLoader = ImageLoader.Builder(context)
                    .components {
                        if (SDK_INT >= 28) {
                            add(ImageDecoderDecoder.Factory())
                        } else {
                            add(GifDecoder.Factory())
                        }
                    }
                    .build()
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context).data(data = R.drawable.animationlearn).apply(block = {
                            size(Size.ORIGINAL)
                        }).build(), imageLoader = imageLoader
                    ),
                    contentDescription = null,
                    modifier = Modifier.wrapContentSize()
                )*/
                Text(
                    text = titles[currentPage],

                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Turquoise,
                    fontWeight = FontWeight.Bold,

                    )
                Text(
                    text = description[currentPage],
                    modifier = Modifier.padding(top = 24.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    letterSpacing = TextUnit(
                        0.15f,
                        TextUnitType.Sp
                    ),
                    color = Turquoise,
                    fontWeight = FontWeight.Normal,

                    )
                PageIndicator(
                    pagerCount = titles.size,
                    currentPage = pagerState.currentPage,

                    modifier = Modifier.padding(top = 60.dp)
                )
                Column (Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom){
                    ButtonSection(pagerState = pagerState, navController = navController)

                }
            }

        }



    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonSection(pagerState: PagerState, navController: NavController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        if (pagerState.currentPage != 3 && pagerState.currentPage != 0) {

            Text(
                text = "Back",
                color = Orange,
                fontFamily = FontFamily.Default,
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                ),
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)

                        }

                    }
            )

            Text(
                text = "Next",
                color = Orange,
                fontFamily = FontFamily.Default,
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                ),
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {

                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)

                        }

                    }
            )
        } else if (pagerState.currentPage == 0) {

            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                Text(
                    text = "Next",
                    color = Orange,
                    fontFamily = FontFamily.Default,
                    style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    ),
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            scope.launch {
                                Utils.onBoardingFinished(context, true)
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)

                            }
                        }
                )
            }


        } else {

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
                    navController.navigate(
                        "login",
                        navOptions = NavOptions.Builder().setPopUpTo(navController.graph.id, true)
                            .build()
                    )
                }) {
                Text(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    text = "Get Started",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Default,
                )


            }
        }
    }
}

@Composable
fun PageIndicator(pagerCount: Int, currentPage: Int, modifier: Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {

        repeat(pagerCount) {
            IndicatorSingle(isSelected = it == currentPage)
        }

    }
}

@Composable
fun IndicatorSingle(isSelected: Boolean) {

    val width = animateDpAsState(targetValue = if (isSelected) 28.dp else 15.dp, label = "")
    Box(
        modifier = Modifier
            .height(15.dp)
            .width(width.value)
            .padding(2.dp)
            .background(
                color = if (isSelected) Orange else Orange.copy(alpha = 0.5f),
                shape = CircleShape
            )



    )
}
