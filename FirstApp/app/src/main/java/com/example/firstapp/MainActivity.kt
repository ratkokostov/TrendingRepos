package com.example.firstapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstapp.View.RepoItem
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.model.Item
import com.example.firstapp.model.Post
import com.example.firstapp.ui.theme.FirstAppTheme
import com.example.firstapp.viewModel.PostViewModel
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {

    private val postViewModel by viewModels<PostViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postViewModel.getPostsList()
        setContent {
            FirstAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    RepoList(repoList = postViewModel.postListResponse)
                }
            }
        }
    }
}

@Composable
fun RepoList(repoList: StateFlow<List<Item>>){
    LazyColumn{
        itemsIndexed(items = repoList.value){
            _, item ->  RepoItem(post = item)
        }
    }
    
    
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirstAppTheme {
    }
}