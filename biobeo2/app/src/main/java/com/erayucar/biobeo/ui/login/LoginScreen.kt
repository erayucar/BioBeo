package com.erayucar.biobeo.ui.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.erayucar.biobeo.R
import com.erayucar.biobeo.core.network.dto.SignUpBody
import com.erayucar.biobeo.ui.theme.Orange
import com.erayucar.biobeo.ui.theme.Pink80
import com.erayucar.biobeo.ui.theme.Turquoise
import com.erayucar.biobeo.utils.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onComplete: () -> Unit,
    onRegister: () -> Unit

) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val showPassword = remember { mutableStateOf(false) }
    var showAuthDialog by remember { mutableStateOf(false) }
    val state = viewModel.loginUiState.value
    var isTextfieldError by remember { mutableStateOf(false) }

    if (showAuthDialog) {
        AuthDialog()
    }
    if (state.data.token.isNotEmpty()) {
        Utils.saveToken(LocalContext.current,state.data.token)
        onComplete()

    }
    if(isTextfieldError){
        ErrorDialog(
            onShowErrorDialog = { isTextfieldError = false },
            errorMessage = "Please fill the username field"
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    keyboardController?.hide()
                }

            }
            .background(
                Pink80
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier.padding(top = 50.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )



        Spacer(modifier = Modifier.height(50.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 50.dp, bottom = 10.dp),
            value = email.value,
            shape = RoundedCornerShape(40.dp),
            onValueChange = {
                email.value = it
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.account_turquas_bound),
                    contentDescription = "username icon",
                )
            },
            label = {
                Text(
                    text = "Username",
                    color = Turquoise,
                    fontFamily = FontFamily.Default,

                    )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Turquoise,
                containerColor = Color.White,
                focusedIndicatorColor = Turquoise,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions().copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next

            ),
            keyboardActions = KeyboardActions()

        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(20.dp)),
            value = password.value,
            shape = RoundedCornerShape(40.dp),
            onValueChange = {
                password.value = it
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.padlock),
                    contentDescription = "password icon",
                )
            },
            trailingIcon = {
                IconButton(onClick = { showPassword.value = !showPassword.value }
                ) {
                    if (showPassword.value)
                        Image(
                            painter = painterResource(id = R.drawable.show),
                            contentDescription = "show password icon"
                        )
                    else
                        Image(
                            painter = painterResource(id = R.drawable.hide),
                            contentDescription = "hide password icon"
                        )

                }
            },
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            label = {
                Text(
                    text = "Password",
                    color = Turquoise,
                    fontFamily = FontFamily.Default,

                    )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Turquoise,
                containerColor = Color.White,
                focusedIndicatorColor = Turquoise,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions().copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done

            ),
            singleLine = true,
            keyboardActions = KeyboardActions()

        )

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
                if (email.value.isNotEmpty()) {
                    viewModel.testAuthenticate(SignUpBody(username = email.value))
                    showAuthDialog = true
                }else
                    isTextfieldError = true
            }) {
            Text(
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                text = "Login",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Default,
            )


        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Row {
            Text(
                text = "Don't have an account?",
                color = Turquoise,
                fontFamily = FontFamily.Default,
            )
            Spacer(
                modifier = Modifier.width(5.dp)
            )
            Text(
                text = "Sign Up",
                color = Orange,
                fontFamily = FontFamily.Default,
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                ),
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {

                        onRegister()
                    }
            )


        }
    }

}


@Composable
fun GradientButton(
    text: String = "",
    textColor: Color = Color.White,
    gradient: Brush,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {

            Text(text = text, color = textColor, fontFamily = FontFamily.Monospace)
        }
    }
}


@Composable
fun AuthDialog() {

    Dialog(onDismissRequest = { }) {
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
                text = "Please wait...",
                color = Turquoise,
                fontFamily = FontFamily.Default,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(20.dp)
            )
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(20.dp)
                    .size(50.dp),
                color = Orange
            )

        }
    }


}
@Composable
fun ErrorDialog(
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

            Text(text = errorMessage,
                fontWeight = FontWeight.Bold,
                color = Turquoise)


        }
    }


}