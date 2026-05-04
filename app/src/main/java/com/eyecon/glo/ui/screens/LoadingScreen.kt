package com.eyecon.glo.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.eyecon.glo.ui.components.LoadingTrafficScene
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.MutedBlue
import com.eyecon.glo.ui.theme.PaleBlue
import com.eyecon.glo.ui.theme.PrimaryBlue
import com.eyecon.glo.ui.theme.SoftBlue
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onFinished()
    }
    val transition = rememberInfiniteTransition(label = "halo")
    val pulse by transition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(1100, easing = LinearEasing), repeatMode = RepeatMode.Reverse),
        label = "pulse",
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(PaleBlue, SoftBlue, PaleBlue)),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
        ) {
            Spacer(Modifier.weight(0.4f))
            Box(
                modifier = Modifier
                    .size(280.dp)
                    .alpha(pulse)
                    .background(SoftBlue, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                LoadingTrafficScene(modifier = Modifier.size(240.dp))
            }
            Spacer(Modifier.height(36.dp))
            Text(
                "Get ready to drive",
                style = MaterialTheme.typography.headlineMedium,
                color = DeepBlue,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Loading your road rules companion",
                style = MaterialTheme.typography.bodyMedium,
                color = MutedBlue,
            )
            Spacer(Modifier.height(28.dp))
            CircularProgressIndicator(
                color = PrimaryBlue,
                trackColor = SoftBlue,
                strokeWidth = 4.dp,
                modifier = Modifier.size(38.dp),
            )
            Spacer(Modifier.weight(1f))
        }
    }
}