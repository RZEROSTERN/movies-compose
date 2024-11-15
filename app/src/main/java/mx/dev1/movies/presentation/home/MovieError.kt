package mx.dev1.movies.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieError(
    errorMessage: String = "Ocurrió un error, verifica tu conexión",
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
       Text(
           text = errorMessage,
           textAlign = TextAlign.Center,
           fontSize = 24.sp,
           modifier = Modifier.fillMaxWidth().padding(8.dp)
       )

        Button(
            onClick = onRefreshClick,
            modifier = Modifier.fillMaxWidth().padding(24.dp)
        ) {
            Text(text = "Reintentar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieErrorPreview() {
    MovieError {

    }
}