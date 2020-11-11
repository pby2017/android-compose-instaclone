package com.bdragon.instacloneapp.data

import com.bdragon.instacloneapp.R
import com.bdragon.instacloneapp.data.model.SamplePostItem

object SampleDataProvider {

    private val samplePostItem = SamplePostItem(
        id = 1,
        text = "갤럭시",
        author = "안드로이드",
        authorImageResId = R.drawable.baseline_face_black_24,
        postImageResId = R.drawable.smartphone,
        likesCount = 0
    )

    val samplePostItemList = mutableListOf<SamplePostItem>().apply {
        for (index in 0..5) {
            add(
                samplePostItem.copy(
                    id = samplePostItem.id + index,
                    text = "${samplePostItem.text}_$index",
                    author = "${samplePostItem.author}_$index",
                    likesCount = samplePostItem.likesCount + index
                )
            )
        }
    }.toList()
}