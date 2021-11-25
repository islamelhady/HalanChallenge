package com.example.halanchallenge.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.data.model.ProductsList
import com.example.halanchallenge.data.repositories.ChallengeRepository
import com.example.halanchallenge.util.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductsListViewModel(private val repository: ChallengeRepository) : ViewModel() {

    private val _productInfo = MutableLiveData<State<ProductsList>>()
    val productInfo : LiveData<State<ProductsList>>
        get() = _productInfo

    fun getProductsInfo(token : String?){
        viewModelScope.launch {
            repository.getProductsInfo(token).collect {
                _productInfo.value = it
            }
        }
    }
}