package com.example.a30daysofreading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30daysofreading.model.QuoteRepository
import com.example.a30daysofreading.ui.theme._30DaysOfReadingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DaysOfReadingTheme(useDarkTheme = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App()
{
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar =
        {
            TopAppBar()
        }
    ) {
        val quotes=QuoteRepository.quotes
        QuoteList(quotes, contentPadding = it)

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier:Modifier=Modifier)
{
    CenterAlignedTopAppBar(
        title = {
            Text(
                text= "30 Quotes",
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier=modifier
    )

}