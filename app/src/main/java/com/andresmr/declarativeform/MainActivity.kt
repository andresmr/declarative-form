package com.andresmr.declarativeform

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andresmr.declarativeform.ui.components.HeaderImage
import com.andresmr.declarativeform.ui.components.MessageList
import com.andresmr.declarativeform.ui.components.ScreenContainer

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.uiState.observeAsState().value?.let { uiState ->
                ScreenContainer {
                    MainScreenContent(uiState = uiState)
                }
            }
        }
    }
}

@Composable
fun MainScreenContent(uiState: MainState) {

    Column(modifier = Modifier.fillMaxHeight()) {

        HeaderImage(resource = R.drawable.header)

        Text(
            uiState.title,
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.h5,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )

        MessageList(
            messages = uiState.messages,
            Modifier.apply {
                padding(12.dp)
                weight(1f)
                fillMaxWidth()
            }
        )
    }

    /*AnimatedVisibility(visible = saveAvailable.value) {
        FloatingButton()
    }*/
}


@ExperimentalAnimationApi
@Preview(
    showBackground = true,
    name = "Main Screen Preview"
)
@Composable
fun DefaultPreview() {
    ScreenContainer {
        MainScreenContent(
            MainState(
                "Title",
                listOf(
                    "Extremoduro",
                    "Fito y Fitipaldis",
                    "Marea",
                    "Reincidentes",
                    "Sinkope",
                    "Derby Motoreta's Burrito Cachimba",
                    "Califato 3/4"
                ),
                false
            )
        )
    }
}