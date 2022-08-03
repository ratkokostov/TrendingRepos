package com.example.firstapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.firstapp.model.Item
import com.example.firstapp.model.Post

@Composable
fun RepoItem(post: Item){
    Card(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .height(110.dp),
        shape = RoundedCornerShape(8.dp),
        elevation =  4.dp
    ){
        Surface(){
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()){
               AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(post.owner.avatarUrl)
                        .crossfade(true)
                        .build(),
                   contentDescription = post.description,
                   contentScale =  ContentScale.Crop,
                   modifier = Modifier
                       .clip(CircleShape)
                       .fillMaxHeight()
                       .weight(0.2f)
                   )
                Column(
                    verticalArrangement =  Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ){
                    Text(text = post.fullName,
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = post.name, style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp))

                    Text(text = post.description, style = MaterialTheme.typography.body1,maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }


        }

    }
}