package com.ashu.bsctask.domain

import com.ashu.bsctask.core.datatype.Result
import com.ashu.bsctask.core.datatype.ResultType
import com.ashu.bsctask.ui.Domain
import com.ashu.bsctask.data.DataRepositoryImpl
import com.ashu.bsctask.data.items.PartyItem
import com.ashu.bsctask.data.items.PersonItem
import com.ashu.bsctask.domain.entities.Party
import com.ashu.bsctask.domain.entities.Person

class DomainImpl: Domain {

  private val repositoryImpl = DataRepositoryImpl()

  override fun getParty(): Result<Party> {
    val result = repositoryImpl.getDataFromFile()

    return if (result.resultType == ResultType.SUCCESS && result.data is PartyItem) {
      val party = getParty(result.data)
      Result.success(party)
    } else {
      Result.error(result.error)
    }
  }

  private fun getParty(partyItem: PartyItem): Party {
    return Party(
      partyItem.partyName,
      partyItem.partyImage,
      createHostParty(partyItem.hostParty),
      createGuests(partyItem.guests))
  }

  private fun createHostParty(personItem: PersonItem): Person {
    return Person(personItem.name, personItem.imageURL)
  }

  private fun createGuests(guestList: List<PersonItem>): List<Person> {
    val guests = ArrayList<Person>()
    for (guest in guestList) {
      guests.add(Person(guest.name, guest.imageURL))
    }
    return guests
  }
}