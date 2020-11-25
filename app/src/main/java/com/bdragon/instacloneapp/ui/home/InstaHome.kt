package com.bdragon.instacloneapp.ui.home

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.imageResource
import com.bdragon.instacloneapp.R
import com.bdragon.instacloneapp.data.model.SamplePostItem
import com.bdragon.instacloneapp.viewmodel.InstaHomeViewModel

@Composable
fun InstaHome(instaHomeViewModel: InstaHomeViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Insta")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(asset = imageResource(id = R.drawable.baseline_rowing_black_24))
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
        InstaHomeContent(instaHomeViewModel)
    }
}

@Composable
fun InstaHomeContent(instaHomeViewModel: InstaHomeViewModel) {
    Column {
        // todo : insta story section
        InstaPostsList(instaHomeViewModel)
    }
}

@Composable
fun InstaPostsList(instaHomeViewModel: InstaHomeViewModel) {
    val postList: List<SamplePostItem> by instaHomeViewModel.postListLiveData.observeAsState(listOf())
    val postIdList = instaHomeViewModel.getPostIdList()

    LazyColumnFor(items = postIdList) { postId ->
        postList.find { it.id == postId }?.let { postItem ->
            InstaPostItem(instaHomeViewModel, postItem)
        }
    }
}