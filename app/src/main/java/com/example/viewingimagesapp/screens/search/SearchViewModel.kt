package com.example.viewingimagesapp.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewingimagesapp.data.repository.Repository
import com.example.viewingimagesapp.model.ImagesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchViewModel : ViewModel() {

    var repo = Repository()

    private val _myImagesList: MutableLiveData<List<ImagesItem>> = MutableLiveData()
    val myImagesList: LiveData<List<ImagesItem>> = _myImagesList

    private val _isLastPage: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLastPage: LiveData<Boolean> = _isLastPage
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading
    private var page: Int = 1
    private var query: String = ""

    init {
        loadFirstPage("random")
    }

    fun onQueryChanged(query: String) = loadFirstPage(query)

    fun uploadPage(onLoaded: (List<ImagesItem>) -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val body = withContext(Dispatchers.IO) {
                    repo.getIm(query, page).body()
                }
                val loadedList = body?.results ?: emptyList()
                val currentList = _myImagesList.value ?: emptyList()
                currentList.toMutableList().addAll(loadedList)
                _myImagesList.value = currentList
                onLoaded.invoke(loadedList)
                page++
            } catch (exception: Exception) {

            }
        }
        _isLoading.value = false
    }

    private fun loadFirstPage(query: String) {
        _isLoading.value = true
        this@SearchViewModel.query = query
        viewModelScope.launch {
            try {
                page = 1
                val body = withContext(Dispatchers.IO) {
                    repo.getIm(query, page).body()
                }
                _myImagesList.value = body?.results ?: emptyList()
                page++
            } catch (exception: Exception) {
            }
        }
        _isLoading.value = false
    }
}