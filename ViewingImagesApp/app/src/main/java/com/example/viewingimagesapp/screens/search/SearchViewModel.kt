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

    init {
        getImagesVM("random")
    }

    fun getImagesVM(query: String) {
        viewModelScope.launch {
            val body = withContext(Dispatchers.IO) {
                repo.getIm(query, 1).body()
            }
            _myImagesList.value = body?.results ?: emptyList()
        }
    }
}