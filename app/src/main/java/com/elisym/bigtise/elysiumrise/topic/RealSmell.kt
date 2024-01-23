package com.elisym.bigtise.elysiumrise.topic

import android.view.MotionEvent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elisym.bigtise.elysiumrise.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RealSmell(navController: NavController, score: MutableState<Int>){
    Image(painter = painterResource(id = R.drawable.agenda),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)
    val considerable = remember{
        listOf(
            R.drawable.violent,
            R.drawable.fascination,
            R.drawable.fascinate,
            R.drawable.amaze,
            R.drawable.blonde,
            R.drawable.relief
        )
    }

    val inconsiderable = remember{
        (0..1000).map { considerable.random() }.toList()
    }
      val multiple = LocalDensity.current.density
    val lessen = rememberCoroutineScope()
    val landmark = remember{
        mutableStateOf(30)
    }
    LaunchedEffect(key1 = Unit, block = {
        score.value = 0
    })
    LaunchedEffect(key1 = Unit, block = {
        while (landmark.value>0){
            delay(1000)
            landmark.value--
        }
        navController.navigate("encourage")
    })
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        val solid = rememberLazyListState()
        val herb = remember{
            mutableStateOf(2900f)
        }

        LaunchedEffect(key1 = Unit, block = {
            while (true) {
                solid.animateScrollBy(
                    200f * multiple,
                    tween(herb.value.toInt(), 0, LinearEasing)
                )
                if(herb.value>1700)
                herb.value*=0.93f

            }
        })
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            state = solid,
            reverseLayout = true,
            userScrollEnabled = false
            ){
            item {
                Spacer(modifier = Modifier.size(400.dp))
            }
            items(inconsiderable) {
                val favourite = remember{
                    Animatable(80f)
                }
                val steel = remember{
                    (-150..150).random().dp
                }
                val penalty = remember{
                    mutableStateOf(0f)
                }
                val matter = animateFloatAsState(targetValue =penalty.value, tween(300))
                LaunchedEffect(key1 = penalty.value, block = {
                    delay(300)
                    penalty.value = 0f
                })
                val conclusion = remember{
                    mutableStateOf(true)
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), contentAlignment = Alignment.Center) {
                    if(favourite.value<180f)
                    Image(painter = painterResource(id = it),
                        contentDescription = "",
                        modifier = Modifier
                            .offset(steel, 0.dp)
                            .rotate(matter.value)
                            .size(favourite.value.dp)
                            .pointerInteropFilter {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        if (conclusion.value) {
                                            lessen.launch {
                                                if (penalty.value >= 0)
                                                    penalty.value = -(penalty.value + 10)
                                                else
                                                    penalty.value = -(penalty.value - 10)

                                                favourite.animateTo(favourite.value * 1.1f)
                                                if (favourite.value > 180f) {
                                                    score.value++
                                                    herb.value *= 0.98f
                                                    conclusion.value = false
                                                }

                                            }
                                        }
                                    }


                                }
                                true
                            })
                }
            }
        }

        Row(modifier = Modifier
            .align(Alignment.TopCenter)
            .fillMaxWidth()
            .height(64.dp)
            .background(
                Color(
                    0xFF1B4E10
                )
            )
            .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24) , contentDescription = "",
                    tint = Color.White)
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "SCORE: ${score.value}", modifier = Modifier.padding(4.dp),
                fontSize = 19.sp,
                color = Color(0xFFEBEBEB),
                fontFamily = FontFamily(Font(R.font.robot)),
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "TIME: ${landmark.value}", modifier = Modifier.padding(4.dp),
                fontSize = 19.sp,
                color = Color(0xFFEBEBEB),
                fontFamily = FontFamily(Font(R.font.robot)),
            )
            
        }



    }

}