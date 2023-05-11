package com.alex.jwtauthclient.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alex.jwtauthclient.auth.AuthResult
import com.alex.jwtauthclient.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    navController.navigate(Screen.SecretScreen.route)
                }

                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "Not authorized",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is AuthResult.UknownError -> {
                    Toast.makeText(
                        context,
                        "An unknoown eror ocured",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color("#F9FAFC".toColorInt()))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Welcome to beatiful app",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Transparent),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .shadow(
                        elevation = 10.dp
                    )
                    .background(Color.Transparent)
            ) {

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.username,
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    ),
                    placeholder = { Text(text = "Type username...") },
                    onValueChange = {
                        viewModel.onEvent(
                            AuthUiEvent.UsernameChanged(it)
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color("#F9FAFC".toColorInt()),
                        unfocusedIndicatorColor = Color("#F9FAFC".toColorInt()),
                        disabledIndicatorColor = Color("#F9FAFC".toColorInt())
                    ),
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.password,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 10.dp
                    ),
                    placeholder = { Text(text = "Type password...") },
                    onValueChange = {
                        viewModel.onEvent(
                            AuthUiEvent.PasswordChanged(it)
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            var brush = Brush.horizontalGradient(
                colors = listOf(
                    Color("#41E2C0".toColorInt()),
                    Color("#6FB4D1".toColorInt())
                )
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                contentPadding = PaddingValues(),
                onClick = {
                    viewModel.onEvent(AuthUiEvent.SignIn)
                }) {
                Box(
                    modifier = Modifier
                        .background(brush)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Sign in")
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Rounded.ArrowForwardIos,
                            contentDescription = null,
                            Modifier.height(15.dp)
                        )
                    }

                }


            }
            Spacer(modifier = Modifier.height(20.dp))
            Row() {
                Text(text = "No one account?", color = Color.Gray)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Sign up",
                    modifier = Modifier.clickable {
                        viewModel.onEvent(AuthUiEvent.SignUp)

                    }
                )
            }

        }
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

//@Preview
//@Composable
//fun showAuth() {
//    AuthScreen()
//}