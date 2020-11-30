package com.ashu.bsctask.data.items

import kotlinx.serialization.Serializable

@Serializable
data class PersonItem (val name: String = "", val imageURL: String = "")