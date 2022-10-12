package com.mhdncb.eurosportappmehdi.presentation.screen.home

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.mhdncb.eurosportappmehdi.R
import com.mhdncb.eurosportappmehdi.domain.entity.MixedArticles
import com.mhdncb.eurosportappmehdi.domain.entity.Story
import com.mhdncb.eurosportappmehdi.presentation.component.home.StoryItem
import com.mhdncb.eurosportappmehdi.presentation.component.home.VideoItem
import com.mhdncb.eurosportappmehdi.ui.theme.BlueDark
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    onStoryClicked: (Story) -> Unit
) {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val loading = viewModel.loadingPost

    val mixedPost = viewModel.mixedArticles
    
    val modalSheetState = rememberBottomSheetScaffoldState(bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    ))

    val url = rememberSaveable(String) { mutableStateOf("") }

    val playerExo = remember {
        ExoPlayer.Builder(context).build().apply {
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        }
    }

    val playerView = remember {
        StyledPlayerView(context).apply {
            player = playerExo
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            useController = true
            setShutterBackgroundColor(0x00FFFFFF)
            setShowBuffering(StyledPlayerView.SHOW_BUFFERING_ALWAYS)
        }
    }

    BackHandler {
        if (modalSheetState.bottomSheetState.isExpanded) {
            scope.launch {
                modalSheetState.bottomSheetState.collapse()
                stopAndClearPlayer(playerExo)
            }
        }
    }

    LaunchedEffect(modalSheetState.bottomSheetState.isExpanded) {
        if (url.value != "" && modalSheetState.bottomSheetState.isExpanded) {
            playerExo.setMediaItem(MediaItem.fromUri(Uri.parse(url.value)))
            playerExo.prepare()
            playerExo.playWhenReady = true
        }
    }

    BottomSheetScaffold(
        scaffoldState = modalSheetState,
        sheetBackgroundColor = Color.Black,
        sheetGesturesEnabled = false,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    factory = {
                        playerView
                    }
                )
                IconButton(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    onClick = {
                        scope.launch {
                            modalSheetState.bottomSheetState.collapse()
                            stopAndClearPlayer(playerExo)
                        }
                    }
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close",
                        tint = Color.White
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray.copy(alpha = 0.5f))
                .navigationBarsPadding(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                HeaderBar()
                if (loading.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.CircularProgressIndicator(
                            modifier = Modifier
                                .size(30.dp),
                            color = BlueDark,
                            strokeWidth = 3.dp
                        )
                    }
                } else {
                    PostList(
                        mixedPost = mixedPost,
                        onStoryClicked = {
                            onStoryClicked(it)
                        },
                        onVideoClicked = {
                            scope.launch {
                                url.value = it
                                modalSheetState.bottomSheetState.animateTo(BottomSheetValue.Expanded)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.PostList(
    mixedPost: List<MixedArticles>,
    onStoryClicked: (Story) -> Unit,
    onVideoClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.weight(1f),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(end = 8.dp, start = 8.dp, top = 12.dp)
    ) {
        items(
            items = mixedPost
        ) {
            if (it.story.id != 0) {
                StoryItem(it.story) { story ->
                    onStoryClicked(story)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
            if (it.video.id != 0) {
                VideoItem(video = it.video) { url ->
                    onVideoClicked(url)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun HeaderBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BlueDark)
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(modifier = Modifier.padding(vertical = 16.dp), text = stringResource(id = R.string.featured), fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

private fun stopAndClearPlayer(
    playerExoPlayer: ExoPlayer
) {
    playerExoPlayer.stop()
    playerExoPlayer.clearMediaItems()
}