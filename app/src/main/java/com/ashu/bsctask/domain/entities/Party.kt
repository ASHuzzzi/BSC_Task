package com.ashu.bsctask.domain.entities

data class Party (val partyName: String = "", val hostParty: Person, val guests: List<Person>)