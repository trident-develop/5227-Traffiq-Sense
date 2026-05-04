package com.eyecon.glo.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eyecon.glo.data.TrafficTestsData
import com.eyecon.glo.model.Question
import com.eyecon.glo.model.Test
import com.eyecon.glo.ui.components.AnimatedProgressBar
import com.eyecon.glo.ui.theme.AccentBlue
import com.eyecon.glo.ui.theme.DangerRed
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.DividerBlue
import com.eyecon.glo.ui.theme.InkBlue
import com.eyecon.glo.ui.theme.MutedBlue
import com.eyecon.glo.ui.theme.PrimaryBlue
import com.eyecon.glo.ui.theme.SoftBlue
import com.eyecon.glo.ui.theme.SuccessGreen
import com.eyecon.glo.ui.theme.SurfaceWhite
import com.eyecon.glo.ui.theme.WarningAmber
import kotlinx.coroutines.delay

data class CompletedTest(
    val testId: String,
    val testTitle: String,
    val correct: Int,
    val total: Int,
    val score: Int,
    val percentage: Int,
    val completedAt: Long,
    val timedOut: Boolean,
)

@Composable
fun TestDetailScreen(
    test: Test,
    onComplete: (CompletedTest) -> Unit,
    onAbort: () -> Unit,
) {
    val questions = remember(test.id) { test.questions.shuffled() }
    var currentIndex by remember(test.id) { mutableIntStateOf(0) }
    var selectedIndex by remember(test.id) { mutableStateOf<Int?>(null) }
    var correctCount by remember(test.id) { mutableIntStateOf(0) }
    var secondsRemaining by remember(test.id) { mutableIntStateOf(TrafficTestsData.TIMER_SECONDS) }
    var finished by remember(test.id) { mutableStateOf(false) }
    var showQuit by remember { mutableStateOf(false) }

    LaunchedEffect(test.id) {
        while (secondsRemaining > 0 && !finished) {
            delay(1000)
            secondsRemaining -= 1
        }
        if (!finished) {
            finished = true
            val total = questions.size
            val percentage = (correctCount * 100 / total).coerceIn(0, 100)
            onComplete(
                CompletedTest(
                    testId = test.id,
                    testTitle = test.title,
                    correct = correctCount,
                    total = total,
                    score = correctCount * 5,
                    percentage = percentage,
                    completedAt = System.currentTimeMillis(),
                    timedOut = true,
                ),
            )
        }
    }

    BackHandler(enabled = !finished) { showQuit = true }

    if (showQuit) {
        AlertDialog(
            onDismissRequest = { showQuit = false },
            confirmButton = {
                TextButton(onClick = {
                    showQuit = false
                    finished = true
                    onAbort()
                }) { Text("Quit", color = DangerRed) }
            },
            dismissButton = {
                TextButton(onClick = { showQuit = false }) { Text("Continue") }
            },
            title = { Text("Leave the test?") },
            text = { Text("Your progress will not be saved unless the timer runs out.") },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        TestTopBar(
            title = test.title,
            secondsRemaining = secondsRemaining,
            onBack = { showQuit = true },
        )
        Spacer(Modifier.height(14.dp))

        val total = questions.size
        val progress = (currentIndex.toFloat() + (if (selectedIndex != null) 1f else 0f)) / total

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Question ${currentIndex + 1} / $total", style = MaterialTheme.typography.labelLarge, color = MutedBlue)
            Text(
                if (correctCount == 0) "Stay focused" else "Correct: $correctCount",
                style = MaterialTheme.typography.labelLarge,
                color = if (correctCount > 0) SuccessGreen else MutedBlue,
            )
        }
        Spacer(Modifier.height(8.dp))
        AnimatedProgressBar(progress = progress, height = 8)
        Spacer(Modifier.height(16.dp))

        AnimatedContent(
            targetState = currentIndex,
            transitionSpec = {
                (slideInHorizontally(animationSpec = tween(280)) { it / 4 } + fadeIn()) togetherWith
                    (slideOutHorizontally(animationSpec = tween(280)) { -it / 4 } + fadeOut())
            },
            label = "question",
            modifier = Modifier.weight(1f),
        ) { index ->
            val q = questions[index]
            QuestionView(
                question = q,
                selectedIndex = selectedIndex,
                onSelect = { picked ->
                    if (selectedIndex == null) {
                        selectedIndex = picked
                        if (picked == q.correctIndex) correctCount += 1
                    }
                },
            )
        }

        Spacer(Modifier.height(12.dp))
        val isLast = currentIndex == questions.size - 1
        Button(
            onClick = {
                if (selectedIndex == null) return@Button
                if (isLast) {
                    if (!finished) {
                        finished = true
                        val percentage = (correctCount * 100 / total).coerceIn(0, 100)
                        onComplete(
                            CompletedTest(
                                testId = test.id,
                                testTitle = test.title,
                                correct = correctCount,
                                total = total,
                                score = correctCount * 5,
                                percentage = percentage,
                                completedAt = System.currentTimeMillis(),
                                timedOut = false,
                            ),
                        )
                    }
                } else {
                    currentIndex += 1
                    selectedIndex = null
                }
            },
            enabled = selectedIndex != null,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue,
                contentColor = SurfaceWhite,
                disabledContainerColor = DividerBlue,
                disabledContentColor = MutedBlue,
            ),
        ) {
            Text(if (isLast) "Finish test" else "Next question")
        }
    }
}

@Composable
private fun TestTopBar(title: String, secondsRemaining: Int, onBack: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .background(SoftBlue, shape = CircleShape)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onBack,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text("‹", style = MaterialTheme.typography.headlineMedium, color = AccentBlue)
        }
        Spacer(Modifier.width(10.dp))
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            color = DeepBlue,
            modifier = Modifier.weight(1f),
        )
        TimerChip(secondsRemaining)
    }
}

@Composable
private fun TimerChip(seconds: Int) {
    val mins = seconds / 60
    val secs = seconds % 60
    val low = seconds < 30
    Card(
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = if (low) DangerRed.copy(alpha = 0.12f) else SoftBlue),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(if (low) DangerRed else AccentBlue, shape = CircleShape),
            )
            Spacer(Modifier.width(8.dp))
            Text(
                "%d:%02d".format(mins, secs),
                style = MaterialTheme.typography.titleSmall,
                color = if (low) DangerRed else AccentBlue,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun QuestionView(
    question: Question,
    selectedIndex: Int?,
    onSelect: (Int) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            Text(
                question.text,
                style = MaterialTheme.typography.titleLarge,
                color = InkBlue,
                modifier = Modifier.padding(20.dp),
            )
        }
        Spacer(Modifier.height(14.dp))
        question.options.forEachIndexed { i, option ->
            OptionRow(
                index = i,
                text = option,
                state = when {
                    selectedIndex == null -> OptionState.Idle
                    i == question.correctIndex && selectedIndex != null -> OptionState.Correct
                    i == selectedIndex -> OptionState.Wrong
                    else -> OptionState.Disabled
                },
                onClick = { onSelect(i) },
            )
            Spacer(Modifier.height(10.dp))
        }
    }
}

private enum class OptionState { Idle, Correct, Wrong, Disabled }

@Composable
private fun OptionRow(index: Int, text: String, state: OptionState, onClick: () -> Unit) {
    val border = when (state) {
        OptionState.Idle -> DividerBlue
        OptionState.Correct -> SuccessGreen
        OptionState.Wrong -> DangerRed
        OptionState.Disabled -> DividerBlue
    }
    val tint = when (state) {
        OptionState.Idle -> SurfaceWhite
        OptionState.Correct -> SuccessGreen.copy(alpha = 0.08f)
        OptionState.Wrong -> DangerRed.copy(alpha = 0.08f)
        OptionState.Disabled -> SurfaceWhite
    }
    val letter = ('A' + index).toString()
    Card(
        onClick = onClick,
        enabled = state == OptionState.Idle,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = tint),
        border = BorderStroke(1.dp, border),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        when (state) {
                            OptionState.Correct -> SuccessGreen
                            OptionState.Wrong -> DangerRed
                            else -> SoftBlue
                        },
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    letter,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (state == OptionState.Correct || state == OptionState.Wrong) SurfaceWhite else AccentBlue,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(text, style = MaterialTheme.typography.bodyLarge, color = InkBlue, modifier = Modifier.weight(1f))
            if (state == OptionState.Correct) {
                Text("✓", color = SuccessGreen, style = MaterialTheme.typography.titleMedium)
            } else if (state == OptionState.Wrong) {
                Text("✕", color = DangerRed, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

