package com.example.kotlincoroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlincoroutine.models.Post
import com.example.kotlincoroutine.network.JsonPlaceHolderService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val postsLiveData = MutableLiveData<List<Post>>()

    private val retrofit = Retrofit.Builder()
        .baseUrl(JsonPlaceHolderService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit
        .create<JsonPlaceHolderService>(JsonPlaceHolderService::class.java)

    init {
        isLoading.value = false
    }


    fun getPosts() {
        // 비동기 네트워크 요청
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            // 여기부턴 백스라운드 작업
            val posts = service.getPosts()

            postsLiveData.postValue(posts)
            isLoading.postValue(false)
        }
    }

}