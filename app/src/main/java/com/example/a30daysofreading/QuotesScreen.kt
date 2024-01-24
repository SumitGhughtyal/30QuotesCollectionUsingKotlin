package com.example.a30daysofreading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysofreading.model.Quote
import com.example.a30daysofreading.ui.theme._30DaysOfReadingTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuoteList(
    quotes:List<Quote>,
    modifier: Modifier=Modifier,
    contentPadding:PaddingValues= PaddingValues(0.dp)
){
    val visibleState = remember{
        MutableTransitionState(false).apply {
            targetState=true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)

        ),
        exit= fadeOut(),
        modifier = Modifier
    ){
        LazyColumn(contentPadding = contentPadding)
        {
            itemsIndexed(quotes){index, quote->
                QuoteListItem(quote ,
                    modifier= Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) })
                        ))
            }
        }
    }

}



@Composable
fun QuoteListItem(
    quote: Quote,
    modifier:Modifier=Modifier,
){

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ){
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)

        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)

            ) {
                Text(
                    text = stringResource(id = quote.titleRes),
                    style=MaterialTheme.typography.displaySmall,
                )
                Text(
                    text= stringResource(id = quote.quoteRes),

                    style = MaterialTheme.typography.bodyLarge
                )


            }
            Spacer(modifier = Modifier.width(16.dp))



            Column {
                Box(
                    modifier= Modifier
                        .size(130.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        ),


                    )
                {
                    Image(
                        painter = painterResource(id = quote.imageRes) ,
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.FillWidth,
                    )

                }
                Spacer(modifier = Modifier.height(100.dp))

                Text(
                    text = stringResource(id = quote.authorRes),
                    style = MaterialTheme.typography.bodySmall,
                    modifier=Modifier
                        .width(80.dp)


                )
            }

        }
    }

}



@Preview
@Composable
fun DefaultPreview()
{
    val quote=Quote(R.string.title_1, R.drawable.day_1_image,R.string.quote_1,R.string.author_1)

    _30DaysOfReadingTheme(useDarkTheme = true) {
        Surface (
            color=MaterialTheme.colorScheme.background
        ){
            QuoteListItem(quote)

        }
    }
}