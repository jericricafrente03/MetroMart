package com.jeric.metromart.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jeric.metromart.domain.model.GithubModel

@Composable
fun GithubItem(
    githubModel: GithubModel,
    onClick: (data: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick(githubModel.id) }
            .padding(10.dp)
    ) {


    }
}