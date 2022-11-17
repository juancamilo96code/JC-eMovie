package com.jc.collantes.emovie.presentation.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jc.collantes.emovie.R
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun EmptyState(
    popBackEnable: Boolean = false,
    onBackButtonPressed: () -> Unit,
    onRetryButton: () -> Unit
) {

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (popBackEnable) {
            Icon(painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "arrow_back",
                modifier = Modifier
                    .padding(top = 38.dp, start = 28.dp)
                    .align(Alignment.TopStart)
                    .clickable{
                        onBackButtonPressed()
                    })
        }

        Column(Modifier.fillMaxWidth().align(Alignment.Center)) {
            Text(
                text = stringResource(id = R.string.empty_state_label),
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.retry_label),
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable(true) {
                        onRetryButton()
                    },
                color = Color.Blue,
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_retry),
                contentDescription = "retry",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable(true) {
                        onRetryButton()
                    },
                tint = Color.Blue
            )

        }
    }

}

@Preview
@Composable
fun EmptyStatePreview() {
    EmptyState(true, {}, {})
}