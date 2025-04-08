package br.com.zafcode.olimpo.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.zafcode.olimpo.repository.Message
import br.com.zafcode.olimpo.ui.theme.OlimpoTheme
import com.halilibo.richtext.commonmark.Markdown
import com.halilibo.richtext.ui.material3.RichText

@Composable
fun ChatBubble(
    message: Message,
    modifier: Modifier = Modifier
) {
    val (text, isModel) = message

    Row(
        horizontalArrangement = if (isModel) Arrangement.Start else Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = if (isModel) 12.dp else 48.dp,
                end = if (isModel) 48.dp else 12.dp,
                top = 4.dp,
                bottom = 4.dp
            )
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(
                    if (isModel) MaterialTheme.colorScheme.secondary
                    else MaterialTheme.colorScheme.primary
                )
                .padding(16.dp)
        ) {
            CompositionLocalProvider(LocalContentColor provides Color.White) {
                CompositionLocalProvider(
                    LocalTextStyle provides MaterialTheme.typography.bodyMedium.copy(
                        textAlign = TextAlign.Justify
                    )
                ) {
                    RichText {
                        Markdown(text)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ChatBubblePreview() {
    OlimpoTheme {
        ChatBubble(
            message = Message("Hello, how can I help you?", isModel = true)
        )
    }
}
