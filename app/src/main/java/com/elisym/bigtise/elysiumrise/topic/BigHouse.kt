package com.elisym.bigtise.elysiumrise.topic

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elisym.bigtise.elysiumrise.R

@Composable
fun SweetDream(navController: NavController){
    Image(painter = painterResource(id = R.drawable.honour),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)

    val cont = LocalContext.current as Activity
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)){

            OutlinedButton(onClick = {
                                    navController.navigate("Game")
            },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF165C1A),
                )) {
                Text(text = "PLAY", modifier = Modifier.padding(4.dp),
                    fontSize = 27.sp,
                    color = Color(0xFFFDFDFD),
                    fontFamily = FontFamily(Font(R.font.robot)),
                )
            }
            OutlinedButton(onClick = {
                cont.finishAffinity()
            },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF165C1A),
                )) {
                Text(text = "EXIT", modifier = Modifier.padding(4.dp),
                    fontSize = 27.sp,
                    color = Color(0xFFFFFFFF),
                    fontFamily = FontFamily(Font(R.font.robot)),
                )
            }
        }
    }
}