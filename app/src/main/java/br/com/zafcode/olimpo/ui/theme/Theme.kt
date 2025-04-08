package br.com.zafcode.olimpo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun OlimpoTheme(
    content: @Composable () -> Unit,
) = MaterialTheme(
    colorScheme = lightColorScheme(
        primary = Color(0xFF3079FF),
        secondary = Color(0xFF2F2F33),
        surface = Color(0xFFF2F2F2),
        onSurface = Color(0xFF5B5B5E),
        onBackground = Color(0xFF2F2F33),
    ),
    typography = Typography,
    content = content
)