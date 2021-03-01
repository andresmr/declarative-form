package com.andresmr.declarativeform

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andresmr.declarativeform.ui.components.CheckboxWithText
import com.andresmr.declarativeform.ui.components.HeaderImage
import com.andresmr.declarativeform.ui.components.MessageList
import com.andresmr.declarativeform.ui.components.ScreenContainer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel()
            viewModel.uiState.observeAsState().value?.let { uiState ->
                ScreenContainer {
                    MainScreenContent(
                        uiState = uiState,
                        onChecked = { selected ->
                            viewModel.onCheckChange(selected)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreenContent(uiState: MainState, onChecked: (Boolean) -> Unit) {
    Column {

        HeaderImage(resource = R.drawable.header)

        CheckboxWithText(
            text = uiState.title,
            isSelected = uiState.allSelected,
            onChecked = onChecked
        )

        MessageList(
            messages = uiState.messages,
            selectAll = uiState.allSelected
        )
    }
}



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
        ) {}
    }
}