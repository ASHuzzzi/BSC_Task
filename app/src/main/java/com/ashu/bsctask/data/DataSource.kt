package com.ashu.bsctask.data

import com.ashu.bsctask.core.datatype.Result

interface DataSource {

    fun getData(): Result<String>
}