package com.erayucar.biobeo.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erayucar.biobeo.R
import com.erayucar.biobeo.ui.login.ErrorDialog
import com.erayucar.biobeo.ui.theme.Orange
import com.erayucar.biobeo.ui.theme.Pink80
import com.erayucar.biobeo.ui.theme.Turquoise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onLogin: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    var emailErrMsg = remember { mutableStateOf("") }
    var isEmailValid = remember { mutableStateOf(true) }
    var isPasswordValid = remember { mutableStateOf(true) }
    var isConfirmPasswordValid = remember { mutableStateOf(true) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val showPassword = remember { mutableStateOf(false) }
    val showErrorDialog = remember{ mutableStateOf(false) }

    if (showErrorDialog.value){
        ErrorDialog(onShowErrorDialog = { showErrorDialog.value = false }, errorMessage = "This screen just for UI design and test in this demo version ")

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink80)
            .padding(15.dp)
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    keyboardController?.hide()
                }

            },
    ) {

        Text(
            modifier = Modifier.padding(top = 30.dp, start = 20.dp),
            text = "Hey, Hello!",
            style = MaterialTheme.typography.headlineMedium,
            color = Turquoise,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            fontSize = 40.sp
        )
        // Spacer(Modifier.height(5.dp))

        Spacer(Modifier.height(85.dp))


        /* Row(
             modifier = Modifier.fillMaxWidth(),
             horizontalArrangement = Arrangement.Center
         ) {

             Divider(
                 color = Color.Gray,
                 thickness = 1.dp,
                 modifier = Modifier.width(50.dp).padding(top = 10.dp)
             )
             Spacer(Modifier.height(10.dp))
             Text(
                 text = "or continue with",
                 style = MaterialTheme.typography.body1,
                 color = Color.Gray,
                 fontSize = 14.sp
             )
             Spacer(Modifier.height(10.dp))

             Divider(
                 color = Color.Gray,
                 thickness = 1.dp,
                 modifier = Modifier.width(50.dp).padding(top = 10.dp)

             )
         }*/

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = username.value,
            shape = RoundedCornerShape(40.dp),
            onValueChange = {
                username.value = it
            },
            label = {
                Text(
                    text = "Username", color = Turquoise,
                    fontFamily = FontFamily.Default,

                )
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.account_turquas_bound),
                    contentDescription = "username icon",
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Turquoise,
                containerColor = Color.White,
                focusedIndicatorColor = Turquoise,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions().copy(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Next

            ),
            keyboardActions = KeyboardActions()

        )
        Spacer(Modifier.height(5.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(40.dp),
            value = email.value,
            isError = !isEmailValid.value,
            onValueChange = {
                email.value = it
                if (it.isNotEmpty()) {
                    isEmailValid.value = false
                }
                isEmailValid.value = validateEmail(email.value)
                if (!isEmailValid.value) {
                    emailErrMsg.value = "Invalid Email"
                } else {
                    emailErrMsg.value = ""
                }
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.email_turquas),
                    contentDescription = "email icon",
                )
            },
            label = {
                Text(
                    text = "Email", color = Turquoise,
                    fontFamily = FontFamily.Default,

                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Turquoise,
                containerColor = Color.White,
                focusedIndicatorColor = Turquoise,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions().copy(
                keyboardType = KeyboardType.Email, imeAction = ImeAction.Next

            ),
            keyboardActions = KeyboardActions()

        )
        Spacer(Modifier.height(5.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(40.dp),
            value = password.value,
            isError = !isPasswordValid.value,
            onValueChange = {
                password.value = it
                if (it.isNotEmpty()) {
                    isPasswordValid.value = false
                }
                isPasswordValid.value = validatePassword(password.value)
                if (!isPasswordValid.value) {
                    // emailErrMsg.value = "Invalid Password"
                } else {
                    //emailErrMsg.value = ""
                }

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
                    text = "Password (at least 8 characters.Also 1 capital and 1 special character)",
                    color = Turquoise,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Default,

                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Turquoise,
                containerColor = Color.White,
                focusedIndicatorColor = Turquoise,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions().copy(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Next

            ),
            keyboardActions = KeyboardActions()

        )
        Spacer(Modifier.height(5.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(40.dp),
            value = confirmPassword.value,
            isError = !isConfirmPasswordValid.value,
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
            onValueChange = {
                confirmPassword.value = it
                if (it.isNotEmpty()) {
                    isConfirmPasswordValid.value = false
                }
                isConfirmPasswordValid.value = (password.value == confirmPassword.value)

                if (!isConfirmPasswordValid.value) {
                    //emailErrMsg.value = "Invalid Password"
                } else {
                    //emailErrMsg.value = ""
                }
            },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.padlock),
                    contentDescription = "password icon",
                )
            },
            label = {
                Text(
                    text = "Confirm Password", color = Turquoise,
                    fontFamily = FontFamily.Default,

                )
            },

            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Turquoise,
                containerColor = Color.White,
                focusedIndicatorColor = Turquoise,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions().copy(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done

            ),
            keyboardActions = KeyboardActions()

        )

        Spacer(Modifier.height(50.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(50.dp)),
            onClick = {
                showErrorDialog.value = true
                if (email.value.isNotEmpty() && password.value.isNotEmpty() && confirmPassword.value.isNotEmpty() && username.value.isNotEmpty()) {
                    if (isConfirmPasswordValid.value && isEmailValid.value && isPasswordValid.value) {
                        // registerUser(email.value, password.value, fullName.value)

                    }

                }
            },
            colors = ButtonColors(
                containerColor = Orange,
                contentColor = Color.White,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Gray,
            )
        ) {
            Text(
                text = "Register",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account?", color = Turquoise
            )
            Spacer(
                modifier = Modifier.width(5.dp)
            )
            Text(text = "Sign In", color = Orange, style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            ), modifier = Modifier
                .wrapContentSize()
                .clickable {

                    onLogin()

                })


        }


    }
}

fun validateEmail(email: String): Boolean {
    val emailRegex = Regex("^\\w[\\w.-]*@([\\w-]+\\.)+[\\w-]{2,4}$")
    return emailRegex.matches(email)
}

fun validatePassword(password: String): Boolean {
    val passwordRegex =
        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")
    return passwordRegex.matches(password)
}

