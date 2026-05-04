package com.eyecon.glo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.eyecon.glo.model.Subtopic
import com.eyecon.glo.model.Topic
import com.eyecon.glo.ui.components.IllustrationView
import com.eyecon.glo.ui.theme.AccentBlue
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.InkBlue
import com.eyecon.glo.ui.theme.MutedBlue
import com.eyecon.glo.ui.theme.PrimaryBlue
import com.eyecon.glo.ui.theme.SoftBlue
import com.eyecon.glo.ui.theme.SurfaceWhite

@Composable
fun LessonScreen(
    topic: Topic,
    subtopic: Subtopic,
    onNextSubtopic: (Subtopic) -> Unit,
    onBackToTopics: () -> Unit,
) {
    val nextSubtopic = remember(topic, subtopic) {
        val idx = topic.subtopics.indexOfFirst { it.id == subtopic.id }
        if (idx in 0 until topic.subtopics.size - 1) topic.subtopics[idx + 1] else null
    }

    var visible by remember(subtopic.id) { mutableStateOf(false) }
    LaunchedEffect(subtopic.id) { visible = true }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            BackHeader(onBack = onBackToTopics, label = topic.title)
            Spacer(Modifier.height(12.dp))
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it / 4 }),
            ) {
                Text(
                    subtopic.title,
                    style = MaterialTheme.typography.displayMedium,
                    color = DeepBlue,
                )
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item {
                IllustrationView(
                    kind = subtopic.lesson.illustration,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                )
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                ) {
                    Text(
                        subtopic.lesson.explanation,
                        style = MaterialTheme.typography.bodyLarge,
                        color = InkBlue,
                        modifier = Modifier.padding(18.dp),
                    )
                }
            }
            item {
                Text(
                    "Key points",
                    style = MaterialTheme.typography.titleMedium,
                    color = DeepBlue,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            items(count = subtopic.lesson.keyPoints.size) { index ->
                KeyPointRow(index + 1, subtopic.lesson.keyPoints[index])
            }
            item { Spacer(Modifier.height(8.dp)) }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    OutlinedButton(
                        onClick = onBackToTopics,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, SolidColor(AccentBlue)),
                    ) {
                        Text("Back to topics", color = AccentBlue)
                    }
                    if (nextSubtopic != null) {
                        Button(
                            onClick = { onNextSubtopic(nextSubtopic) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue, contentColor = SurfaceWhite),
                        ) {
                            Text("Next subtopic")
                        }
                    }
                }
            }
            item { Spacer(Modifier.height(20.dp)) }
        }
    }
}

@Composable
private fun KeyPointRow(index: Int, text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(SoftBlue, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(index.toString(), style = MaterialTheme.typography.labelLarge, color = AccentBlue)
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text,
                style = MaterialTheme.typography.bodyMedium,
                color = MutedBlue,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
