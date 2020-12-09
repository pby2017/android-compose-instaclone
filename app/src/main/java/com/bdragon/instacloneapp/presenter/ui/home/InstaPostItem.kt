package com.bdragon.instacloneapp.presenter.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.SpanStyleRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bdragon.instacloneapp.R
import com.bdragon.instacloneapp.data.model.SamplePostItem
import com.bdragon.instacloneapp.presenter.ui.typography
import com.bdragon.instacloneapp.presenter.viewmodel.InstaHomeViewModel

@Composable
fun InstaPostItem(instaHomeViewModel: InstaHomeViewModel, post: SamplePostItem) {
    Column {
        AuthorInfoSection(post = post)
        PostImageSection(imageResId = post.postImageResId)
        PostIconSection(
            favoriteState = post.favoriteState,
            onFavoriteClicked = { favoriteState ->
                instaHomeViewModel.onFavoriteClicked(
                    postId = post.id,
                    favoriteState = favoriteState
                )
            }
        )
        LikesSection(post = post)
        AuthorContentSection(
            post = post,
            onShowEditContentDialogClick = {
                instaHomeViewModel.onContentClick(postId = post.id)
            })
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
private fun PostIconSection(
    favoriteState: Boolean,
    onFavoriteClicked: (Boolean) -> Unit
) {
    Row {
        var favoriteStateRemember by remember { mutableStateOf(favoriteState) }

        IconToggleButton(
            checked = favoriteStateRemember,
            onCheckedChange = { changedState ->
                onFavoriteClicked(changedState)
                favoriteStateRemember = changedState
            }) {
            val icon =
                if (favoriteStateRemember) Icons.Default.Favorite else Icons.Default.FavoriteBorder
            val tint = if (favoriteStateRemember) Color.Red else MaterialTheme.colors.onBackground
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
                modifier = Modifier.padding(start = 4.dp),
                fontWeight = FontWeight(1000),
                style = typography.subtitle2
            )
        }
    }
}

@Composable
fun AuthorContentSection(
    post: SamplePostItem,
    onShowEditContentDialogClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(start = 8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val originalText = "${post.author} ${post.text}"

        Text(
            text = AnnotatedString(
                text = originalText,
                spanStyles = getSpanStyles(originalText = originalText, post = post)
            ),
            modifier = Modifier.padding(start = 4.dp)
                .clickable(onClick = { onShowEditContentDialogClick() })
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInstaPostItem() {
//    SampleDataProvider.samplePostItemList.getOrNull(1)?.let {
//        InstaPostItem(it)
//    }
}

private fun getSpanStyles(originalText: String, post: SamplePostItem): List<SpanStyleRange> {
    val spanStyles = mutableListOf<SpanStyleRange>()

    // 작성자 닉네임 처리
    val authorStartIndex = 0
    val authorEndIndex = post.author.length
    spanStyles.add(
        AnnotatedString.Range(
            SpanStyle(fontWeight = FontWeight(1000)),  // bode
            authorStartIndex,
            authorEndIndex
        )
    )

    // # 해시태그 처리 (클릭어떻게할지)
    val contentStartIndex = authorEndIndex + 1
    var isHashTagText = false
    var hashTagStartIndex = 0
    var hashTagEndIndex = 0
    originalText.toCharArray(startIndex = contentStartIndex).let {
        val contentLength = it.size
        it.forEachIndexed { index, char ->
            if (!isHashTagText && char == '#') {
                isHashTagText = true
                hashTagStartIndex = index + contentStartIndex
            } else if (isHashTagText
                && (char == '#' || char == ' ' || char == '\n' || index == contentLength - 1)
            ) {
                isHashTagText = false
                hashTagEndIndex = index + contentStartIndex
                spanStyles.add(
                    AnnotatedString.Range(
                        item = SpanStyle(
                            color = Color.Blue,
                            fontWeight = FontWeight(1000),
                        ),
                        start = hashTagStartIndex,
                        end = hashTagEndIndex
                    )
                )

                if (char == '#') {
                    isHashTagText = true
                    hashTagStartIndex = index + contentStartIndex
                }
            }
        }
    }

    return spanStyles
}