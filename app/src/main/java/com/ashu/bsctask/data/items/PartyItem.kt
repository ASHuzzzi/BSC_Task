package com.ashu.bsctask.data.items

import kotlinx.serialization.Serializable

@Serializable
data class PartyItem (val partyName: String = "",
                      val hostParty: PersonItem,
                      val guests: List<PersonItem>)