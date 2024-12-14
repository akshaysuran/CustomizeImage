package com.example.playingwithimageincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.playingwithimageincompose.ui.theme.PlayingWithImageInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayingWithImageInComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    ContentScaleComposable(
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                    ClipAnImage(modifier = Modifier.padding(innerPadding))
                    //  borderComposable(modifier = Modifier.padding(innerPadding))
                    ImageWithBlendMode(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ContentScaleComposable(modifier: Modifier = Modifier) {
    val imageModifier = modifier
        .size(300.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Yellow)

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.graphics_source_image_land),
            contentDescription = stringResource(id = R.string.dog_content_description),
            contentScale = ContentScale.Fit, // we can apply different contentScale based on how we want show the image
            modifier = imageModifier)
    }
}

/**
 * Test Different type of Clip like CircleShape or RoundedCornerShape or CircleShape or SquashedOval
 * */
@Composable
fun ClipAnImage(modifier: Modifier) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.graphics_source_image_small),
            contentDescription = stringResource(id = R.string.dog_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(SquashedOval())
        )
    }
}

/**
 * Add Border to an Image composable using borderStroke through color or provide brush of paint
 * */
@Composable
fun borderComposable(modifier: Modifier) {
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }

    val borderWidth = 4.dp
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.graphics_source_image_small),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp)
                .border(
                    BorderStroke(borderWidth, rainbowColorsBrush),
                    CircleShape
                )
                .padding(borderWidth)
                .clip(CircleShape)
        )
    }
}


@Composable
fun ImageWithBlendMode(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            modifier = modifier,
            painter = painterResource(id = R.drawable.graphics_source_image_land),
            contentDescription = stringResource(id = R.string.dog_content_description),
            colorFilter = ColorFilter.tint(Color.Green, blendMode = BlendMode.Dst)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlayingWithImageInComposeTheme {
        //Greeting()
    }
}