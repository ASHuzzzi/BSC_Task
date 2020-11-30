package com.ashu.bsctask.domain

import com.ashu.bsctask.core.datatype.Result
import com.ashu.bsctask.data.items.PartyItem

interface DataRepository {

    fun getDataFromFile(): Result<PartyItem>
}