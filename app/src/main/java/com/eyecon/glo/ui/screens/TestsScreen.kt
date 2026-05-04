package com.eyecon.glo.ui.screens

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eyecon.glo.data.TrafficTestsData
import com.eyecon.glo.data.TrafficTopicsData
import com.eyecon.glo.model.Test
import com.eyecon.glo.model.TestResult
import com.eyecon.glo.ui.components.TopicIconBadge
import com.eyecon.glo.ui.theme.AccentBlue
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.MutedBlue
import com.eyecon.glo.ui.theme.SoftBlue
import com.eyecon.glo.ui.theme.SuccessGreen
import com.eyecon.glo.ui.theme.SurfaceWhite

@Composable
fun TestsScreen(
    results: Map<String, TestResult>,
    onTestSelected: (Test) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            Text(
                "Tests",
                style = MaterialTheme.typography.displayMedium,
                color = DeepBlue,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Each test takes 5 minutes and has 20 questions.",
                style = MaterialTheme.typography.bodyMedium,
                color = MutedBlue,
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(TrafficTestsData.tests, key = { it.id }) { test ->
                val topic = TrafficTopicsData.topicById(test.topicId)
                val result = results[test.id]
                TestRow(test = test, topic = topic, result = result) { onTestSelected(test) }
            }
            item { Spacer(Modifier.height(12.dp)) }
        }
    }
}

@Composable
private fun TestRow(
    test: Test,
    topic: com.eyecon.glo.model.Topic?,
    result: TestResult?,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 6.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (topic != null) {
                TopicIconBadge(topic.icon, size = 52.dp)
                Spacer(Modifier.width(14.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(test.title, style = MaterialTheme.typography.titleMedium, color = DeepBlue)
                Spacer(Modifier.height(2.dp))
                Text(
                    "${test.questions.size} questions · 5:00 timer",
                    style = MaterialTheme.typography.bodySmall,
                    color = MutedBlue,
                )
            }
            if (result != null) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .background(SoftBlue, shape = CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        "${result.bestPercentage}%",
                        style = MaterialTheme.typography.labelLarge,
                        color = if (result.bestPercentage >= 70) SuccessGreen else AccentBlue,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
