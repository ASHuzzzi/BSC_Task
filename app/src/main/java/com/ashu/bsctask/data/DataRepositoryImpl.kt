package com.ashu.bsctask.data

import com.ashu.bsctask.core.datatype.Result
import com.ashu.bsctask.core.datatype.ResultType
import com.ashu.bsctask.data.datasource.DataSourceImpl
import com.ashu.bsctask.data.items.PartyItem
import com.ashu.bsctask.domain.DataRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DataRepositoryImpl: DataRepository {

    private val dataSourceImpl = DataSourceImpl()

    override fun getDataFromFile(): Result<PartyItem> {
        val result = dataSourceImpl.getData()

        return if (result.resultType == ResultType.SUCCESS && !result.data.isNullOrEmpty()) {
            getPartyFromRawData(result.data)
        } else {
            Result.error(result.error)
        }
    }

    private fun getPartyFromRawData(rawData: String): Result<PartyItem> {
        return try {
            Result.success(Json.decodeFromString(rawData))
        } catch (exception: Exception) {
            Result.error(exception)
        }
    }
}