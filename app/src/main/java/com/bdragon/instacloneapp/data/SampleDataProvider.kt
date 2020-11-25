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
        postImageResUrl = "",
        likesCount = 0
    )

    val samplePostItemList = mutableListOf<SamplePostItem>().apply {
        for (index in 0..5) {
            add(
                samplePostItem.copy(
                    id = samplePostItem.id + index,
                    text = "${samplePostItem.text}_$index 구매하러 갑니다. #갤럭시#S2$index 오늘 드디어 갤럭시 구매하러 가는데 어디로 가야할지 추천좀 해주세요. 써보신 분들 후기좀\n 알려주세요.",
                    author = "${samplePostItem.author}_$index",
                    likesCount = samplePostItem.likesCount + index
                )
            )
        }
    }.toList()
}