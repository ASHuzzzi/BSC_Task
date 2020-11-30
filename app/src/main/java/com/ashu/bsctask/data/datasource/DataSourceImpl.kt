package com.ashu.bsctask.data.datasource

import com.ashu.bsctask.Application
import com.ashu.bsctask.core.datatype.Result
import com.ashu.bsctask.data.DataSource
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class DataSourceImpl: DataSource {

    override fun getData(): Result<String> {
        return try {
            val reader = BufferedReader(InputStreamReader(
                Application.instance.assets.open("JsonFull.txt"),
                "UTF-8"))
            val result = reader.readLine()
            if (result.isNullOrEmpty()) {
                throw Exception("Empty File")
            } else {
                Result.success(result)
            }
        } catch (exception: IOException) {
            Result.error(exception)
        } catch (exception: Exception) {
            Result.error(exception)
        }

    }
}