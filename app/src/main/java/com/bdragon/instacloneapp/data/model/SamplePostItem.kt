package com.bdragon.instacloneapp.data.model

data class SamplePostItem(
    val id: Int = -1,
    val text: String = "",
    val author: String = "",
    val authorImageResId: Int = -1,
    val postImageResId: Int = -1,
    val postImageResUrl: String = "",
    val likesCount: Int = 0,
    val favoriteState:Boolean = false
)