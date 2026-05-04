package com.eyecon.glo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eyecon.glo.data.TrafficTopicsData
import com.eyecon.glo.model.Topic
import com.eyecon.glo.ui.components.TopicCard
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.MutedBlue

@Composable
fun LearnScreen(onTopicSelected: (Topic) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            Text(
                "Learn",
                style = MaterialTheme.typography.displayMedium,
                color = DeepBlue,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Pick a topic and explore the rules at your own pace.",
                style = MaterialTheme.typography.bodyMedium,
                color = MutedBlue,
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(TrafficTopicsData.topics, key = { it.id }) { topic ->
                TopicCard(topic = topic, onClick = { onTopicSelected(topic) })
            }
            item { Spacer(Modifier.height(12.dp)) }
        }
    }
}
