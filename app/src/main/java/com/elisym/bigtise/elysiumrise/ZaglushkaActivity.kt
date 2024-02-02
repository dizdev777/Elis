package com.elisym.bigtise.elysiumrise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elisym.bigtise.elysiumrise.topic.RealSmell
import com.elisym.bigtise.elysiumrise.topic.SweetDream
import com.elisym.bigtise.elysiumrise.topic.TrueSight
import com.elisym.bigtise.elysiumrise.wave.theme.ElysiumRiseTheme
import com.onesignal.OneSignal

class ZaglushkaActivity : ComponentActivity() {
    override fun onCreate(customs: Bundle?) {
        super.onCreate(customs)
        val group = Punitive(false)
        group.pipe()
        group.restraint()
       OneSignal.User.pushSubscription.optOut()
        setContent {
            ElysiumRiseTheme {
                val edge = remember{
                    mutableStateOf(0)
                }
                val preserve = rememberNavController()
                NavHost(navController = preserve, startDestination = "handsome"){
                    composable("handsome"){
                            TrueSight(preserve)
                    }
                    composable("Game"){
                        RealSmell(preserve,edge)
                    }
                    composable("Home"){
                        SweetDream(navController = preserve)
                    }
                    composable("encourage"){
                        Image(painter = painterResource(id = R.drawable.agenda),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop)
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.background(Color(0xFF165C1A), RoundedCornerShape(16.dp)).padding(16.dp)){
                                Text(text = "Game over!", letterSpacing = 3.sp,color = Color.White,
                                    fontSize = 27.sp)
                                Text(text = "Score: ${edge.value}", letterSpacing = 3.sp,color = Color.White,
                                    fontSize = 27.sp)
                                OutlinedButton(onClick = {
                                 preserve.navigate("Game"){
                                 popUpTo(0)}
                                },
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = Color(0xFF165C1A),
                                    )) {
                                    Text(text = "Back", modifier = Modifier.padding(4.dp),
                                        fontSize = 22.sp,
                                        color = Color(0xFFFFFFFF),
                                        fontFamily = FontFamily(Font(R.font.robot)),
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}
