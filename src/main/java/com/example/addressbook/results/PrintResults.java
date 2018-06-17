package com.example.addressbook.results;

import com.example.addressbook.filereader.FileReader;
import com.example.addressbook.model.AddressBookContact;
import com.example.addressbook.model.Gender;
import com.example.addressbook.service.AddressBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class PrintResults {

    private static final String FILE_PATH = "src/main/resources/AddressBook";
    private static Logger logger = LoggerFactory.getLogger(PrintResults.class);

    private AddressBookService addressBookService;

    private FileReader fileReader;

    private List<AddressBookContact> contacts;

    public PrintResults(FileReader fileReader, AddressBookService addressBookService) {
        this.fileReader = fileReader;
        this.addressBookService = addressBookService;
    }

    public void printResults() {
        readFromFile();

        long countMales = addressBookService.count(contacts, m -> m.getGender() == Gender.MALE);
        System.out.println((String.format("Results::::::::: Number of males %s", countMales)));

        Optional<AddressBookContact> contact = addressBookService.getOldestPerson(contacts);
        System.out.println(String.format("Results::::::::: Older person is %s", contact.get()));

        final Optional<AddressBookContact> bill = addressBookService.searchEntryByName(contacts, "Bill McKnight");
        final Optional<AddressBookContact> paul = addressBookService.searchEntryByName(contacts, "Paul Robinson");
        long countDays = addressBookService.getDaysDifference(bill.get(), paul.get());

        System.out.println(String.format("Results::::::::: Days difference between Paul and Bill are %s", countDays));
    }

    private void readFromFile() {
       contacts = fileReader.getContacts(Paths.get(FILE_PATH));
    }
}
