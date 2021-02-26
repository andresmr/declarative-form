package com.andresmr.declarativeform

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStory()
        }
    }
}

@Composable
fun NewsStory() {
    val viewModel: MainViewModel = viewModel()
    MaterialTheme {
        viewModel.uiState.observeAsState().value?.let { uiState ->
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.header),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    uiState.title,
                    style = typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )

                Checkbox(
                    checked = uiState.allSelected,
                    onCheckedChange = { selected ->
                        viewModel.onCheckChange(selected)
                    }
                )

                MessageList(
                    messages = uiState.messages,
                    selectAll = uiState.allSelected
                )
            }
        }
    }
}

@Composable
fun MessageList(
    messages: List<String>,
    selectAll: Boolean
) {
    LazyColumn() {
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
            selected -> MaterialTheme.colors.surface
            else -> MaterialTheme.colors.secondary
        }
    ) {
        Text(
            text = text,
            style = typography.body2,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    NewsStory()
}