import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var clickCount by remember { mutableStateOf(0) }
    var text by remember { mutableStateOf("Compose é incrível!") }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
        ) {

            Text(
                text = "Meu App Compose",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )

            Text(
                text = "Cliques: $clickCount",
                fontSize = 18.sp
            )

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Digite algo") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Button(
                onClick = {
                    clickCount++
                    text = "Botão clicado $clickCount vezes!"
                },
                modifier = Modifier.padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF6200EE),
                    contentColor = Color.White
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Coração",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Clique Aqui!")
                }
            }
            
            if (clickCount > 5) {
                Text(
                    text = "🎉 Você é um expert em Compose!",
                    color = Color.Green,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "App Compose Desktop"
    ) {
        App()
    }
}