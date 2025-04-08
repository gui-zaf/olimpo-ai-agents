//package br.com.zafcode.fireticket.screen
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
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
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
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
//class CreatorsScreen : Screen {
//    @Composable
//    override fun Content() {
//        val navigator = LocalNavigator.currentOrThrow
//        val coroutineScope = rememberCoroutineScope()
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
//                    "Criadores do Fireticket™",
//                    style = MaterialTheme.typography.headlineSmall
//                )
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clip(MaterialTheme.shapes.large)
//                        .background(color = Color.White)
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    Row {
//                        Image(
//                            painter = painterResource(id = R.drawable.code),
//                            contentDescription = null,
//                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
//                            modifier = Modifier
//                                .requiredSize(32.dp)
//                                .aspectRatio(1f)
//                        )
//                    }
//                    Row() {
//                        Text(
//                            "Fireticket™ foi desenvolvido por Snowcode, uma empresa de desenvolvimento de software focada em soluções inovadoras e de alta qualidade.",
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Normal,
//                            color = MaterialTheme.colorScheme.onSurface,
//                        )
//                    }
//                    Separator()
//                    Column {
//                        Row(
//                            horizontalArrangement = Arrangement.spacedBy(4.dp)
//                        ) {
//                            Text(
//                                "Guilherme",
//                                fontSize = 24.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = MaterialTheme.colorScheme.primary
//                            )
//                            Text(
//                                "Ferraz",
//                                fontSize = 24.sp,
//                                fontWeight = FontWeight.Bold
//                            )
//                        }
//                        Row(
//                            horizontalArrangement = Arrangement.spacedBy(4.dp)
//                        ) {
//                            Text(
//                                "@gui-zaf",
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight.Medium,
//                                color = MaterialTheme.colorScheme.onSurface
//                            )
//                        }
//                    }
//                    Separator()
//                    Column {
//                        Row(
//                            horizontalArrangement = Arrangement.spacedBy(4.dp)
//                        ) {
//                            Text(
//                                "Maria",
//                                fontSize = 24.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = MaterialTheme.colorScheme.primary
//                            )
//                            Text(
//                                "Gabriela",
//                                fontSize = 24.sp,
//                                fontWeight = FontWeight.Bold
//                            )
//                        }
//                        Row(
//                            horizontalArrangement = Arrangement.spacedBy(4.dp)
//                        ) {
//                            Text(
//                                "@snowtenshi",
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight.Medium,
//                                color = MaterialTheme.colorScheme.onSurface
//                            )
//                        }
//                    }
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
//                }
//            }
//        }
//    }
//}
//
//
//
//@Preview
//@Composable
//fun CreatorsScreenPreview() {
//    FireticketTheme {
//        Navigator(CreatorsScreen())
//    }
//}