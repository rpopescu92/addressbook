package com.example.addressbook.service;

import com.example.addressbook.model.AddressBookContact;
import com.example.addressbook.model.Gender;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.example.addressbook.model.Gender.MALE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AddressBookServiceTest {

    private AddressBookServiceImpl addressBookService;
    private List<AddressBookContact> contacts;

    @Before
    public void setup() {
        addressBookService = new AddressBookServiceImpl();
    }

    @Test
    public void countShouldReturn2Males() {
        final Predicate<AddressBookContact> filterPredicate = entry -> entry.getGender() == MALE;
        long males = addressBookService.count(createContactsList(), filterPredicate);

        assertEquals(2L, males);
    }

    @Test
    public void getOldestPersonShouldReturnOldestPerson() {
        final List<AddressBookContact> addressBook = createContactsList();

        final Optional<AddressBookContact> oldestPerson = addressBookService.getOldestPerson(addressBook);

        assertTrue(oldestPerson.isPresent());
        assertEquals("Jane Doe", oldestPerson.get().getName());
    }

    @Test
    public void getDaysDifferenceShouldReturn25DaysDifference() {
        final AddressBookContact contact1 = new AddressBookContact("Peter Pan", MALE, LocalDate.of(1977, 2, 2));
        final AddressBookContact contact2 = new AddressBookContact("Paul Robinson", MALE, LocalDate.of(1977, 2, 27));

        long daysDifference = addressBookService.getDaysDifference(contact1, contact2);
        assertEquals(25, daysDifference);
    }

    @Test
    public void searchByName() {
        Optional<AddressBookContact> optionalContact = addressBookService.searchEntryByName(createContactsList(), "Jane Doe");
        assertTrue(optionalContact.isPresent());
        assertEquals("Jane Doe", optionalContact.get().getName());
    }

    private List<AddressBookContact> createContactsList() {
        String DATE_PATTERN = "dd/MM/yy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);;
        contacts = Arrays.asList(new AddressBookContact("James Bond", MALE, LocalDate.parse("12/03/77", formatter)),
                new AddressBookContact("Jane Doe", Gender.FEMALE, LocalDate.parse("14/02/77", formatter)),
                new AddressBookContact("Peter Pan", MALE, LocalDate.parse("27/02/92", formatter)));

        return contacts;
    }
}
