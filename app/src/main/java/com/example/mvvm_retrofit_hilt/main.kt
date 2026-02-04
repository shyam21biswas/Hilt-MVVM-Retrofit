package com.example.mvvm_retrofit_hilt


import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import dagger.hilt.android.HiltAndroidApp
import retrofit2.http.GET

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel



data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)



interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}



class PostRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }
}



@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    // Ideally, use a sealed class for UI State (Loading, Success, Error)
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val result = repository.getPosts()
                _posts.value = result
            } catch (e: Exception) {
                // Handle error (e.g., No internet)
                e.printStackTrace()
            }
        }
    }
}



@HiltAndroidApp
class MyApplication : Application()



/*
@Composable
fun PostScreen(viewModel: PostViewModel = hiltViewModel()) {
    val posts = viewModel.posts.collectAsState()


    LazyColumn {
        items(posts.value) { post ->
            Text(text = post.title)
            // Add styling and more details here
        }
    }
}

*/

//... (rest of your code: Post, ApiService, etc.)

@Composable
fun PostScreen(viewModel: PostViewModel = hiltViewModel()) {
    val posts = viewModel.posts.collectAsState()

    // Use the key parameter for better performance during list updates
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp) // Add some horizontal padding
    ) {
        items(items = posts.value, key = { it.id }) { post ->
            // Use ListItem for a standard, structured look
            ListItem(
                headlineContent = {
                    Text(
                        text = post.title,
                        // style = MaterialTheme.typography.titleMedium // Optional: Apply typography
                    )
                },
                supportingContent = {
                    Text(
                        text = post.body,
                        // style = MaterialTheme.typography.bodySmall, // Optional: Apply typography
                        maxLines = 2 // Limit body text to 2 lines
                    )
                }
            )
            // Add a divider between items for clarity
            HorizontalDivider()
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant

            )
        }
    }
}

