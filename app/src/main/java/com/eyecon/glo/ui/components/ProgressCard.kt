package com.eyecon.glo.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eyecon.glo.model.TestResult
import com.eyecon.glo.ui.theme.AccentBlue
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.DividerBlue
import com.eyecon.glo.ui.theme.MutedBlue
import com.eyecon.glo.ui.theme.PrimaryBlue
import com.eyecon.glo.ui.theme.SkyBlue
import com.eyecon.glo.ui.theme.SoftBlue
import com.eyecon.glo.ui.theme.SuccessGreen
import com.eyecon.glo.ui.theme.SurfaceWhite
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ResultCard(
    title: String,
    result: TestResult?,
    onStart: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, color = DeepBlue)
            Spacer(Modifier.height(10.dp))
            if (result == null) {
                Text(
                    "Not completed yet",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MutedBlue,
                )
                Spacer(Modifier.height(10.dp))
                AnimatedProgressBar(progress = 0f, active = false)
                Spacer(Modifier.height(14.dp))
                Button(
                    onClick = onStart,
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue, contentColor = SurfaceWhite),
                    shape = RoundedCornerShape(14.dp),
                ) {
                    Text("Start test")
                }
            } else {
                ResultLine("Last score", "${result.lastScore}")
                ResultLine("Best score", "${result.bestScore}")
                ResultLine("Last percentage", "${result.lastPercentage}%")
                ResultLine("Correct answers", "${result.lastCorrect} / ${result.totalQuestions}")
                ResultLine("Completed", formatDate(result.lastCompletedAt))
                Spacer(Modifier.height(12.dp))
                AnimatedProgressBar(progress = result.lastPercentage / 100f, active = true)
                Spacer(Modifier.height(14.dp))
                Button(
                    onClick = onStart,
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue, contentColor = SurfaceWhite),
                    shape = RoundedCornerShape(14.dp),
                ) {
                    Text("Retake test")
                }
            }
        }
    }
}

@Composable
private fun ResultLine(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MutedBlue)
        Text(value, style = MaterialTheme.typography.bodyMedium, color = DeepBlue, fontWeight = FontWeight.SemiBold)
    }
    Spacer(Modifier.height(2.dp))
}

@Composable
fun AnimatedProgressBar(progress: Float, active: Boolean = true, height: Int = 12) {
    val target = remember(progress, active) { if (active) progress.coerceIn(0f, 1f) else 0f }
    val animated by animateFloatAsState(
        targetValue = target,
        animationSpec = spring(stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioLowBouncy),
        label = "progress",
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .clip(RoundedCornerShape(50))
            .background(if (active) SoftBlue else DividerBlue),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animated)
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            if (active) PrimaryBlue else DividerBlue,
                            if (active) AccentBlue else DividerBlue,
                        ),
                    ),
                ),
        )
    }
}

@Composable
fun StatBox(label: String, value: String, accent: androidx.compose.ui.graphics.Color = PrimaryBlue, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(label, style = MaterialTheme.typography.labelMedium, color = MutedBlue)
            Spacer(Modifier.height(6.dp))
            Text(value, style = MaterialTheme.typography.titleLarge, color = accent, fontWeight = FontWeight.Bold)
        }
    }
}

private fun formatDate(millis: Long): String {
    if (millis <= 0L) return "—"
    val format = SimpleDateFormat("d MMM yyyy, HH:mm", Locale.ENGLISH)
    return format.format(Date(millis))
}

val SuccessAccent: androidx.compose.ui.graphics.Color = SuccessGreen
val SkyAccent: androidx.compose.ui.graphics.Color = SkyBlue
