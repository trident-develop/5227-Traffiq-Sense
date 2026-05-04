package com.eyecon.glo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eyecon.glo.model.TestResult
import com.eyecon.glo.ui.theme.AccentBlue
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.DividerBlue
import com.eyecon.glo.ui.theme.InkBlue
import com.eyecon.glo.ui.theme.MutedBlue
import com.eyecon.glo.ui.theme.PrimaryBlue
import com.eyecon.glo.ui.theme.SoftBlue
import com.eyecon.glo.ui.theme.SuccessGreen
import com.eyecon.glo.ui.theme.SurfaceWhite
import com.eyecon.glo.ui.theme.WarningAmber

@Composable
fun TestResultScreen(
    result: TestResult,
    timedOut: Boolean,
    onRetake: () -> Unit,
    onClose: () -> Unit,
) {
    val percent = result.lastPercentage
    val accentColor = when {
        percent >= 85 -> SuccessGreen
        percent >= 60 -> AccentBlue
        percent >= 40 -> WarningAmber
        else -> com.eyecon.glo.ui.theme.DangerRed
    }
    val feedback = when {
        timedOut && percent < 50 -> "Time ran out before you could finish. Take a moment to revisit the topic."
        percent >= 90 -> "Excellent. You clearly know this topic."
        percent >= 75 -> "Great work. A short review and you will master it."
        percent >= 60 -> "Good effort. Focus on the areas that tripped you up."
        percent >= 40 -> "Keep practising. Re-read the lessons and try again."
        else -> "This topic deserves more time. Start with the lesson, then retry."
    }

    var visible by remember(result.testId) { mutableStateOf(false) }
    LaunchedEffect(result.testId) { visible = true }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(8.dp))
        Text("Result", style = MaterialTheme.typography.titleMedium, color = MutedBlue)
        Spacer(Modifier.height(2.dp))
        Text(result.testTitle, style = MaterialTheme.typography.headlineMedium, color = DeepBlue)
        Spacer(Modifier.height(20.dp))

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow)) +
                scaleIn(initialScale = 0.85f),
        ) {
            PercentDial(percent = percent, accent = accentColor)
        }

        Spacer(Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                ResultStat("Score", "${result.lastScore}")
                ResultStat("Correct answers", "${result.lastCorrect} / ${result.totalQuestions}")
                ResultStat("Percentage", "$percent%")
                ResultStat("Best score so far", "${result.bestScore}")
                if (timedOut) {
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Timer ran out – the result was saved automatically.",
                        style = MaterialTheme.typography.bodySmall,
                        color = WarningAmber,
                    )
                }
            }
        }

        Spacer(Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SoftBlue),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        ) {
            Text(
                feedback,
                style = MaterialTheme.typography.bodyLarge,
                color = InkBlue,
                modifier = Modifier.padding(16.dp),
            )
        }

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            OutlinedButton(
                onClick = onClose,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(14.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, SolidColor(AccentBlue)),
            ) { Text("Close", color = AccentBlue) }
            Button(
                onClick = onRetake,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue, contentColor = SurfaceWhite),
            ) { Text("Retake test") }
        }
    }
}

@Composable
private fun PercentDial(percent: Int, accent: Color) {
    val animated by animateFloatAsState(
        targetValue = percent / 100f,
        animationSpec = spring(stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioLowBouncy),
        label = "dial",
    )
    Box(
        modifier = Modifier.size(180.dp),
        contentAlignment = Alignment.Center,
    ) {
        androidx.compose.foundation.Canvas(modifier = Modifier.size(180.dp)) {
            drawDial(animated, accent)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("$percent%", style = MaterialTheme.typography.displayLarge, color = accent, fontWeight = FontWeight.Bold)
            Text("score", style = MaterialTheme.typography.labelMedium, color = MutedBlue)
        }
    }
}

private fun DrawScope.drawDial(progress: Float, accent: Color) {
    val w = size.width
    val stroke = w * 0.08f
    val pad = stroke / 2f
    drawArc(
        color = DividerBlue,
        startAngle = -90f,
        sweepAngle = 360f,
        useCenter = false,
        topLeft = Offset(pad, pad),
        size = Size(w - stroke, w - stroke),
        style = Stroke(width = stroke),
    )
    drawArc(
        brush = Brush.sweepGradient(listOf(accent, accent.copy(alpha = 0.7f), accent)),
        startAngle = -90f,
        sweepAngle = 360f * progress,
        useCenter = false,
        topLeft = Offset(pad, pad),
        size = Size(w - stroke, w - stroke),
        style = Stroke(width = stroke),
    )
}

@Composable
private fun ResultStat(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MutedBlue)
        Text(value, style = MaterialTheme.typography.titleMedium, color = DeepBlue, fontWeight = FontWeight.SemiBold)
    }
}
