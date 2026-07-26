package com.example.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shared.animations.cyberNeonBorder

@Composable
fun QuickActionCard(onActionClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "DAFTAR TOOLS",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF8FAFC),
                letterSpacing = 1.sp,
                shadow = Shadow(
                    color = Color(0xFF00E5FF).copy(alpha = 0.5f),
                    offset = Offset(0f, 0f),
                    blurRadius = 8f
                )
            ),
            modifier = Modifier.padding(bottom = 12.dp, start = 4.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .cyberNeonBorder(radius = 24.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column {
                ActionItem(
                    icon = Icons.Default.AttachMoney,
                    title = "Tambah Pengeluaran",
                    subtitle = "Catat pengeluaran baru.",
                    onClick = { onActionClick("Tambah Pengeluaran") }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), thickness = 1.dp)
                ActionItem(
                    icon = Icons.Default.AccountBalance,
                    title = "Tabungan",
                    subtitle = "Kelola tabungan pribadi.",
                    onClick = { onActionClick("Tabungan") }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), thickness = 1.dp)
                ActionItem(
                    icon = Icons.Default.Handshake,
                    title = "Hutang",
                    subtitle = "Catat pinjaman keluar.",
                    onClick = { onActionClick("Hutang") }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), thickness = 1.dp)
                ActionItem(
                    icon = Icons.Default.Payments,
                    title = "Piutang",
                    subtitle = "Catat uang masuk.",
                    onClick = { onActionClick("Piutang") }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), thickness = 1.dp)
                ActionItem(
                    icon = Icons.Default.MusicNote,
                    title = "YTMP3",
                    subtitle = "Unduh musik dari YouTube.",
                    onClick = { onActionClick("YTMP3") }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), thickness = 1.dp)
                ActionItem(
                    icon = Icons.Default.SmartToy,
                    title = "AI",
                    subtitle = "Tanya AI Assistant.",
                    onClick = { onActionClick("AI") }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), thickness = 1.dp)
                ActionItem(
                    icon = Icons.Default.Chat,
                    title = "Bot WA",
                    subtitle = "Kelola Bot WhatsApp.",
                    onClick = { onActionClick("Bot WA") }
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f), thickness = 1.dp)
                ActionItem(
                    icon = Icons.Default.Security,
                    title = "Track COC Mu",
                    subtitle = "Pantau akun Clash of Clans.",
                    onClick = { onActionClick("Track COC Mu") }
                )
            }
        }
    }
}

@Composable
private fun ActionItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = tween(durationMillis = 150),
        label = "scale"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current
            ) { onClick() }
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        awaitFirstDown(requireUnconsumed = false)
                        isPressed = true
                        waitForUpOrCancellation()
                        isPressed = false
                    }
                }
            }
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .shadow(8.dp, RoundedCornerShape(12.dp), spotColor = Color(0xFF00E5FF).copy(alpha = 0.5f))
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(22.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}
