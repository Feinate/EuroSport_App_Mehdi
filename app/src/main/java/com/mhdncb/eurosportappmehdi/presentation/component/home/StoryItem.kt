package com.mhdncb.eurosportappmehdi.presentation.component.home

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mhdncb.eurosportappmehdi.domain.entity.Story

@Composable
fun StoryItem(
    story: Story,
    onStoryClicked: (Story) -> Unit
) {
    ArticleItem(
        cardBox = {
            Box(
              modifier = Modifier
                  .matchParentSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(story.image)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = story.title
                )
            }
        },
        tag = story.sport.name,
        title = story.title,
        subTitle = "By ${story.author} - ${story.date}",
        onCardClick = {
            onStoryClicked(story)
        }
    )
}