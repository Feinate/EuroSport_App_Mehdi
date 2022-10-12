package com.mhdncb.eurosportappmehdi.presentation.screen.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mhdncb.eurosportappmehdi.domain.entity.Story
import com.mhdncb.eurosportappmehdi.presentation.component.home.TagChip

@Composable
fun Details(
    story: Story,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (ArticleBox, tagBox, titleBox, subtitleBox) = createRefs()

                HeaderImage(ArticleBox, story)
                TagChip(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .constrainAs(tagBox) {
                            top.linkTo(ArticleBox.bottom)
                            start.linkTo(parent.start)
                            bottom.linkTo(ArticleBox.bottom)
                        },
                    text = story.sport.name
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 12.dp, end = 12.dp, bottom = 8.dp)
                        .constrainAs(titleBox) {
                            top.linkTo(tagBox.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = story.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Black
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, bottom = 16.dp)
                        .fillMaxWidth()
                        .constrainAs(subtitleBox) {
                            top.linkTo(titleBox.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("By ")
                            withStyle(
                                SpanStyle(
                                    fontSize = 16.sp,
                                    color = Color.Blue.copy(alpha = 0.7f)
                                )
                            ) {
                                append(story.author)
                            }
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text(
                        text = story.date.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                text = story.teaser,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
        NavigationHeader(
            onBack = onBack
        )
    }
}

@Composable
private fun NavigationHeader(
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {
                onBack()
            }
        ) {
            Icon(modifier = Modifier.size(30.dp), painter = painterResource(id = com.mhdncb.eurosportappmehdi.R.drawable.back), contentDescription = "Back", tint = Color.White)
        }

        IconButton(
            onClick = {}
        ) {
            Icon(modifier = Modifier.size(30.dp), painter = painterResource(id = com.mhdncb.eurosportappmehdi.R.drawable.share), contentDescription = "Share", tint = Color.White)
        }
    }
}

@Composable
private fun ConstraintLayoutScope.HeaderImage(
    ArticleBox: ConstrainedLayoutReference,
    story: Story
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .constrainAs(ArticleBox) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
    ) {
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
    }
}