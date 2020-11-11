package com.bdragon.instacloneapp.ui.home

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bdragon.instacloneapp.R
import com.bdragon.instacloneapp.data.SampleDataProvider
import com.bdragon.instacloneapp.data.model.SamplePostItem
import com.bdragon.instacloneapp.ui.typography

@Composable
fun InstaPostItem(post: SamplePostItem) {
    Column {
        AuthorInfoSection(post = post)
        PostImageSection(imageResId = post.postImageResId)
        PostIconSection()
        LikesSection(post = post)
    }
}

@Composable
private fun AuthorInfoSection(post: SamplePostItem) {
    Row(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            asset = imageResource(id = post.authorImageResId),
            modifier = Modifier.preferredSize(32.dp)
        )
        Text(
            text = post.author,
            style = typography.body1,
            modifier = Modifier.padding(8.dp).weight(1f)
        )
        Icon(asset = Icons.Default.MoreVert)
    }
}

@Composable
private fun PostImageSection(imageResId: Int) {
    if (imageResId != -1) {
        Image(
            asset = imageResource(id = imageResId),
            modifier = Modifier.fillMaxWidth().preferredHeight(450.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun PostIconSection() {
    Row {
        var favoriteState by remember { mutableStateOf(false) }
        IconToggleButton(
            checked = favoriteState,
            onCheckedChange = { changedState ->
                favoriteState = changedState
            }) {
            val icon = if (favoriteState) Icons.Default.Favorite else Icons.Default.FavoriteBorder
            val tint = if (favoriteState) Color.Red else MaterialTheme.colors.onBackground
            Icon(
                asset = icon,
                modifier = Modifier.preferredSize(44.dp),
                tint = tint
            )
        }
        IconToggleButton(checked = false, onCheckedChange = {}) {
            Icon(
                asset = imageResource(id = R.drawable.baseline_message_black_24),
                modifier = Modifier.preferredSize(44.dp)
            )
        }
        IconToggleButton(checked = false, onCheckedChange = {}) {
            Icon(
                asset = Icons.Filled.Send,
                modifier = Modifier.preferredSize(44.dp)
            )
        }
    }
}

@Composable
fun LikesSection(post: SamplePostItem) {
    post.likesCount.takeIf { it > 0 }?.let { likesCount ->
        Row(
            modifier = Modifier.padding(start = 8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "좋아요 ${likesCount}개",
                style = typography.caption,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInstaPostItem() {
    SampleDataProvider.samplePostItemList.getOrNull(1)?.let {
        InstaPostItem(it)
    }
}