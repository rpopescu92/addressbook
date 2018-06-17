package com.example.addressbook.service;

import com.example.addressbook.model.AddressBookContact;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Override
    public long count(List<AddressBookContact> addressBook,
                      Predicate<AddressBookContact> filterPredicate) {
        return addressBook.stream()
                          .filter(filterPredicate)
                          .count();
    }

    @Override
    public Optional<AddressBookContact> getOldestPerson(List<AddressBookContact> addressBook) {
        return addressBook.stream()
                          .min(Comparator.comparing(AddressBookContact::getDob));
    }

    @Override
    public long getDaysDifference(AddressBookContact firstContact, AddressBookContact secondContact) {
        return ChronoUnit.DAYS.between(firstContact.getDob(), secondContact.getDob());
    }

    @Override
    public Optional<AddressBookContact> searchEntryByName(List<AddressBookContact> contacts, String name) {
        return contacts.stream()
                       .filter(c -> c.getName().equals(name)).findAny();
    }
}
