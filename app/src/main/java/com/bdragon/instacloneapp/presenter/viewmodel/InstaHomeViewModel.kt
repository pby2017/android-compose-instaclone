package com.bdragon.instacloneapp.presenter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bdragon.instacloneapp.data.SampleDataProvider
import com.bdragon.instacloneapp.data.model.SamplePostItem

class InstaHomeViewModel : ViewModel() {

    private val postList: MutableList<SamplePostItem> = mutableListOf<SamplePostItem>().apply {
        addAll(SampleDataProvider.samplePostItemList.filter { it.postImageResId != -1 })
    }

    private val _postListLiveData = MutableLiveData<List<SamplePostItem>>()
    val postListLiveData: LiveData<List<SamplePostItem>> = _postListLiveData

    private val _showEditContentDialog = MutableLiveData<Boolean>()
    val showEditContentDialog: LiveData<Boolean> = _showEditContentDialog

    private val _contentToEditInDialog = MutableLiveData<String>()
    val contentToEditInDialog: LiveData<String> = _contentToEditInDialog

    init {
        _postListLiveData.value = postList
    }

    fun getPostIdList(): List<Int> {
        return postList.map {
            it.id
        }
    }

    fun onFavoriteClicked(postId: Int, favoriteState: Boolean) {
        val newPostList = postList.map {
            if (it.id == postId) {
                val likesCount = if (favoriteState) it.likesCount + 1 else it.likesCount

                it.copy(
                    id = it.id,
                    text = it.text,
                    author = it.author,
                    authorImageResId = it.authorImageResId,
                    postImageResId = it.postImageResId,
                    postImageResUrl = it.postImageResUrl,
                    likesCount = likesCount,
                    favoriteState = favoriteState
                )
            } else {
                it
            }
        }

        postList.run {
            clear()
            addAll(newPostList)
        }
        _postListLiveData.value = newPostList
    }

    fun onContentClick(postId: Int) {
        postList.find { it.id == postId }?.let {
            setContentToEditInDialog(it.text)
            showEditContentDialog()
        } ?: run {
            setContentToEditInDialog(content = "")
            hideEditContentDialog()
        }
    }

    fun setContentToEditInDialog(content: String) {
        _contentToEditInDialog.value = content
    }

    fun showEditContentDialog() {
        _showEditContentDialog.value = true
    }

    fun hideEditContentDialog() {
        _showEditContentDialog.value = false
    }

    fun onCompleteEditContentClick() {
        val contentToEditInDialog = _contentToEditInDialog.value

    }
}