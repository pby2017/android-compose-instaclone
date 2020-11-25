package com.bdragon.instacloneapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.bdragon.instacloneapp.ui.InstaCloneAppTheme
import com.bdragon.instacloneapp.ui.home.InstaHome
import com.bdragon.instacloneapp.viewmodel.InstaHomeViewModel

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

