package com.example.addressbook.service;

import com.example.addressbook.model.AddressBookContact;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Operations on address book contacts list
 */
public interface AddressBookService {

    long count(List<AddressBookContact> addressBook, Predicate<AddressBookContact> filterPredicate);

    Optional<AddressBookContact> getOldestPerson(List<AddressBookContact> addressBook);

    long getDaysDifference(AddressBookContact first, AddressBookContact second);

    Optional<AddressBookContact> searchEntryByName(List<AddressBookContact> contacts, String name);
}
