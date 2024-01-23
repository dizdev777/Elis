package com.elisym.bigtise.elysiumrise.topic

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elisym.bigtise.elysiumrise.R

@Composable
fun TrueSight(herbal: NavController){
    Image(painter = painterResource(id = R.drawable.render), contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)
    val chef = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = Unit, block = {
        chef.animateTo(1f, tween(1100,0))
        herbal.navigate("Home")
    })
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.background(Color(0xFF165C1A), RoundedCornerShape(16.dp)).padding(16.dp)){
            Text(text = "Loading", letterSpacing = 3.sp,color = Color.White,
                fontSize = 27.sp)
            LinearProgressIndicator(progress = chef.value,
                modifier = Modifier.background(Color.White, RoundedCornerShape(2.dp)).size(180.dp,8.dp).clip(
                    RoundedCornerShape(2.dp)),
                trackColor = Color(0xFF578159), color = Color.White)
        }
    }
}
