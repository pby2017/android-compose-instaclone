package com.bdragon.instacloneapp.ui.home

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.imageResource
import com.bdragon.instacloneapp.data.model.SamplePostItem

@Composable
fun InstaPostItem(post: SamplePostItem) {
    Column {
        AuthorInfoSection(post)
    }
}

@Composable
private fun AuthorInfoSection(post: SamplePostItem) {
    Row {
        Image(asset = imageResource(id = post.authorImageResId))
        Text(text = post.author)
        Icon(asset = Icons.Default.MoreVert)
    }
}