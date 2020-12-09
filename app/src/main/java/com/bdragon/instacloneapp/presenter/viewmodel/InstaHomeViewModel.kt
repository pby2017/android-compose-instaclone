package com.bdragon.instacloneapp.presenter.viewmodel

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

    private val _postIdToEditInDialog = MutableLiveData<Int>()
    val postIdToEditInDialog: LiveData<Int> = _postIdToEditInDialog

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

                it.copyPostItem(likesCount = likesCount, favoriteState = favoriteState)
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
            setPostIdToEditInDialog(postId = it.id)
            setContentToEditInDialog(content = it.text)
            showEditContentDialog()
        } ?: run {
            setPostIdToEditInDialog(postId = -1)
            setContentToEditInDialog(content = "")
            hideEditContentDialog()
        }
    }

    fun setPostIdToEditInDialog(postId: Int) {
        _postIdToEditInDialog.value = postId
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

    fun onCompleteEditContentClick(postId: Int, content: String) {
        val newPostList = postList.map {
            if (it.id == postId) {
                it.copyPostItem(text = content)
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

    private fun SamplePostItem.copyPostItem(
        id: Int? = null,
        text: String? = null,
        author: String? = null,
        authorImageResId: Int? = null,
        postImageResId: Int? = null,
        postImageResUrl: String? = null,
        likesCount: Int? = null,
        favoriteState: Boolean? = null
    ): SamplePostItem {
        return this.copy(
            id = id ?: this.id,
            text = text ?: this.text,
            author = author ?: this.author,
            authorImageResId = authorImageResId ?: this.authorImageResId,
            postImageResId = postImageResId ?: this.postImageResId,
            postImageResUrl = postImageResUrl ?: this.postImageResUrl,
            likesCount = likesCount ?: this.likesCount,
            favoriteState = favoriteState ?: this.favoriteState
        )
    }
}