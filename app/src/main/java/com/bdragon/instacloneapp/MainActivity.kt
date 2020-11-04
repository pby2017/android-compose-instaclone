package com.bdragon.instacloneapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.bdragon.instacloneapp.ui.InstaCloneAppTheme
import com.bdragon.instacloneapp.ui.home.InstaHome

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstaCloneAppTheme {
                InstaContent()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InstaCloneAppTheme {
        InstaContent()
    }
}

@Composable
fun InstaContent() {
    InstaHome()
}

