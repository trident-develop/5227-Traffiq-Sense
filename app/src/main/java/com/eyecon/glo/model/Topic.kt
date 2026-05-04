package com.eyecon.glo.model

data class Topic(
    val id: String,
    val title: String,
    val shortDescription: String,
    val icon: TopicIcon,
    val subtopics: List<Subtopic>,
)

enum class TopicIcon {
    ROAD_SIGN,
    TRAFFIC_LIGHT,
    PRIORITY,
    SPEED,
    PARKING,
    PEDESTRIAN,
    LANE,
    OVERTAKE,
    INTERSECTION,
    EMERGENCY,
    DISTANCE,
    WEATHER,
}
