package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.intranet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import pe.edu.ulima.pm.demoextrasapp.R
import pe.edu.ulima.pm.demoextrasapp.presentation.ui.viewModels.LibraryViewModel

@Composable
fun IntranetScreen(
    libraryViewModel: LibraryViewModel,
    onLoginClick: () -> Unit
) {

    var usuarioTextValue by remember { mutableStateOf("") }
    var passwordTextValue by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 20.dp, 20.dp, 20.dp)
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.estrella),
                contentDescription = "carlos",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
            )

            Text(
                text = "ULIMA",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                fontSize = 50.sp,
                modifier = Modifier.padding(bottom = 25.dp),
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .background(colorResource(id = R.color.universidad_de_lima))
                    .padding(4.dp)
                    .width(300.dp)

            ) {
                TextField(
                    value = usuarioTextValue,
                    onValueChange = { usuarioTextValue = it },
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFFFFFFF)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    label = {
                        Text(
                            "Usuario",
                            textAlign = TextAlign.Center,
                            color = Color.LightGray,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                        )
                    }
                )

                TextField(
                    value = passwordTextValue,
                    onValueChange = { passwordTextValue = it },
                    shape = RoundedCornerShape(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFFFFFFF)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    label = {
                        Text(
                            "Contraseña",
                            textAlign = TextAlign.Center,
                            color = Color.LightGray,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                        )
                    }
                )

                Button(
                    onClick = onLoginClick,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .height(50.dp),

                    content = {
                        Text(
                            "INGRESAR",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            style = MaterialTheme.typography.body1
                        )
                    }
                )
            }
        }
    }
}