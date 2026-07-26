package com.example.shared.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.cyberNeonBorder(
    radius: Dp = 24.dp,
    borderWidth: Dp = 2.dp,
    cyanColor: Color = Color(0xFF00E5FF)
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "cyber_neon")
    val progress by infiniteTransition.animateFloat(
        initialValue = -500f,
        targetValue = 3500f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "neon_progress"
    )

    var positionInWindow by remember { mutableStateOf(Offset.Zero) }

    this
        .onGloballyPositioned { coordinates ->
            positionInWindow = coordinates.positionInWindow()
        }
        .drawWithContent {
            drawContent()
            val localY = progress - positionInWindow.y
            val brush = Brush.verticalGradient(
                colors = listOf(
                    cyanColor.copy(alpha = 0.08f), // Base neon border 8%
                    cyanColor.copy(alpha = 0.5f),  // Highlight transition
                    cyanColor.copy(alpha = 1.0f),  // Brightest highlight
                    cyanColor.copy(alpha = 0.5f),  // Highlight transition
                    cyanColor.copy(alpha = 0.08f)  // Base neon border 8%
                ),
                startY = localY - 300f,
                endY = localY + 300f
            )
            drawRoundRect(
                brush = brush,
                size = size,
                cornerRadius = CornerRadius(radius.toPx()),
                style = Stroke(width = borderWidth.toPx())
            )
        }
}
