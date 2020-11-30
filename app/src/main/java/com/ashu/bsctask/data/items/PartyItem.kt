package com.ashu.bsctask.data.items

import kotlinx.serialization.Serializable

@Serializable
data class PartyItem (val partyName: String = "",
                      val partyImage: String = "",
                      val hostParty: PersonItem,
                      val guests: List<PersonItem>)