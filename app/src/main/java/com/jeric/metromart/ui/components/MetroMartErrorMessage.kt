package com.jeric.metromart.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jeric.metromart.R
import com.jeric.metromart.ui.theme.DarkGray
import com.jeric.metromart.ui.theme.LightGray

@Composable
fun MetroMartErrorMessage(
    message: String
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_network_error),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp),
                tint = if (isSystemInDarkTheme()) LightGray else DarkGray,
            )
            Text(
                modifier = Modifier
                    .padding(top = 10.dp),
                text = message,
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                textAlign = TextAlign.Center,
            )
        }
    }
}