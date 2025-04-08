//package br.com.zafcode.fireticket.screen
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.requiredSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.rotate
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import br.com.zafcode.fireticket.R
//import br.com.zafcode.fireticket.composable.NavButton
//import br.com.zafcode.fireticket.composable.NavSecondaryHeader
//import br.com.zafcode.fireticket.ui.theme.FireticketTheme
//import cafe.adriel.voyager.core.screen.Screen
//import cafe.adriel.voyager.navigator.LocalNavigator
//import cafe.adriel.voyager.navigator.Navigator
//import cafe.adriel.voyager.navigator.currentOrThrow
//
//class HelpScreen : Screen {
//    @Composable
//    override fun Content() {
//        val navigator = LocalNavigator.currentOrThrow
//        val coroutineScope = rememberCoroutineScope()
//
//        var expandedIndex by remember { mutableIntStateOf(-1) }
//
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp, 32.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                NavSecondaryHeader()
//                Text(
//                    "Ajuda",
//                    style = MaterialTheme.typography.headlineSmall
//                )
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clip(MaterialTheme.shapes.large)
//                        .background(color = Color.White)
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    Row {
//                        Image(
//                            painter = painterResource(id = R.drawable.interrogation),
//                            contentDescription = null,
//                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
//                            modifier = Modifier
//                                .requiredSize(32.dp)
//                                .aspectRatio(1f)
//                        )
//                    }
//                    Topic(
//                        "Cadastrar um Usuário",
//                        "Preencha corretamente todas as informações do participante, selecione um evento e clique em Gerar Ticket.",
//                        isExpanded = expandedIndex == 0,
//                        onExpand = { expandedIndex = if (expandedIndex == 0) -1 else 0 }
//                    )
//                    Separator()
//                    Topic(
//                        "Cadastrar um Evento",
//                        "Preencha corretamente todas as informações do evento e clique em Adicionar Evento.",
//                        isExpanded = expandedIndex == 1,
//                        onExpand = { expandedIndex = if (expandedIndex == 1) -1 else 1 })
//                    Separator()
//                    Topic(
//                        "Gerar um Ticket",
//                        "Preencha o CPF do Usuário, selecione o evento, confirme as informações do ticket prévia e clique em Gerar Ticket.",
//                        isExpanded = expandedIndex == 2,
//                        onExpand = { expandedIndex = if (expandedIndex == 2) -1 else 2 })
//                    Separator()
//                    Topic(
//                        "Compartilhar um Ticket",
//                        "Clique no botão compartilhar ticket e escolha o aplicativo de compartilhamento.",
//                        isExpanded = expandedIndex == 3,
//                        onExpand = { expandedIndex = if (expandedIndex == 3) -1 else 3 })
//                    Separator()
//                    Topic(
//                        "Impressora Térmica",
//                        "Seguindo a etapa de 'Compartilhar um Ticket' na opção imprimir ticket: verifique se a impressora está conectada ao Bluetooth e a selecione para imprimir o ticket.",
//                        isExpanded = expandedIndex == 4,
//                        onExpand = { expandedIndex = if (expandedIndex == 4) -1 else 4 })
//                    Separator()
//                    Topic(
//                        "Validar um Ticket",
//                        "Aponte sua câmera para o QR Code do ticket e aguarde a validação, ou digite o código do ticket manualmente.",
//                        isExpanded = expandedIndex == 5,
//                        onExpand = { expandedIndex = if (expandedIndex == 5) -1 else 5 })
//                    Separator()
//                    Topic(
//                        "Lista de Presença",
//                        "Na tela de eventos, clique sobre o evento que deseja visualizar a lista de presença.",
//                        isExpanded = expandedIndex == 6,
//                        onExpand = { expandedIndex = if (expandedIndex == 6) -1 else 6 })
//                }
//                Row(
//                    modifier = Modifier.height(168.dp),
//                    horizontalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    NavButton(
//                        text = "Tela Inicial",
//                        icon = R.drawable.house_blank,
//                        onClick = {
//                            navigator.popUntilRoot()
//                        }
//                    )
//                    NavButton(
//                        text = "Criadores",
//                        icon = R.drawable.code,
//                        onClick = {
//                            navigator.replace(CreatorsScreen())
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Topic(title: String, body: String, isExpanded: Boolean, onExpand: () -> Unit) {
//    Column(
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clip(MaterialTheme.shapes.small)
//                .clickable { onExpand() }
//                .padding(0.dp, 8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = title,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colorScheme.onSurface
//            )
//            Image(
//                painter = painterResource(id = R.drawable.back),
//                contentDescription = "Voltar",
//                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
//                modifier = Modifier
//                    .requiredSize(24.dp)
//                    .rotate(if (isExpanded) 90f else 270f)
//            )
//        }
//        AnimatedVisibility(
//            visible = isExpanded
//        ) {
//            Text(
//                text = body,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Normal,
//                color = MaterialTheme.colorScheme.onSurface,
//                textAlign = TextAlign.Justify,
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//fun AddHelpScreenPreview() {
//    FireticketTheme {
//        Navigator(HelpScreen())
//    }
//}