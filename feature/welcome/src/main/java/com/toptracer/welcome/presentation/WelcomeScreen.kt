package com.toptracer.welcome.presentation

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.toptracer.welcome.R

@Composable
fun WelcomeScreen(
    state: WelcomeState,
    onLogoutClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.loading!!) {
            CircularProgressIndicator(
                progress = 1f,
                color = Color.Blue
            )
        } else {
            Text(
                text = "Welcome, ${state.username}!",
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(30.dp))
            if (state.errorMessage == Error.ResponseError) {
                Text(
                    text = stringResource(R.string.error_message),
                    style = MaterialTheme.typography.body2,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }

            DisplayGiphy(state = state)

            Text(
                modifier = Modifier
                    .clickable {
                        onLogoutClicked()
                    }
                    .padding(top = 50.dp),
                color = Color.Blue,
                style = MaterialTheme.typography.h6,
                text = stringResource(R.string.button_logout)
            )
        }
    }

}

@Composable
fun DisplayGiphy(
    state: WelcomeState
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context)
                .data(state.giphyEntity?.data?.images?.downsizedMedium?.url)
                .apply(block = {
                    size(Size.ORIGINAL)
                }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
    )
}