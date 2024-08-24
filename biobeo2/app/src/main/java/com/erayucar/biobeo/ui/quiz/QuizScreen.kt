package com.erayucar.biobeo.ui.quiz

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.erayucar.biobeo.ui.theme.Orange
import com.erayucar.biobeo.ui.theme.Pink80
import com.erayucar.biobeo.ui.theme.Turquoise

@Composable
fun QuizScreen(
    onComplete: () -> Unit
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    if (showDialog) {
        QuizScreenDialog(
            onComplete = { onComplete() },
            onShowErrorDialog = { showDialog = false },
            errorMessage = "This screen just for UI design and test in this demo version "
        )

    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink80),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = "Quiz",
                color = Turquoise,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.size(10.dp))
        }


        item {
            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            20.dp
                        )
                    )
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Orange),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = "Q.1: Mis on hild?",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Left
                )
                Text(
                    text = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Left
                )


            }
            Spacer(modifier = Modifier.size(25.dp))
        }

        item {
            QuizOptions()

        }

        item {
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
                    showDialog = true
                }) {
                Text(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    text = "Submit Answer",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Default,
                )
            }
        }

    }

}

@Composable
fun QuizOptions() {
    val options = listOf("Option A", "Option B", "Option C", "Option D")
    val selectedOption = remember { mutableStateOf("") }

    options.forEach { option ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable {
                    selectedOption.value = option
                }
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
        ) {

            RadioButton(
                selected = (selectedOption.value == option),
                onClick = { selectedOption.value = option },
                colors = RadioButtonColors(
                    selectedColor = Orange,
                    unselectedColor = Orange,
                    disabledSelectedColor = Orange,
                    disabledUnselectedColor = Orange
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = option,
                color = Turquoise,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.size(25.dp))

    }

}

@Composable
fun QuizScreenDialog(
    onComplete: () -> Unit,
    onShowErrorDialog: () -> Unit,
    errorMessage: String
) {

    Dialog(onDismissRequest = { onShowErrorDialog() }) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = errorMessage,
                fontWeight = FontWeight.Bold,
                color = Turquoise,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(text = "OK", color = Orange, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                ), modifier = Modifier
                    .wrapContentSize()
                    .padding(bottom = 30.dp, end = 30.dp)
                    .clickable {

                        onComplete()

                    })


            }
        }
    }


}
