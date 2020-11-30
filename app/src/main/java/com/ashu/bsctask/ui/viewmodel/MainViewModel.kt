package com.ashu.bsctask.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ashu.bsctask.core.datatype.Result
import com.ashu.bsctask.core.datatype.ResultType
import com.ashu.bsctask.domain.DomainImpl
import com.ashu.bsctask.domain.entities.Party
import com.ashu.bsctask.ui.Domain
import kotlinx.coroutines.*

class MainViewModel(application: Application): AndroidViewModel(application)  {

    private lateinit var domain: Domain
    private val job = Job()
    private val coroutine = CoroutineScope(Dispatchers.IO + job)
    private val partyLiveData: MutableLiveData<Party> = MutableLiveData()
    private val errorLiveData: MutableLiveData<String> = MutableLiveData()
    private lateinit var readyParty: Result<Party>

    fun getParty() {
        if (this::readyParty.isInitialized) {
            checkPartyAndUpdateLiveData(readyParty)
        } else {
            coroutine.launch {
                domain = DomainImpl()
                val result = domain.getParty()
                withContext(Dispatchers.Main) {
                    readyParty = result
                    checkPartyAndUpdateLiveData(readyParty)
                }
            }
        }
    }

    fun partyLiveData() = partyLiveData

    fun errorLiveData() = errorLiveData

    private fun checkPartyAndUpdateLiveData(readyParty: Result<Party>) {
        if (readyParty.resultType == ResultType.SUCCESS) {
            partyLiveData.postValue(readyParty.data)
        } else {
            errorLiveData.postValue(readyParty.error?.message)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}