package br.com.zafcode.olimpo.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.zafcode.olimpo.OlimpoApplication
import br.com.zafcode.olimpo.R
import br.com.zafcode.olimpo.composable.AiModel
import br.com.zafcode.olimpo.repository.Model
import br.com.zafcode.olimpo.ui.theme.OlimpoTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainMenuScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val coroutineScope = rememberCoroutineScope()

        val application = LocalContext.current.applicationContext as? OlimpoApplication
        val projectService = application?.projectService

        var isLoading by remember { mutableStateOf(true) }
        var models by remember { mutableStateOf(emptyList<Model>()) }
        val olimpoModels =
            remember(models) { models.filter { iconByModelName.containsKey(it.name) } }
        val customModels =
            remember(models) { models.filter { !iconByModelName.containsKey(it.name) } }

        LaunchedEffect(isLoading) {
            withContext(Dispatchers.IO) {
                val response = projectService?.getAll()
                withContext(Dispatchers.Main) {
                    models = response?.body() ?: emptyList()
                    isLoading = false
                }
            }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            PullToRefreshBox(
                isRefreshing = isLoading,
                onRefresh = { isLoading = true }
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp, 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CurrentUser()
                    Separator()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.olimpo_model),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .requiredSize(28.dp)
                                .aspectRatio(1f)
                        )
                        Text(
                            "Modelos Olímpo",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    if (olimpoModels.isNotEmpty()) {
                        olimpoModels.chunked(2).forEach { chunk ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(168.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                chunk.forEach { (name, subtitle) ->
                                    AiModel(
                                        modelName = name,
                                        modelDescription = subtitle,
                                        icon = iconByModelName[name]!!,
                                        isOlimpo = true,
                                        onClick = {
                                            navigator.push(ModelChatScreen(name))
                                        }
                                    )
                                }
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.boston),
                                contentDescription = null,
                                modifier = Modifier
                                    .aspectRatio(1f)
                            )
                            Text(
                                text = "Parece que você não tem nenhum modelo Olímpo disponível",
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Separator()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.custom_brain),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .requiredSize(28.dp)
                                .aspectRatio(1f)
                        )
                        Text(
                            "Modelos Customizados",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    if (customModels.isNotEmpty()) {
                        customModels.chunked(2).forEach { chunk ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(168.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                chunk.forEach { (name, subtitle) ->
                                    AiModel(
                                        modelName = name,
                                        modelDescription = subtitle,
                                        icon = R.drawable.custom_brain,
                                        isOlimpo = false,
                                        onClick = {
                                            navigator.push(ModelChatScreen(name))
                                        }
                                    )
                                }
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.custom_models),
                                contentDescription = null,
                                modifier = Modifier
                                    .aspectRatio(1f)
                            )
                            Text(
                                text = "Adicione um modelo customizado na Central Olímpo",
                                textAlign = TextAlign.Center
                            )
                        }

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            4.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.shield),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .requiredSize(16.dp)
                                .aspectRatio(1f)
                        )
                        Text(
                            text = "Olímpo™ by Snowcode",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 12.sp,
                        )
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

    @Composable
    fun CurrentUser() {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(56.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "GF",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        "Olá,",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        "Gui Ferraz",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(
                    "240.XXX.318-XX",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview
@Composable
fun MainMenuScreenPreview() {
    OlimpoTheme {
        Navigator(MainMenuScreen())
    }
}