package com.bdragon.instacloneapp.data

import com.bdragon.instacloneapp.R
import com.bdragon.instacloneapp.data.model.SamplePostItem

object SampleDataProvider {

    val samplePostItemList = listOf(
        SamplePostItem(
            id = 1,
            text = "갤럭시",
            author = "안드로이드",
            authorImageResId = R.drawable.baseline_face_black_24,
            postImageResId = R.drawable.ic_launcher_foreground
        )
    )
}