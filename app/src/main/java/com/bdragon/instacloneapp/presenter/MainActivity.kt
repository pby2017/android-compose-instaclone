package com.bdragon.instacloneapp.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.bdragon.instacloneapp.presenter.ui.InstaCloneAppTheme
import com.bdragon.instacloneapp.presenter.ui.home.InstaHome
import com.bdragon.instacloneapp.presenter.viewmodel.InstaHomeViewModel

class MainActivity : AppCompatActivity() {
    private val instaHomeViewModel = InstaHomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InstaCloneAppTheme {
                InstaContent(instaHomeViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val instaHomeViewModel = InstaHomeViewModel()

//    InstaCloneAppTheme {
//        InstaContent(instaHomeViewModel)
//    }
}

@Composable
fun InstaContent(instaHomeViewModel: InstaHomeViewModel) {
    InstaHome(instaHomeViewModel)
}

