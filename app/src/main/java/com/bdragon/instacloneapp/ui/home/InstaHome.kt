package com.bdragon.instacloneapp.ui.home

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.bdragon.instacloneapp.data.SampleDataProvider

@Composable
fun InstaHome() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Insta")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(asset = Icons.Filled.Place)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(asset = Icons.Filled.Send)
                    }
                }
            )
        }
    ) {
        InstaHomeContent()
    }
}

@Composable
fun InstaHomeContent() {
    Column {
        InstaPostsList()
    }
}

@Composable
fun InstaPostsList() {
    val posts = remember {
        SampleDataProvider.samplePostItemList.filter { it.postImageResId != -1 }
    }
    LazyColumnFor(items = posts) { post ->
        InstaPostItem(post)
    }
}