package com.andresmr.declarativeform.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.andresmr.declarativeform.ui.theme.DeclarativeFormTheme

@Composable
fun ScreenContainer(content: @Composable () -> Unit) {
    DeclarativeFormTheme {
        Surface(color = Color.White) {
            content()
        }
    }
}

@Composable
fun HeaderImage(resource: Int, description: String? = null) {
    Image(
        painter = painterResource(id = resource),
        contentDescription = description,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun MessageList(
    messages: List<String>,
    modifier: Modifier
) {
    val scrollState = rememberScrollState()

    LazyColumn(modifier = modifier) {
        items(messages) { message ->
            Item(
                label = "Label",
                value = message
            )
        }
    }
}

@Composable
fun Item(label: String, value: String) {
    val inputValue = remember { mutableStateOf(TextFieldValue(value)) }
    OutlinedTextField(
        value = inputValue.value,
        onValueChange = { inputValue.value = it },
        modifier = Modifier
            .padding(all = 12.dp)
            .fillMaxWidth(),
        label = { Text(label) },
        placeholder = { Text(text = value) },
        singleLine = true
    )

}

@Composable
fun FloatingButton() {
    FloatingActionButton(
        onClick = {},
        content = {}
    )
}
