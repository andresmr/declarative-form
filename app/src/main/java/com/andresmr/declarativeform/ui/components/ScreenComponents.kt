package com.andresmr.declarativeform.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.andresmr.declarativeform.ui.theme.DeclarativeFormTheme

@Composable
fun ScreenContainer(content: @Composable () -> Unit) {
    DeclarativeFormTheme() {
        Surface(color = Color.LightGray) {
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
fun CheckboxWithText(text: String, isSelected: Boolean, onChecked: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = onChecked
        )

        Text(
            text,
            style = MaterialTheme.typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MessageList(
    messages: List<String>,
    selectAll: Boolean
) {
    LazyColumn(verticalArrangement = Arrangement.SpaceEvenly) {
        items(messages) { message ->
            Item(
                text = message,
                selected = selectAll
            )
        }
    }
}

@Composable
fun Item(text: String, selected: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = when {
            selected -> MaterialTheme.colors.secondary
            else -> MaterialTheme.colors.surface
        }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(12.dp)
        )
    }
}