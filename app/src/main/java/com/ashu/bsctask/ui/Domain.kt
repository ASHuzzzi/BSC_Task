package com.ashu.bsctask.ui

import com.ashu.bsctask.core.datatype.Result
import com.ashu.bsctask.domain.entities.Party

interface Domain {

    fun getParty(): Result<Party>
}