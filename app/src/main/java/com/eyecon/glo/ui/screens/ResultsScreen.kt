package com.eyecon.glo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eyecon.glo.data.TrafficTestsData
import com.eyecon.glo.model.Test
import com.eyecon.glo.model.TestResult
import com.eyecon.glo.ui.components.ResultCard
import com.eyecon.glo.ui.components.StatBox
import com.eyecon.glo.ui.theme.AccentBlue
import com.eyecon.glo.ui.theme.DangerRed
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.MutedBlue
import com.eyecon.glo.ui.theme.PrimaryBlue
import com.eyecon.glo.ui.theme.SoftBlue
import com.eyecon.glo.ui.theme.SuccessGreen

@Composable
fun ResultsScreen(
    results: Map<String, TestResult>,
    onStartTest: (Test) -> Unit,
) {
    val analytics = remember(results) { computeAnalytics(results) }

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            Text("Results", style = MaterialTheme.typography.displayMedium, color = DeepBlue)
            Spacer(Modifier.height(6.dp))
            Text(
                "Track your progress across every topic.",
                style = MaterialTheme.typography.bodyMedium,
                color = MutedBlue,
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it / 4 }),
                ) {
                    AnalyticsRow(analytics)
                }
            }
            if (results.isEmpty()) {
                item { EmptyResultsHint() }
            }
            item { Spacer(Modifier.height(4.dp)) }
            itemsIndexed(TrafficTestsData.tests, key = { _, test -> test.id }) { index, test ->
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it / (4 + index) }),
                ) {
                    ResultCard(
                        title = test.title,
                        result = results[test.id],
                        onStart = { onStartTest(test) },
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }
        }
    }
}

@Composable
private fun AnalyticsRow(analytics: ResultsAnalytics) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            StatBox(
                label = "Average",
                value = if (analytics.completed == 0) "—" else "${analytics.averagePercent}%",
                accent = AccentBlue,
                modifier = Modifier.weight(1f),
            )
            StatBox(
                label = "Tests done",
                value = "${analytics.completed} / ${analytics.total}",
                accent = PrimaryBlue,
                modifier = Modifier.weight(1f),
            )
        }
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            StatBox(
                label = "Strongest",
                value = analytics.bestTitle ?: "—",
                accent = SuccessGreen,
                modifier = Modifier.weight(1f),
            )
            StatBox(
                label = "Weakest",
                value = analytics.worstTitle ?: "—",
                accent = DangerRed,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun EmptyResultsHint() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SoftBlue),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Text(
            "No results yet. Pick a test below to get your first score.",
            style = MaterialTheme.typography.bodyLarge,
            color = DeepBlue,
            modifier = Modifier.padding(18.dp),
        )
    }
}

private data class ResultsAnalytics(
    val total: Int,
    val completed: Int,
    val averagePercent: Int,
    val bestTitle: String?,
    val worstTitle: String?,
)

private fun computeAnalytics(results: Map<String, TestResult>): ResultsAnalytics {
    val total = TrafficTestsData.tests.size
    val completed = results.size
    if (completed == 0) {
        return ResultsAnalytics(total = total, completed = 0, averagePercent = 0, bestTitle = null, worstTitle = null)
    }
    val avg = results.values.sumOf { it.bestPercentage } / completed
    val best = results.values.maxBy { it.bestPercentage }
    val worst = results.values.minBy { it.bestPercentage }
    return ResultsAnalytics(
        total = total,
        completed = completed,
        averagePercent = avg,
        bestTitle = best.testTitle.removeSuffix(" Test"),
        worstTitle = worst.testTitle.removeSuffix(" Test"),
    )
}
