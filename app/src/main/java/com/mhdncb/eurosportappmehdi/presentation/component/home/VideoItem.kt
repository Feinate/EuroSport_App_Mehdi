package com.mhdncb.eurosportappmehdi.presentation.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mhdncb.eurosportappmehdi.R
import com.mhdncb.eurosportappmehdi.domain.entity.Video

@Composable
fun VideoItem(
    video: Video,
    onVideoClicked: (String) -> Unit
) {
    val context = LocalContext.current

    ArticleItem(
        cardBox = {
            Box(
                modifier = Modifier
                    .matchParentSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(video.thumb)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = video.title
                )
                Image(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.drawable.play),
                    contentDescription = "Play"
                )
            }
        },
        tag = video.sport.name,
        title = video.title,
        subTitle = "${video.views} views",
        onCardClick = {
            onVideoClicked(video.url)
        }
    )
}