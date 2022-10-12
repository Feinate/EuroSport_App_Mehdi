package com.mhdncb.eurosportappmehdi.presentation.screen.home

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdncb.eurosportappmehdi.domain.entity.MixedArticles
import com.mhdncb.eurosportappmehdi.domain.entity.Story
import com.mhdncb.eurosportappmehdi.domain.entity.Video
import com.mhdncb.eurosportappmehdi.domain.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: ArticlesRepository
) : ViewModel() {

    // Loading
    private val _loadingPost: MutableState<Boolean> = mutableStateOf(false)
    val loadingPost: State<Boolean> = _loadingPost

    // Post
    private val _mixedArticles = mutableStateListOf(MixedArticles())
    val mixedArticles: List<MixedArticles> = _mixedArticles

    init {
        getArticles()
    }

    private fun getArticles() {
        _loadingPost.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = postRepository.getArticles()
                val body = response.body()
                body?.let { post ->
                    val videoSorted = post.videos.sortedBy { it.date }
                    val storySorted = post.stories.sortedBy { it.date }
                    val size = videoSorted.size + storySorted.size
                    for (i in 0..size) {
                        _mixedArticles.add(
                            MixedArticles(
                                video = if (i < videoSorted.size) videoSorted[i] else Video(),
                                story = if (i < storySorted.size) storySorted[i] else Story()
                            )
                        )
                    }
                }
                _loadingPost.value = false
            } catch (e: java.lang.Exception) {
                Log.e("TagPostError", e.message.toString())
                _loadingPost.value = false
            }
        }
    }
}