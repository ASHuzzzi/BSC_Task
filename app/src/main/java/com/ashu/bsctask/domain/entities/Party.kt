package com.ashu.bsctask.domain.entities

data class Party (val partyName: String = "",
                  val partyImage: String = "",
                  val hostParty: Person,
                  val guests: List<Person>)