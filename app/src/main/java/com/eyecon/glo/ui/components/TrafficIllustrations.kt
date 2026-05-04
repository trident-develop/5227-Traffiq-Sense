package com.eyecon.glo.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eyecon.glo.model.IllustrationKind
import com.eyecon.glo.model.TopicIcon
import com.eyecon.glo.ui.theme.AccentBlue
import com.eyecon.glo.ui.theme.DangerRed
import com.eyecon.glo.ui.theme.DeepBlue
import com.eyecon.glo.ui.theme.PrimaryBlue
import com.eyecon.glo.ui.theme.SkyBlue
import com.eyecon.glo.ui.theme.SoftBlue
import com.eyecon.glo.ui.theme.SuccessGreen
import com.eyecon.glo.ui.theme.WarningAmber

@Composable
fun TopicIconBadge(
    icon: TopicIcon,
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
) {
    Surface(
        modifier = modifier.size(size),
        shape = CircleShape,
        color = SoftBlue,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(size * 0.6f)) {
                when (icon) {
                    TopicIcon.ROAD_SIGN -> drawRoadSignIcon()
                    TopicIcon.TRAFFIC_LIGHT -> drawTrafficLightIcon()
                    TopicIcon.PRIORITY -> drawPriorityIcon()
                    TopicIcon.SPEED -> drawSpeedIcon()
                    TopicIcon.PARKING -> drawParkingIcon()
                    TopicIcon.PEDESTRIAN -> drawPedestrianIcon()
                    TopicIcon.LANE -> drawLaneIcon()
                    TopicIcon.OVERTAKE -> drawOvertakeIcon()
                    TopicIcon.INTERSECTION -> drawIntersectionIcon()
                    TopicIcon.EMERGENCY -> drawEmergencyIcon()
                    TopicIcon.DISTANCE -> drawDistanceIcon()
                    TopicIcon.WEATHER -> drawWeatherIcon()
                }
            }
        }
    }
}

private fun DrawScope.drawRoadSignIcon() {
    val w = size.width
    val h = size.height
    val path = Path().apply {
        moveTo(w / 2, 0f)
        lineTo(w, h)
        lineTo(0f, h)
        close()
    }
    drawPath(path, color = WarningAmber)
    drawPath(path, color = DeepBlue, style = Stroke(width = w * 0.06f))
}

private fun DrawScope.drawTrafficLightIcon() {
    val w = size.width
    val h = size.height
    drawRoundRect(
        color = DeepBlue,
        topLeft = Offset(w * 0.3f, 0f),
        size = Size(w * 0.4f, h),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.06f),
    )
    drawCircle(DangerRed, radius = w * 0.09f, center = Offset(w * 0.5f, h * 0.22f))
    drawCircle(WarningAmber, radius = w * 0.09f, center = Offset(w * 0.5f, h * 0.5f))
    drawCircle(SuccessGreen, radius = w * 0.09f, center = Offset(w * 0.5f, h * 0.78f))
}

private fun DrawScope.drawPriorityIcon() {
    val w = size.width
    val h = size.height
    val cx = w / 2
    val cy = h / 2
    val r = w * 0.45f
    val path = Path().apply {
        moveTo(cx, cy - r)
        lineTo(cx + r, cy)
        lineTo(cx, cy + r)
        lineTo(cx - r, cy)
        close()
    }
    drawPath(path, color = WarningAmber)
    drawPath(path, color = DeepBlue, style = Stroke(width = w * 0.05f))
}

private fun DrawScope.drawSpeedIcon() {
    val w = size.width
    val h = size.height
    drawCircle(Color.White, radius = w * 0.45f, center = Offset(w / 2, h / 2))
    drawCircle(DangerRed, radius = w * 0.45f, center = Offset(w / 2, h / 2), style = Stroke(width = w * 0.08f))
}

private fun DrawScope.drawParkingIcon() {
    val w = size.width
    val h = size.height
    drawRoundRect(
        color = PrimaryBlue,
        topLeft = Offset(w * 0.1f, h * 0.05f),
        size = Size(w * 0.8f, h * 0.9f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.1f),
    )
    drawRoundRect(
        color = Color.White,
        topLeft = Offset(w * 0.32f, h * 0.25f),
        size = Size(w * 0.18f, h * 0.5f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.04f),
    )
    drawRoundRect(
        color = Color.White,
        topLeft = Offset(w * 0.32f, h * 0.25f),
        size = Size(w * 0.36f, h * 0.22f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.04f),
    )
}

private fun DrawScope.drawPedestrianIcon() {
    val w = size.width
    val h = size.height
    drawCircle(DeepBlue, radius = w * 0.08f, center = Offset(w * 0.5f, h * 0.18f))
    drawLine(DeepBlue, Offset(w * 0.5f, h * 0.28f), Offset(w * 0.5f, h * 0.6f), strokeWidth = w * 0.07f)
    drawLine(DeepBlue, Offset(w * 0.5f, h * 0.6f), Offset(w * 0.32f, h * 0.95f), strokeWidth = w * 0.07f)
    drawLine(DeepBlue, Offset(w * 0.5f, h * 0.6f), Offset(w * 0.7f, h * 0.95f), strokeWidth = w * 0.07f)
    drawLine(DeepBlue, Offset(w * 0.5f, h * 0.4f), Offset(w * 0.78f, h * 0.55f), strokeWidth = w * 0.07f)
    drawLine(DeepBlue, Offset(w * 0.5f, h * 0.4f), Offset(w * 0.25f, h * 0.55f), strokeWidth = w * 0.07f)
}

private fun DrawScope.drawLaneIcon() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.1f), size = Size(w, h * 0.8f))
    drawLine(Color.White, Offset(w * 0.5f, h * 0.15f), Offset(w * 0.5f, h * 0.35f), strokeWidth = w * 0.05f)
    drawLine(Color.White, Offset(w * 0.5f, h * 0.45f), Offset(w * 0.5f, h * 0.65f), strokeWidth = w * 0.05f)
    drawLine(Color.White, Offset(w * 0.5f, h * 0.75f), Offset(w * 0.5f, h * 0.95f), strokeWidth = w * 0.05f)
}

private fun DrawScope.drawOvertakeIcon() {
    val w = size.width
    val h = size.height
    drawRoundRect(
        color = AccentBlue,
        topLeft = Offset(w * 0.15f, h * 0.55f),
        size = Size(w * 0.35f, h * 0.3f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.05f),
    )
    drawRoundRect(
        color = PrimaryBlue,
        topLeft = Offset(w * 0.5f, h * 0.15f),
        size = Size(w * 0.35f, h * 0.3f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.05f),
    )
}

private fun DrawScope.drawIntersectionIcon() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.4f), size = Size(w, h * 0.2f))
    drawRect(DeepBlue, topLeft = Offset(w * 0.4f, 0f), size = Size(w * 0.2f, h))
    drawLine(Color.White, Offset(w * 0.1f, h * 0.5f), Offset(w * 0.35f, h * 0.5f), strokeWidth = w * 0.04f)
    drawLine(Color.White, Offset(w * 0.65f, h * 0.5f), Offset(w * 0.9f, h * 0.5f), strokeWidth = w * 0.04f)
    drawLine(Color.White, Offset(w * 0.5f, h * 0.1f), Offset(w * 0.5f, h * 0.35f), strokeWidth = w * 0.04f)
    drawLine(Color.White, Offset(w * 0.5f, h * 0.65f), Offset(w * 0.5f, h * 0.9f), strokeWidth = w * 0.04f)
}

private fun DrawScope.drawEmergencyIcon() {
    val w = size.width
    val h = size.height
    drawRoundRect(
        color = DangerRed,
        topLeft = Offset(w * 0.1f, h * 0.3f),
        size = Size(w * 0.8f, h * 0.45f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.06f),
    )
    drawCircle(DeepBlue, radius = w * 0.08f, center = Offset(w * 0.3f, h * 0.78f))
    drawCircle(DeepBlue, radius = w * 0.08f, center = Offset(w * 0.7f, h * 0.78f))
    drawRect(Color.White, topLeft = Offset(w * 0.45f, h * 0.4f), size = Size(w * 0.1f, h * 0.25f))
    drawRect(Color.White, topLeft = Offset(w * 0.37f, h * 0.48f), size = Size(w * 0.26f, h * 0.09f))
}

private fun DrawScope.drawDistanceIcon() {
    val w = size.width
    val h = size.height
    drawRoundRect(
        color = AccentBlue,
        topLeft = Offset(w * 0.05f, h * 0.55f),
        size = Size(w * 0.3f, h * 0.25f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.04f),
    )
    drawRoundRect(
        color = PrimaryBlue,
        topLeft = Offset(w * 0.65f, h * 0.55f),
        size = Size(w * 0.3f, h * 0.25f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.04f),
    )
    val arrow = Path().apply {
        moveTo(w * 0.4f, h * 0.7f)
        lineTo(w * 0.6f, h * 0.7f)
    }
    drawPath(arrow, DeepBlue, style = Stroke(width = w * 0.04f))
    drawLine(DeepBlue, Offset(w * 0.55f, h * 0.65f), Offset(w * 0.6f, h * 0.7f), strokeWidth = w * 0.04f)
    drawLine(DeepBlue, Offset(w * 0.55f, h * 0.75f), Offset(w * 0.6f, h * 0.7f), strokeWidth = w * 0.04f)
    drawLine(DeepBlue, Offset(w * 0.45f, h * 0.65f), Offset(w * 0.4f, h * 0.7f), strokeWidth = w * 0.04f)
    drawLine(DeepBlue, Offset(w * 0.45f, h * 0.75f), Offset(w * 0.4f, h * 0.7f), strokeWidth = w * 0.04f)
}

private fun DrawScope.drawWeatherIcon() {
    val w = size.width
    val h = size.height
    drawCircle(Color.White, radius = w * 0.18f, center = Offset(w * 0.4f, h * 0.4f))
    drawCircle(Color.White, radius = w * 0.22f, center = Offset(w * 0.6f, h * 0.42f))
    drawCircle(Color.White, radius = w * 0.18f, center = Offset(w * 0.78f, h * 0.45f))
    drawLine(SkyBlue, Offset(w * 0.3f, h * 0.7f), Offset(w * 0.25f, h * 0.9f), strokeWidth = w * 0.04f)
    drawLine(SkyBlue, Offset(w * 0.5f, h * 0.7f), Offset(w * 0.45f, h * 0.95f), strokeWidth = w * 0.04f)
    drawLine(SkyBlue, Offset(w * 0.7f, h * 0.7f), Offset(w * 0.65f, h * 0.95f), strokeWidth = w * 0.04f)
}

@Composable
fun IllustrationView(
    kind: IllustrationKind,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = SoftBlue,
        tonalElevation = 0.dp,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(220.dp)) {
                drawIllustration(kind)
            }
        }
    }
}

private fun DrawScope.drawIllustration(kind: IllustrationKind) {
    when (kind) {
        IllustrationKind.WARNING_SIGN -> drawTriangleSign(WarningAmber, "!")
        IllustrationKind.PRIORITY_SIGN -> drawDiamondSign()
        IllustrationKind.PROHIBITION_SIGN -> drawCircleSign(DangerRed, withSlash = true)
        IllustrationKind.MANDATORY_SIGN -> drawCircleSign(PrimaryBlue, withArrow = true)
        IllustrationKind.INFORMATION_SIGN -> drawRectSign(PrimaryBlue, "i")
        IllustrationKind.TEMPORARY_SIGN -> drawTriangleSign(WarningAmber, label = "")
        IllustrationKind.TRAFFIC_LIGHT_RED -> drawTrafficLight(activeIndex = 0)
        IllustrationKind.TRAFFIC_LIGHT_YELLOW -> drawTrafficLight(activeIndex = 1)
        IllustrationKind.TRAFFIC_LIGHT_GREEN -> drawTrafficLight(activeIndex = 2)
        IllustrationKind.TRAFFIC_LIGHT_FLASHING -> drawTrafficLight(activeIndex = 1, dim = false)
        IllustrationKind.PEDESTRIAN_LIGHT -> drawPedestrianLight()
        IllustrationKind.OFFICER -> drawOfficer()
        IllustrationKind.MAIN_ROAD -> drawDiamondSign()
        IllustrationKind.GIVE_WAY -> drawTriangleSign(Color.White, "▽", border = DangerRed)
        IllustrationKind.ROUNDABOUT -> drawRoundabout()
        IllustrationKind.INTERSECTION -> drawIntersection()
        IllustrationKind.PUBLIC_TRANSPORT -> drawBus()
        IllustrationKind.SPEED_CITY -> drawSpeedSign(50)
        IllustrationKind.SPEED_HIGHWAY -> drawSpeedSign(120)
        IllustrationKind.SPEED_SCHOOL -> drawSpeedSign(30)
        IllustrationKind.SPEED_RESIDENTIAL -> drawSpeedSign(20)
        IllustrationKind.SPEED_WEATHER -> drawSpeedSign(80)
        IllustrationKind.SPEED_SIGN -> drawSpeedSign(70)
        IllustrationKind.NO_PARKING -> drawCircleSign(PrimaryBlue, withSlash = true, fillCenter = PrimaryBlue, slashColor = DangerRed)
        IllustrationKind.NO_STOPPING -> drawCircleSign(PrimaryBlue, withCross = true, fillCenter = PrimaryBlue, slashColor = DangerRed)
        IllustrationKind.PARK_CROSSWALK -> drawZebra()
        IllustrationKind.PARK_HILL -> drawHill()
        IllustrationKind.PARK_PARALLEL -> drawParallelPark()
        IllustrationKind.PARK_DISABLED -> drawDisabledSign()
        IllustrationKind.ZEBRA -> drawZebra()
        IllustrationKind.LIGHT_CROSSING -> drawZebraWithLight()
        IllustrationKind.SCHOOL_CROSSING -> drawZebra()
        IllustrationKind.PEDESTRIAN_PRIORITY -> drawZebra()
        IllustrationKind.CYCLIST_CROSSING -> drawCyclistCrossing()
        IllustrationKind.UNSAFE_CROSSING -> drawZebra(parkedCar = true)
        IllustrationKind.LANE_MARKINGS -> drawLanes(broken = true, solid = true)
        IllustrationKind.LANE_CHANGE -> drawLaneChange()
        IllustrationKind.BUS_LANE -> drawBusLane()
        IllustrationKind.TURN_LANE -> drawTurnLane()
        IllustrationKind.LANE_ARROWS -> drawLaneArrows()
        IllustrationKind.SOLID_BROKEN -> drawLanes(broken = true, solid = true)
        IllustrationKind.OVERTAKE_SAFE -> drawOvertake()
        IllustrationKind.OVERTAKE_FORBIDDEN -> drawOvertake(forbidden = true)
        IllustrationKind.OVERTAKE_CYCLIST -> drawOvertakeCyclist()
        IllustrationKind.OVERTAKE_HILL -> drawHill()
        IllustrationKind.OVERTAKE_CROSSING -> drawZebra(parkedCar = true)
        IllustrationKind.OVERTAKE_RETURN -> drawOvertake()
        IllustrationKind.INT_CONTROLLED -> drawIntersection(withLight = true)
        IllustrationKind.INT_UNCONTROLLED -> drawIntersection()
        IllustrationKind.TURN_LEFT -> drawTurn(left = true)
        IllustrationKind.TURN_RIGHT -> drawTurn(left = false)
        IllustrationKind.STOP_LINE -> drawStopLine()
        IllustrationKind.AMBULANCE -> drawAmbulance(DangerRed)
        IllustrationKind.POLICE -> drawAmbulance(PrimaryBlue)
        IllustrationKind.FIRE_TRUCK -> drawAmbulance(DangerRed, ladder = true)
        IllustrationKind.GIVE_WAY_EMERGENCY -> drawTriangleSign(Color.White, "▽", border = DangerRed)
        IllustrationKind.SIRENS -> drawSirens()
        IllustrationKind.SAFE_REACTION -> drawAmbulance(DangerRed)
        IllustrationKind.TWO_SECOND -> drawTwoSecond()
        IllustrationKind.BRAKING -> drawBraking()
        IllustrationKind.WET_DISTANCE -> drawWeatherDrop()
        IllustrationKind.NIGHT_DISTANCE -> drawNight()
        IllustrationKind.TRUCK_DISTANCE -> drawTruck()
        IllustrationKind.TAILGATING -> drawTailgate()
        IllustrationKind.RAIN -> drawRain()
        IllustrationKind.FOG -> drawFog()
        IllustrationKind.SNOW -> drawSnow()
        IllustrationKind.ICE -> drawIce()
        IllustrationKind.LOW_VISIBILITY -> drawFog()
        IllustrationKind.AQUAPLANING -> drawWeatherDrop()
    }
}

private fun DrawScope.drawTriangleSign(
    fill: Color,
    label: String = "!",
    border: Color = DangerRed,
) {
    val w = size.width
    val h = size.height
    val path = Path().apply {
        moveTo(w / 2, h * 0.1f)
        lineTo(w * 0.9f, h * 0.85f)
        lineTo(w * 0.1f, h * 0.85f)
        close()
    }
    drawPath(path, fill)
    drawPath(path, border, style = Stroke(width = w * 0.05f))
    if (label == "!") {
        drawRect(DeepBlue, topLeft = Offset(w * 0.47f, h * 0.35f), size = Size(w * 0.06f, h * 0.28f))
        drawCircle(DeepBlue, radius = w * 0.04f, center = Offset(w * 0.5f, h * 0.72f))
    }
}

private fun DrawScope.drawDiamondSign() {
    val w = size.width
    val h = size.height
    val cx = w / 2
    val cy = h / 2
    val path = Path().apply {
        moveTo(cx, cy - h * 0.4f)
        lineTo(cx + w * 0.4f, cy)
        lineTo(cx, cy + h * 0.4f)
        lineTo(cx - w * 0.4f, cy)
        close()
    }
    drawPath(path, WarningAmber)
    val inner = Path().apply {
        moveTo(cx, cy - h * 0.32f)
        lineTo(cx + w * 0.32f, cy)
        lineTo(cx, cy + h * 0.32f)
        lineTo(cx - w * 0.32f, cy)
        close()
    }
    drawPath(inner, Color.White, style = Stroke(width = w * 0.02f))
    drawPath(path, DeepBlue, style = Stroke(width = w * 0.04f))
}

private fun DrawScope.drawCircleSign(
    border: Color,
    withSlash: Boolean = false,
    withArrow: Boolean = false,
    withCross: Boolean = false,
    fillCenter: Color = Color.White,
    slashColor: Color = DangerRed,
) {
    val w = size.width
    val h = size.height
    val cx = w / 2
    val cy = h / 2
    val r = minOf(w, h) * 0.4f
    drawCircle(fillCenter, r, center = Offset(cx, cy))
    drawCircle(border, r, center = Offset(cx, cy), style = Stroke(width = w * 0.07f))
    if (withSlash) {
        drawLine(slashColor, Offset(cx - r * 0.7f, cy + r * 0.7f), Offset(cx + r * 0.7f, cy - r * 0.7f), strokeWidth = w * 0.05f)
    }
    if (withCross) {
        drawLine(slashColor, Offset(cx - r * 0.7f, cy + r * 0.7f), Offset(cx + r * 0.7f, cy - r * 0.7f), strokeWidth = w * 0.05f)
        drawLine(slashColor, Offset(cx - r * 0.7f, cy - r * 0.7f), Offset(cx + r * 0.7f, cy + r * 0.7f), strokeWidth = w * 0.05f)
    }
    if (withArrow) {
        drawLine(Color.White, Offset(cx, cy + r * 0.4f), Offset(cx, cy - r * 0.4f), strokeWidth = w * 0.06f)
        drawLine(Color.White, Offset(cx, cy - r * 0.4f), Offset(cx - r * 0.2f, cy - r * 0.2f), strokeWidth = w * 0.06f)
        drawLine(Color.White, Offset(cx, cy - r * 0.4f), Offset(cx + r * 0.2f, cy - r * 0.2f), strokeWidth = w * 0.06f)
    }
}

private fun DrawScope.drawRectSign(fill: Color, label: String) {
    val w = size.width
    val h = size.height
    drawRoundRect(
        color = fill,
        topLeft = Offset(w * 0.15f, h * 0.25f),
        size = Size(w * 0.7f, h * 0.5f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.05f),
    )
    drawCircle(Color.White, radius = w * 0.05f, center = Offset(w * 0.5f, h * 0.42f))
    drawRect(Color.White, topLeft = Offset(w * 0.47f, h * 0.5f), size = Size(w * 0.06f, h * 0.18f))
}

private fun DrawScope.drawTrafficLight(activeIndex: Int, dim: Boolean = true) {
    val w = size.width
    val h = size.height
    drawRoundRect(
        color = DeepBlue,
        topLeft = Offset(w * 0.35f, h * 0.1f),
        size = Size(w * 0.3f, h * 0.7f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.04f),
    )
    drawRect(DeepBlue, topLeft = Offset(w * 0.48f, h * 0.8f), size = Size(w * 0.04f, h * 0.15f))
    val r = w * 0.07f
    val centers = listOf(
        Offset(w * 0.5f, h * 0.25f),
        Offset(w * 0.5f, h * 0.45f),
        Offset(w * 0.5f, h * 0.65f),
    )
    val colors = listOf(DangerRed, WarningAmber, SuccessGreen)
    centers.forEachIndexed { i, c ->
        val active = i == activeIndex
        val color = if (active) colors[i] else if (dim) colors[i].copy(alpha = 0.18f) else colors[i].copy(alpha = 0.4f)
        drawCircle(color, r, c)
    }
}

private fun DrawScope.drawPedestrianLight() {
    val w = size.width
    val h = size.height
    drawRoundRect(
        color = DeepBlue,
        topLeft = Offset(w * 0.3f, h * 0.15f),
        size = Size(w * 0.4f, h * 0.55f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.04f),
    )
    drawCircle(DangerRed.copy(alpha = 0.3f), radius = w * 0.07f, center = Offset(w * 0.5f, h * 0.28f))
    drawCircle(SuccessGreen, radius = w * 0.07f, center = Offset(w * 0.5f, h * 0.55f))
}

private fun DrawScope.drawOfficer() {
    val w = size.width
    val h = size.height
    drawCircle(DeepBlue, radius = w * 0.07f, center = Offset(w * 0.5f, h * 0.18f))
    drawLine(DeepBlue, Offset(w * 0.5f, h * 0.25f), Offset(w * 0.5f, h * 0.6f), strokeWidth = w * 0.06f)
    drawLine(DeepBlue, Offset(w * 0.5f, h * 0.6f), Offset(w * 0.35f, h * 0.92f), strokeWidth = w * 0.06f)
    drawLine(DeepBlue, Offset(w * 0.5f, h * 0.6f), Offset(w * 0.65f, h * 0.92f), strokeWidth = w * 0.06f)
    drawLine(DeepBlue, Offset(w * 0.5f, h * 0.32f), Offset(w * 0.85f, h * 0.18f), strokeWidth = w * 0.06f)
    drawLine(DeepBlue, Offset(w * 0.5f, h * 0.32f), Offset(w * 0.2f, h * 0.45f), strokeWidth = w * 0.06f)
}

private fun DrawScope.drawRoundabout() {
    val w = size.width
    val h = size.height
    drawCircle(SkyBlue, radius = w * 0.32f, center = Offset(w * 0.5f, h * 0.5f))
    drawCircle(SoftBlue, radius = w * 0.16f, center = Offset(w * 0.5f, h * 0.5f))
    drawCircle(DeepBlue, radius = w * 0.32f, center = Offset(w * 0.5f, h * 0.5f), style = Stroke(width = w * 0.02f))
    val arrow = Path().apply {
        moveTo(w * 0.55f, h * 0.3f)
        lineTo(w * 0.62f, h * 0.32f)
        lineTo(w * 0.6f, h * 0.4f)
    }
    drawPath(arrow, DeepBlue, style = Stroke(width = w * 0.02f))
}

private fun DrawScope.drawIntersection(withLight: Boolean = false) {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.4f), size = Size(w, h * 0.2f))
    drawRect(DeepBlue, topLeft = Offset(w * 0.4f, 0f), size = Size(w * 0.2f, h))
    val dashEffect = PathEffect.dashPathEffect(floatArrayOf(w * 0.04f, w * 0.04f))
    drawLine(Color.White, Offset(0f, h * 0.5f), Offset(w * 0.4f, h * 0.5f), strokeWidth = w * 0.01f, pathEffect = dashEffect)
    drawLine(Color.White, Offset(w * 0.6f, h * 0.5f), Offset(w, h * 0.5f), strokeWidth = w * 0.01f, pathEffect = dashEffect)
    drawLine(Color.White, Offset(w * 0.5f, 0f), Offset(w * 0.5f, h * 0.4f), strokeWidth = w * 0.01f, pathEffect = dashEffect)
    drawLine(Color.White, Offset(w * 0.5f, h * 0.6f), Offset(w * 0.5f, h), strokeWidth = w * 0.01f, pathEffect = dashEffect)
    if (withLight) {
        drawCircle(DangerRed, radius = w * 0.04f, center = Offset(w * 0.7f, h * 0.3f))
    }
}

private fun DrawScope.drawBus() {
    val w = size.width
    val h = size.height
    drawRoundRect(
        color = AccentBlue,
        topLeft = Offset(w * 0.1f, h * 0.3f),
        size = Size(w * 0.8f, h * 0.4f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.05f),
    )
    listOf(0.18f, 0.32f, 0.46f, 0.6f, 0.74f).forEach { x ->
        drawRect(SoftBlue, topLeft = Offset(w * x, h * 0.38f), size = Size(w * 0.08f, h * 0.15f))
    }
    drawCircle(DeepBlue, radius = w * 0.06f, center = Offset(w * 0.25f, h * 0.78f))
    drawCircle(DeepBlue, radius = w * 0.06f, center = Offset(w * 0.75f, h * 0.78f))
}

private fun DrawScope.drawSpeedSign(speed: Int) {
    val w = size.width
    val h = size.height
    val cx = w / 2
    val cy = h / 2
    val r = minOf(w, h) * 0.4f
    drawCircle(Color.White, r, center = Offset(cx, cy))
    drawCircle(DangerRed, r, center = Offset(cx, cy), style = Stroke(width = w * 0.07f))
    drawContext.canvas.nativeCanvas.apply {
        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.parseColor("#0D2B4E")
            textSize = h * 0.22f
            textAlign = android.graphics.Paint.Align.CENTER
            isAntiAlias = true
            isFakeBoldText = true
        }
        drawText(speed.toString(), cx, cy + h * 0.08f, paint)
    }
}

private fun DrawScope.drawZebra(parkedCar: Boolean = false) {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.45f), size = Size(w, h * 0.35f))
    val stripeW = w * 0.08f
    var x = w * 0.1f
    while (x < w * 0.9f) {
        drawRect(Color.White, topLeft = Offset(x, h * 0.48f), size = Size(stripeW, h * 0.29f))
        x += stripeW * 2f
    }
    if (parkedCar) {
        drawRoundRect(
            color = DangerRed,
            topLeft = Offset(w * 0.05f, h * 0.18f),
            size = Size(w * 0.25f, h * 0.18f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f),
        )
    }
}

private fun DrawScope.drawZebraWithLight() {
    drawZebra(parkedCar = false)
    drawCircle(DangerRed, radius = size.width * 0.04f, center = Offset(size.width * 0.85f, size.height * 0.2f))
}

private fun DrawScope.drawCyclistCrossing() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.5f), size = Size(w, h * 0.3f))
    val dash = PathEffect.dashPathEffect(floatArrayOf(w * 0.04f, w * 0.03f))
    drawLine(SuccessGreen, Offset(0f, h * 0.55f), Offset(w, h * 0.55f), strokeWidth = w * 0.012f, pathEffect = dash)
    drawLine(SuccessGreen, Offset(0f, h * 0.75f), Offset(w, h * 0.75f), strokeWidth = w * 0.012f, pathEffect = dash)
    drawCircle(DeepBlue, radius = w * 0.06f, center = Offset(w * 0.4f, h * 0.66f))
    drawCircle(DeepBlue, radius = w * 0.06f, center = Offset(w * 0.6f, h * 0.66f))
}

private fun DrawScope.drawHill() {
    val w = size.width
    val h = size.height
    val path = Path().apply {
        moveTo(0f, h * 0.85f)
        lineTo(w * 0.4f, h * 0.35f)
        lineTo(w * 0.6f, h * 0.35f)
        lineTo(w, h * 0.85f)
        close()
    }
    drawPath(path, DeepBlue)
    drawPath(path, Color.White, style = Stroke(width = w * 0.012f))
}

private fun DrawScope.drawParallelPark() {
    val w = size.width
    val h = size.height
    drawRect(SoftBlue, topLeft = Offset(0f, h * 0.55f), size = Size(w, h * 0.05f))
    drawRoundRect(AccentBlue, topLeft = Offset(w * 0.05f, h * 0.6f), size = Size(w * 0.25f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.03f))
    drawRoundRect(PrimaryBlue, topLeft = Offset(w * 0.4f, h * 0.6f), size = Size(w * 0.25f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.03f))
    drawRoundRect(AccentBlue, topLeft = Offset(w * 0.75f, h * 0.6f), size = Size(w * 0.2f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.03f))
}

private fun DrawScope.drawDisabledSign() {
    val w = size.width
    val h = size.height
    val cx = w / 2
    val cy = h / 2
    val r = minOf(w, h) * 0.4f
    drawCircle(PrimaryBlue, r, center = Offset(cx, cy))
    drawCircle(Color.White, radius = w * 0.05f, center = Offset(cx, cy - r * 0.4f))
    drawLine(Color.White, Offset(cx - r * 0.2f, cy - r * 0.1f), Offset(cx + r * 0.3f, cy + r * 0.3f), strokeWidth = w * 0.04f)
    drawCircle(Color.White, radius = r * 0.25f, center = Offset(cx + r * 0.05f, cy + r * 0.3f), style = Stroke(width = w * 0.04f))
}

private fun DrawScope.drawLanes(broken: Boolean, solid: Boolean) {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(w * 0.1f, 0f), size = Size(w * 0.8f, h))
    if (solid) {
        drawLine(Color.White, Offset(w * 0.4f, 0f), Offset(w * 0.4f, h), strokeWidth = w * 0.01f)
    }
    if (broken) {
        val dash = PathEffect.dashPathEffect(floatArrayOf(h * 0.06f, h * 0.06f))
        drawLine(Color.White, Offset(w * 0.6f, 0f), Offset(w * 0.6f, h), strokeWidth = w * 0.01f, pathEffect = dash)
    }
}

private fun DrawScope.drawLaneChange() {
    drawLanes(broken = true, solid = false)
    val w = size.width
    val h = size.height
    drawRoundRect(AccentBlue, topLeft = Offset(w * 0.18f, h * 0.7f), size = Size(w * 0.18f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
    drawRoundRect(PrimaryBlue, topLeft = Offset(w * 0.5f, h * 0.3f), size = Size(w * 0.18f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
}

private fun DrawScope.drawBusLane() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, 0f), size = Size(w, h))
    drawRect(SkyBlue, topLeft = Offset(w * 0.1f, 0f), size = Size(w * 0.25f, h))
    drawContext.canvas.nativeCanvas.apply {
        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.WHITE
            textSize = h * 0.12f
            textAlign = android.graphics.Paint.Align.CENTER
            isAntiAlias = true
            isFakeBoldText = true
        }
        drawText("BUS", w * 0.225f, h * 0.55f, paint)
    }
}

private fun DrawScope.drawTurnLane() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(w * 0.1f, 0f), size = Size(w * 0.8f, h))
    drawLine(Color.White, Offset(w * 0.5f, 0f), Offset(w * 0.5f, h), strokeWidth = w * 0.01f)
    val arrow = Path().apply {
        moveTo(w * 0.3f, h * 0.6f)
        lineTo(w * 0.3f, h * 0.3f)
        lineTo(w * 0.22f, h * 0.4f)
        moveTo(w * 0.3f, h * 0.3f)
        lineTo(w * 0.38f, h * 0.4f)
    }
    drawPath(arrow, Color.White, style = Stroke(width = w * 0.02f))
    val arrow2 = Path().apply {
        moveTo(w * 0.7f, h * 0.6f)
        lineTo(w * 0.7f, h * 0.3f)
        lineTo(w * 0.62f, h * 0.4f)
        moveTo(w * 0.7f, h * 0.3f)
        lineTo(w * 0.78f, h * 0.4f)
    }
    drawPath(arrow2, Color.White, style = Stroke(width = w * 0.02f))
}

private fun DrawScope.drawLaneArrows() = drawTurnLane()

private fun DrawScope.drawOvertake(forbidden: Boolean = false) {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(w * 0.1f, h * 0.2f), size = Size(w * 0.8f, h * 0.6f))
    drawRoundRect(AccentBlue, topLeft = Offset(w * 0.18f, h * 0.55f), size = Size(w * 0.22f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
    drawRoundRect(PrimaryBlue, topLeft = Offset(w * 0.5f, h * 0.27f), size = Size(w * 0.22f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
    if (forbidden) {
        drawCircle(DangerRed, radius = w * 0.18f, center = Offset(w * 0.85f, h * 0.2f), style = Stroke(width = w * 0.04f))
        drawLine(DangerRed, Offset(w * 0.74f, h * 0.31f), Offset(w * 0.96f, h * 0.09f), strokeWidth = w * 0.04f)
    }
}

private fun DrawScope.drawOvertakeCyclist() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(w * 0.1f, h * 0.2f), size = Size(w * 0.8f, h * 0.6f))
    drawCircle(WarningAmber, radius = w * 0.05f, center = Offset(w * 0.3f, h * 0.65f))
    drawCircle(WarningAmber, radius = w * 0.05f, center = Offset(w * 0.45f, h * 0.65f))
    drawRoundRect(PrimaryBlue, topLeft = Offset(w * 0.55f, h * 0.27f), size = Size(w * 0.25f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
}

private fun DrawScope.drawTurn(left: Boolean) {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.4f), size = Size(w, h * 0.2f))
    drawRect(DeepBlue, topLeft = Offset(w * 0.4f, 0f), size = Size(w * 0.2f, h))
    val arrow = Path().apply {
        if (left) {
            moveTo(w * 0.5f, h * 0.85f)
            lineTo(w * 0.5f, h * 0.55f)
            lineTo(w * 0.2f, h * 0.5f)
            lineTo(w * 0.3f, h * 0.4f)
            moveTo(w * 0.2f, h * 0.5f)
            lineTo(w * 0.3f, h * 0.6f)
        } else {
            moveTo(w * 0.5f, h * 0.85f)
            lineTo(w * 0.5f, h * 0.55f)
            lineTo(w * 0.8f, h * 0.5f)
            lineTo(w * 0.7f, h * 0.4f)
            moveTo(w * 0.8f, h * 0.5f)
            lineTo(w * 0.7f, h * 0.6f)
        }
    }
    drawPath(arrow, WarningAmber, style = Stroke(width = w * 0.02f))
}

private fun DrawScope.drawStopLine() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(w * 0.1f, 0f), size = Size(w * 0.8f, h))
    drawRect(Color.White, topLeft = Offset(w * 0.1f, h * 0.55f), size = Size(w * 0.8f, h * 0.04f))
    drawRoundRect(AccentBlue, topLeft = Offset(w * 0.4f, h * 0.65f), size = Size(w * 0.2f, h * 0.2f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
}

private fun DrawScope.drawAmbulance(color: Color, ladder: Boolean = false) {
    val w = size.width
    val h = size.height
    drawRoundRect(color, topLeft = Offset(w * 0.1f, h * 0.4f), size = Size(w * 0.8f, h * 0.35f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.04f))
    drawCircle(DeepBlue, radius = w * 0.06f, center = Offset(w * 0.25f, h * 0.78f))
    drawCircle(DeepBlue, radius = w * 0.06f, center = Offset(w * 0.75f, h * 0.78f))
    drawCircle(WarningAmber, radius = w * 0.05f, center = Offset(w * 0.5f, h * 0.32f))
    drawRect(Color.White, topLeft = Offset(w * 0.46f, h * 0.5f), size = Size(w * 0.08f, h * 0.18f))
    drawRect(Color.White, topLeft = Offset(w * 0.4f, h * 0.55f), size = Size(w * 0.2f, h * 0.08f))
    if (ladder) {
        drawLine(DeepBlue, Offset(w * 0.6f, h * 0.42f), Offset(w * 0.95f, h * 0.25f), strokeWidth = w * 0.02f)
    }
}

private fun DrawScope.drawSirens() {
    val w = size.width
    val h = size.height
    drawAmbulance(PrimaryBlue)
    drawCircle(DangerRed.copy(alpha = 0.4f), radius = w * 0.18f, center = Offset(w * 0.5f, h * 0.32f))
    drawCircle(SkyBlue.copy(alpha = 0.5f), radius = w * 0.12f, center = Offset(w * 0.5f, h * 0.32f))
}

private fun DrawScope.drawTwoSecond() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.5f), size = Size(w, h * 0.3f))
    drawRoundRect(AccentBlue, topLeft = Offset(w * 0.1f, h * 0.55f), size = Size(w * 0.2f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
    drawRoundRect(PrimaryBlue, topLeft = Offset(w * 0.7f, h * 0.55f), size = Size(w * 0.2f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
    drawContext.canvas.nativeCanvas.apply {
        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.WHITE
            textSize = h * 0.12f
            textAlign = android.graphics.Paint.Align.CENTER
            isAntiAlias = true
            isFakeBoldText = true
        }
        drawText("2 s", w * 0.5f, h * 0.68f, paint)
    }
}

private fun DrawScope.drawBraking() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.5f), size = Size(w, h * 0.3f))
    drawRoundRect(AccentBlue, topLeft = Offset(w * 0.65f, h * 0.55f), size = Size(w * 0.2f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
    val dash = PathEffect.dashPathEffect(floatArrayOf(w * 0.03f, w * 0.02f))
    drawLine(WarningAmber, Offset(w * 0.1f, h * 0.65f), Offset(w * 0.65f, h * 0.65f), strokeWidth = w * 0.02f, pathEffect = dash)
}

private fun DrawScope.drawWeatherDrop() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.55f), size = Size(w, h * 0.3f))
    repeat(8) { i ->
        val x = w * (0.1f + 0.1f * i)
        drawLine(SkyBlue, Offset(x, h * 0.2f), Offset(x - w * 0.02f, h * 0.4f), strokeWidth = w * 0.012f)
    }
}

private fun DrawScope.drawNight() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, 0f), size = Size(w, h))
    drawCircle(SoftBlue, radius = w * 0.08f, center = Offset(w * 0.8f, h * 0.2f))
    drawRect(Color.Black, topLeft = Offset(0f, h * 0.6f), size = Size(w, h * 0.25f))
    val arrow = Path().apply {
        moveTo(w * 0.2f, h * 0.55f)
        lineTo(w * 0.4f, h * 0.45f)
        lineTo(w * 0.4f, h * 0.55f)
        close()
    }
    drawPath(arrow, WarningAmber.copy(alpha = 0.8f))
}

private fun DrawScope.drawTruck() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.5f), size = Size(w, h * 0.3f))
    drawRoundRect(PrimaryBlue, topLeft = Offset(w * 0.45f, h * 0.3f), size = Size(w * 0.4f, h * 0.4f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
    drawRoundRect(AccentBlue, topLeft = Offset(w * 0.3f, h * 0.4f), size = Size(w * 0.18f, h * 0.3f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
    drawCircle(DeepBlue, radius = w * 0.05f, center = Offset(w * 0.36f, h * 0.74f))
    drawCircle(DeepBlue, radius = w * 0.05f, center = Offset(w * 0.55f, h * 0.74f))
    drawCircle(DeepBlue, radius = w * 0.05f, center = Offset(w * 0.78f, h * 0.74f))
}

private fun DrawScope.drawTailgate() {
    val w = size.width
    val h = size.height
    drawRect(DeepBlue, topLeft = Offset(0f, h * 0.5f), size = Size(w, h * 0.3f))
    drawRoundRect(AccentBlue, topLeft = Offset(w * 0.1f, h * 0.55f), size = Size(w * 0.2f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
    drawRoundRect(DangerRed, topLeft = Offset(w * 0.32f, h * 0.55f), size = Size(w * 0.2f, h * 0.18f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
}

private fun DrawScope.drawRain() {
    val w = size.width
    val h = size.height
    drawRect(SoftBlue, topLeft = Offset(0f, 0f), size = Size(w, h))
    drawArc(SkyBlue, startAngle = 180f, sweepAngle = 180f, useCenter = false, topLeft = Offset(w * 0.2f, h * 0.15f), size = Size(w * 0.6f, h * 0.3f))
    repeat(10) { i ->
        val x = w * (0.2f + 0.06f * i)
        drawLine(PrimaryBlue, Offset(x, h * 0.5f), Offset(x - w * 0.02f, h * 0.7f), strokeWidth = w * 0.012f)
    }
}

private fun DrawScope.drawFog() {
    val w = size.width
    val h = size.height
    drawRect(SoftBlue, topLeft = Offset(0f, 0f), size = Size(w, h))
    repeat(5) { i ->
        val y = h * (0.2f + 0.15f * i)
        drawRoundRect(Color.White.copy(alpha = 0.7f), topLeft = Offset(w * 0.1f, y), size = Size(w * 0.8f, h * 0.04f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f))
    }
}

private fun DrawScope.drawSnow() {
    val w = size.width
    val h = size.height
    drawRect(SoftBlue, topLeft = Offset(0f, 0f), size = Size(w, h))
    val xs = listOf(0.15f, 0.3f, 0.45f, 0.6f, 0.75f, 0.9f)
    val ys = listOf(0.2f, 0.4f, 0.6f, 0.8f)
    xs.forEach { x ->
        ys.forEach { y ->
            drawCircle(Color.White, radius = w * 0.025f, center = Offset(w * x, h * y))
        }
    }
}

private fun DrawScope.drawIce() {
    val w = size.width
    val h = size.height
    drawRect(SoftBlue, topLeft = Offset(0f, 0f), size = Size(w, h))
    drawRect(Color.White.copy(alpha = 0.8f), topLeft = Offset(0f, h * 0.55f), size = Size(w, h * 0.3f))
    drawLine(SkyBlue, Offset(0f, h * 0.6f), Offset(w, h * 0.62f), strokeWidth = w * 0.012f)
    drawLine(SkyBlue, Offset(0f, h * 0.7f), Offset(w, h * 0.71f), strokeWidth = w * 0.012f)
}

@Composable
fun LoadingTrafficScene(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "loading-scene")
    val carX by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(1800, easing = LinearEasing), repeatMode = RepeatMode.Restart),
        label = "car-x",
    )
    val dash by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(900, easing = LinearEasing), repeatMode = RepeatMode.Restart),
        label = "dash",
    )
    val lightStep by transition.animateFloat(
        initialValue = 0f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(animation = tween(2400, easing = LinearEasing), repeatMode = RepeatMode.Restart),
        label = "light",
    )
    val activeIndex = remember(lightStep) { lightStep.toInt().coerceIn(0, 2) }

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        drawRect(SoftBlue, topLeft = Offset(0f, 0f), size = Size(w, h * 0.5f))
        val road = Path().apply {
            moveTo(w * 0.05f, h)
            lineTo(w * 0.95f, h)
            lineTo(w * 0.7f, h * 0.5f)
            lineTo(w * 0.3f, h * 0.5f)
            close()
        }
        drawPath(road, DeepBlue)

        val dashCount = 6
        val baseY = h * 0.5f
        val endY = h
        for (i in 0 until dashCount) {
            val t = ((i + dash) % dashCount) / dashCount
            val y = baseY + t * (endY - baseY)
            val widthFactor = 0.005f + t * 0.04f
            val xOffset = w * (0.5f - widthFactor)
            drawRect(
                Color.White,
                topLeft = Offset(xOffset, y),
                size = Size(w * widthFactor * 2f, h * (0.015f + t * 0.025f)),
            )
        }

        val carWidth = w * 0.16f
        val carHeight = h * 0.07f
        val carY = h * (0.7f + 0.18f * carX)
        val cx = w * 0.5f
        drawRoundRect(
            color = AccentBlue,
            topLeft = Offset(cx - carWidth / 2f, carY),
            size = Size(carWidth, carHeight),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(carWidth * 0.15f),
        )
        drawRoundRect(
            color = SkyBlue,
            topLeft = Offset(cx - carWidth * 0.35f, carY + carHeight * 0.1f),
            size = Size(carWidth * 0.7f, carHeight * 0.45f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(carWidth * 0.08f),
        )
        drawCircle(DeepBlue, radius = carWidth * 0.1f, center = Offset(cx - carWidth * 0.3f, carY + carHeight))
        drawCircle(DeepBlue, radius = carWidth * 0.1f, center = Offset(cx + carWidth * 0.3f, carY + carHeight))

        val lightX = w * 0.78f
        val lightY = h * 0.12f
        drawRoundRect(
            DeepBlue,
            topLeft = Offset(lightX, lightY),
            size = Size(w * 0.12f, h * 0.28f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.02f),
        )
        val centers = listOf(
            Offset(lightX + w * 0.06f, lightY + h * 0.05f),
            Offset(lightX + w * 0.06f, lightY + h * 0.14f),
            Offset(lightX + w * 0.06f, lightY + h * 0.23f),
        )
        val colors = listOf(DangerRed, WarningAmber, SuccessGreen)
        centers.forEachIndexed { i, c ->
            val active = i == activeIndex
            drawCircle(if (active) colors[i] else colors[i].copy(alpha = 0.18f), w * 0.035f, c)
        }

        val signX = w * 0.12f
        val signY = h * 0.18f
        val signSize = w * 0.18f
        val triangle = Path().apply {
            moveTo(signX + signSize / 2f, signY)
            lineTo(signX + signSize, signY + signSize * 0.9f)
            lineTo(signX, signY + signSize * 0.9f)
            close()
        }
        drawPath(triangle, WarningAmber)
        drawPath(triangle, DangerRed, style = Stroke(width = w * 0.012f))
    }
}
