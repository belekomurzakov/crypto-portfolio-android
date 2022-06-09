package com.omurzakov.cryptoportfolio.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.omurzakov.cryptoportfolio.database.ICryptoLocalRepository
import com.omurzakov.cryptoportfolio.model.Crypto

class PriceListViewModel(private val repository: ICryptoLocalRepository) : ViewModel() {

    fun getAll(): LiveData<MutableList<Crypto>> = repository.getAll()
}