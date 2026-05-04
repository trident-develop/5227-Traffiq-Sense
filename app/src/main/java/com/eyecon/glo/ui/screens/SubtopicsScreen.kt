package com.eyecon.glo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eyecon.glo.model.Subtopic
import com.eyecon.glo.model.Topic
import com.eyecon.glo.ui.components.TopicIconBadge
import com.eyecon.glo.ui.theme.AccentBlue
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.MutedBlue
import com.eyecon.glo.ui.theme.SoftBlue
import com.eyecon.glo.ui.theme.SurfaceWhite

@Composable
fun SubtopicsScreen(
    topic: Topic,
    onSubtopicSelected: (Subtopic) -> Unit,
    onBack: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            BackHeader(onBack = onBack)
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                TopicIconBadge(topic.icon, size = 60.dp)
                Spacer(Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(topic.title, style = MaterialTheme.typography.headlineLarge, color = DeepBlue)
                    Spacer(Modifier.height(4.dp))
                    Text(topic.shortDescription, style = MaterialTheme.typography.bodySmall, color = MutedBlue)
                }
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(topic.subtopics, key = { it.id }) { subtopic ->
                SubtopicCard(subtopic) { onSubtopicSelected(subtopic) }
            }
            item { Spacer(Modifier.height(12.dp)) }
        }
    }
}

@Composable
private fun SubtopicCard(subtopic: Subtopic, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(SoftBlue, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    subtopic.title.first().uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    color = AccentBlue,
                )
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(subtopic.title, style = MaterialTheme.typography.titleMedium, color = DeepBlue)
                Spacer(Modifier.height(2.dp))
                Text(
                    subtopic.lesson.keyPoints.first(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MutedBlue,
                    maxLines = 2,
                )
            }
        }
    }
}

@Composable
fun BackHeader(onBack: () -> Unit, label: String = "Back") {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onBack,
            )
            .padding(vertical = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .background(SoftBlue, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text("‹", style = MaterialTheme.typography.headlineMedium, color = AccentBlue)
        }
        Spacer(Modifier.width(10.dp))
        Text(label, style = MaterialTheme.typography.titleMedium, color = AccentBlue)
    }
}
