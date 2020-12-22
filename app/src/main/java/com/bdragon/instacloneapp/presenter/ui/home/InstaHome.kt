package com.bdragon.instacloneapp.presenter.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.imageResource
import com.bdragon.instacloneapp.R
import com.bdragon.instacloneapp.presenter.viewmodel.InstaHomeViewModel

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
                        Icon(bitmap = imageResource(id = R.drawable.baseline_rowing_black_24))
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Send)
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
        AddCommentDialog(instaHomeViewModel)
    }
}

@Composable
fun InstaPostsList(instaHomeViewModel: InstaHomeViewModel) {
    val postIdList = instaHomeViewModel.getPostIdList()

    LazyColumnFor(items = postIdList) { postId ->
        val post = instaHomeViewModel.getPostById(postId = postId)
        InstaPostItem(instaHomeViewModel = instaHomeViewModel, samplePostItem = post)
    }
}

@Composable
fun AddCommentDialog(instaHomeViewModel: InstaHomeViewModel) {
    val isShowAddCommentDialog: Boolean by instaHomeViewModel.showEditContentDialog.observeAsState(
        false
    )
    val contentToEditInDialog: String by instaHomeViewModel.contentToEditInDialog.observeAsState("")
    val postIdToEditInDialog: Int by instaHomeViewModel.postIdToEditInDialog.observeAsState(-1)
// side effect
    if (isShowAddCommentDialog) {
        AlertDialog(
            confirmButton = {
                Button(onClick = {
                    instaHomeViewModel.onCompleteEditContentClick(
                        postId = postIdToEditInDialog,
                        content = contentToEditInDialog
                    )
                }) { Text("등록") }
            },
            dismissButton = {
                Button(onClick = {
                    instaHomeViewModel.hideEditContentDialog()
                }) { Text("취소") }
            },
            onDismissRequest = {
                instaHomeViewModel.hideEditContentDialog()
            },
            text = {
                TextField(
                    value = contentToEditInDialog,
                    onValueChange = { newText ->
                        instaHomeViewModel.setContentToEditInDialog(content = newText)
                    }
                )
            }
        )
    }
}