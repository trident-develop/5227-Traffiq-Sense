package com.eyecon.glo.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eyecon.glo.data.TrafficTestsData
import com.eyecon.glo.data.TrafficTopicsData
import com.eyecon.glo.model.TestResult
import com.eyecon.glo.storage.TestResultStorage
import com.eyecon.glo.ui.screens.CompletedTest
import com.eyecon.glo.ui.screens.LearnScreen
import com.eyecon.glo.ui.screens.LessonScreen
import com.eyecon.glo.ui.screens.ResultsScreen
import com.eyecon.glo.ui.screens.SubtopicsScreen
import com.eyecon.glo.ui.screens.TestDetailScreen
import com.eyecon.glo.ui.screens.TestResultScreen
import com.eyecon.glo.ui.screens.TestsScreen
import com.eyecon.glo.ui.theme.AccentBlue
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.MutedBlue
import com.eyecon.glo.ui.theme.PaleBlue
import com.eyecon.glo.ui.theme.PrimaryBlue
import com.eyecon.glo.ui.theme.SoftBlue
import com.eyecon.glo.ui.theme.SurfaceWhite

sealed interface AppRoute {
    data object Learn : AppRoute
    data class Subtopics(val topicId: String) : AppRoute
    data class Lesson(val topicId: String, val subtopicId: String) : AppRoute
    data object Tests : AppRoute
    data class TestDetail(val testId: String) : AppRoute
    data class TestSummary(val testId: String, val timedOut: Boolean) : AppRoute
    data object Results : AppRoute
}

enum class BottomTab(val label: String, val root: AppRoute) {
    LEARN("Learn", AppRoute.Learn),
    TESTS("Tests", AppRoute.Tests),
    RESULTS("Results", AppRoute.Results),
}

@Composable
fun MainScaffold() {
    val context = LocalContext.current
    val storage = remember { TestResultStorage(context) }

    val results = remember { mutableStateMapOf<String, TestResult>() }
    LaunchedEffect(Unit) {
        results.clear()
        results.putAll(storage.getAllResults())
    }

    var currentTab by remember { mutableStateOf(BottomTab.LEARN) }
    val stacks = remember {
        mutableStateMapOf(
            BottomTab.LEARN to mutableStateOf<List<AppRoute>>(listOf(AppRoute.Learn)),
            BottomTab.TESTS to mutableStateOf<List<AppRoute>>(listOf(AppRoute.Tests)),
            BottomTab.RESULTS to mutableStateOf<List<AppRoute>>(listOf(AppRoute.Results)),
        )
    }

    fun stack() = stacks.getValue(currentTab)
    fun push(route: AppRoute) {
        stack().value = stack().value + route
    }
    fun pop() {
        val s = stack().value
        if (s.size > 1) stack().value = s.dropLast(1)
    }
    fun replaceStack(vararg routes: AppRoute) {
        stack().value = routes.toList()
    }

    BackHandler(enabled = stack().value.size > 1 || currentTab != BottomTab.LEARN) {
        if (stack().value.size > 1) {
            pop()
        } else {
            currentTab = BottomTab.LEARN
        }
    }

    val current = stack().value.last()
    val showBottomBar = current !is AppRoute.TestDetail

    Scaffold(
        containerColor = PaleBlue,
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(tween(220)) { it } + fadeIn(tween(220)),
                exit = slideOutVertically(tween(220)) { it } + fadeOut(tween(220)),
            ) {
                BottomNavBar(
                    current = currentTab,
                    onSelect = { tab ->
                        if (tab == currentTab) {
                            if (stack().value.size > 1) replaceStack(tab.root)
                        } else {
                            currentTab = tab
                        }
                    },
                )
            }
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            AnimatedContent(
                targetState = current,
                transitionSpec = {
                    (slideInHorizontally(tween(280)) { it / 6 } + fadeIn()) togetherWith
                        (slideOutHorizontally(tween(280)) { -it / 6 } + fadeOut())
                },
                label = "route",
            ) { route ->
                when (route) {
                    AppRoute.Learn -> LearnScreen(onTopicSelected = { push(AppRoute.Subtopics(it.id)) })
                    is AppRoute.Subtopics -> {
                        val topic = TrafficTopicsData.topicById(route.topicId)
                        if (topic == null) pop() else SubtopicsScreen(
                            topic = topic,
                            onSubtopicSelected = { push(AppRoute.Lesson(topic.id, it.id)) },
                            onBack = { pop() },
                        )
                    }
                    is AppRoute.Lesson -> {
                        val topic = TrafficTopicsData.topicById(route.topicId)
                        val sub = TrafficTopicsData.subtopicById(route.topicId, route.subtopicId)
                        if (topic == null || sub == null) {
                            pop()
                        } else {
                            LessonScreen(
                                topic = topic,
                                subtopic = sub,
                                onNextSubtopic = { next ->
                                    val s = stack().value
                                    stack().value = s.dropLast(1) + AppRoute.Lesson(topic.id, next.id)
                                },
                                onBackToTopics = {
                                    val s = stack().value
                                    val parentIndex = s.indexOfLast { it is AppRoute.Subtopics }
                                    if (parentIndex >= 0) {
                                        stack().value = s.subList(0, parentIndex + 1)
                                    } else {
                                        replaceStack(BottomTab.LEARN.root)
                                    }
                                },
                            )
                        }
                    }
                    AppRoute.Tests -> TestsScreen(
                        results = results,
                        onTestSelected = { push(AppRoute.TestDetail(it.id)) },
                    )
                    is AppRoute.TestDetail -> {
                        val test = TrafficTestsData.testById(route.testId)
                        if (test == null) {
                            pop()
                        } else {
                            TestDetailScreen(
                                test = test,
                                onComplete = { completed: CompletedTest ->
                                    val saved = storage.saveResult(
                                        testId = completed.testId,
                                        testTitle = completed.testTitle,
                                        score = completed.score,
                                        correct = completed.correct,
                                        total = completed.total,
                                        percentage = completed.percentage,
                                        completedAt = completed.completedAt,
                                    )
                                    results[saved.testId] = saved
                                    val s = stack().value
                                    stack().value = s.dropLast(1) +
                                        AppRoute.TestSummary(completed.testId, completed.timedOut)
                                },
                                onAbort = { pop() },
                            )
                        }
                    }
                    is AppRoute.TestSummary -> {
                        val saved = results[route.testId]
                        if (saved == null) {
                            pop()
                        } else {
                            TestResultScreen(
                                result = saved,
                                timedOut = route.timedOut,
                                onRetake = {
                                    val s = stack().value
                                    stack().value = s.dropLast(1) + AppRoute.TestDetail(saved.testId)
                                },
                                onClose = {
                                    val s = stack().value.dropLast(1)
                                    stack().value = if (s.isEmpty()) listOf(currentTab.root) else s
                                },
                            )
                        }
                    }
                    AppRoute.Results -> ResultsScreen(
                        results = results,
                        onStartTest = { test ->
                            currentTab = BottomTab.TESTS
                            stacks.getValue(BottomTab.TESTS).value =
                                listOf(AppRoute.Tests, AppRoute.TestDetail(test.id))
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavBar(
    current: BottomTab,
    onSelect: (BottomTab) -> Unit,
) {
    Surface(
        color = SurfaceWhite,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BottomTab.entries.forEach { tab ->
                NavTab(tab = tab, selected = tab == current, onClick = { onSelect(tab) })
            }
        }
    }
}

@Composable
private fun NavTab(tab: BottomTab, selected: Boolean, onClick: () -> Unit) {
    val accent = if (selected) PrimaryBlue else MutedBlue
    val pillColor = if (selected) SoftBlue else Color.Transparent
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 14.dp, vertical = 6.dp),
    ) {
        Box(
            modifier = Modifier
                .size(width = 48.dp, height = 32.dp)
                .background(pillColor, shape = RoundedCornerShape(50)),
            contentAlignment = Alignment.Center,
        ) {
            androidx.compose.foundation.Canvas(modifier = Modifier.size(20.dp)) {
                drawTabIcon(tab, accent)
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(
            tab.label,
            style = MaterialTheme.typography.labelMedium,
            color = accent,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
        )
    }
}

private fun DrawScope.drawTabIcon(tab: BottomTab, color: Color) {
    val w = size.width
    val h = size.height
    when (tab) {
        BottomTab.LEARN -> {
            drawRoundRect(
                color = color,
                topLeft = Offset(w * 0.15f, h * 0.1f),
                size = androidx.compose.ui.geometry.Size(w * 0.7f, h * 0.8f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.08f),
                style = Stroke(width = w * 0.08f),
            )
            drawLine(color, Offset(w * 0.3f, h * 0.35f), Offset(w * 0.7f, h * 0.35f), strokeWidth = w * 0.06f)
            drawLine(color, Offset(w * 0.3f, h * 0.55f), Offset(w * 0.7f, h * 0.55f), strokeWidth = w * 0.06f)
            drawLine(color, Offset(w * 0.3f, h * 0.75f), Offset(w * 0.55f, h * 0.75f), strokeWidth = w * 0.06f)
        }
        BottomTab.TESTS -> {
            drawCircle(color, radius = w * 0.4f, center = Offset(w * 0.5f, h * 0.5f), style = Stroke(width = w * 0.08f))
            val check = Path().apply {
                moveTo(w * 0.32f, h * 0.55f)
                lineTo(w * 0.45f, h * 0.7f)
                lineTo(w * 0.7f, h * 0.4f)
            }
            drawPath(check, color, style = Stroke(width = w * 0.1f))
        }
        BottomTab.RESULTS -> {
            drawLine(color, Offset(w * 0.2f, h * 0.85f), Offset(w * 0.2f, h * 0.55f), strokeWidth = w * 0.1f)
            drawLine(color, Offset(w * 0.5f, h * 0.85f), Offset(w * 0.5f, h * 0.3f), strokeWidth = w * 0.1f)
            drawLine(color, Offset(w * 0.8f, h * 0.85f), Offset(w * 0.8f, h * 0.45f), strokeWidth = w * 0.1f)
        }
    }
}

