package br.com.zafcode.olimpo.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.zafcode.olimpo.OlimpoApplication
import br.com.zafcode.olimpo.R
import br.com.zafcode.olimpo.composable.ChatBubble
import br.com.zafcode.olimpo.composable.NavSecondaryHeader
import br.com.zafcode.olimpo.repository.ChatService
import br.com.zafcode.olimpo.repository.Message
import br.com.zafcode.olimpo.ui.theme.OlimpoTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModelChatScreen(private val name: String) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val coroutineScope = rememberCoroutineScope()

        val application = LocalContext.current.applicationContext as? OlimpoApplication
        val chatService = application?.chatService

        var messages by remember {
            mutableStateOf(
                listOf(
                    Message("Olá, eu me chamo $name! Em que posso ajudar?", isModel = true),
                )
            )
        }

        var inputText by remember { mutableStateOf("") }

        val onSend = {
            if (inputText.isNotBlank()) {
                messages += Message(inputText, isModel = false)
                messages += Message("● ● ●", isModel = true)

                inputText = ""

                coroutineScope.launch(Dispatchers.IO) {
                    val body = ChatService.ChatCompleteBody(model = name, messages = messages)
                    val response = chatService?.sendMessage(body)

                    withContext(Dispatchers.Main) {
                        messages = messages.dropLast(1)

                        var isError = false

                        if (response?.isSuccessful == true) {
                            val data = response.body()
                            if (data != null) messages += Message(data.response, isModel = true)
                            else isError = true
                        } else {
                            response?.errorBody()?.string()?.let { Log.e("ModelChatScreen", it) }
                            isError = true
                        }

                        if (isError) {
                            messages += Message(
                                "Desculpe, houve um erro ao enviar a mensagem! Tente novamente.",
                                isModel = true
                            )
                        }
                    }
                }
            }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column {
                NavSecondaryHeader(name, iconByModelName[name] ?: R.drawable.custom_brain)
                Separator()
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier.weight(1f).padding(top = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(messages) { message -> ChatBubble(message = message) }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(88.dp)
                            .padding(16.dp)
                    ) {
                        OutlinedTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary
                            ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                            keyboardActions = KeyboardActions(onSend = { onSend() }),
                            placeholder = {
                                Text(
                                    "Digite uma mensagem...",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Normal
                                    )
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                        )

                        Button(
                            onClick = onSend,
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Image(
                                imageVector = Icons.AutoMirrored.Default.Send,
                                contentDescription = "Enviar",
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        }
                    }

                }

            }
        }
    }

    @Composable
    fun Separator() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
        )
    }
}

@Preview
@Composable
fun ModelChatPreview() {
    OlimpoTheme {
        Navigator(ModelChatScreen("Atena"))
    }
}
