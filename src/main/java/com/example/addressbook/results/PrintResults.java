package com.example.addressbook.results;

import com.example.addressbook.filereader.FileReader;
import com.example.addressbook.model.AddressBookContact;
import com.example.addressbook.model.Gender;
import com.example.addressbook.service.AddressBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Component
public class PrintResults {

    private static final String FILE_PATH = "src/main/resources/AddressBook";
    private static Logger logger = LoggerFactory.getLogger(PrintResults.class);

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private FileReader fileReader;

    private List<AddressBookContact> contacts;

    @PostConstruct
    public void printResults() {
        readFromFile();

        long countMales = addressBookService.count(contacts, m -> m.getGender() == Gender.MALE);
        logger.info(String.format("Results::::::::: Number of males %s", countMales));

        Optional<AddressBookContact> contact = addressBookService.getOldestPerson(contacts);
        logger.info(String.format("Results::::::::: Older person is %s", contact.get()));

        final Optional<AddressBookContact> bill = addressBookService.searchEntryByName(contacts, "Bill McKnight");
        final Optional<AddressBookContact> paul = addressBookService.searchEntryByName(contacts, "Paul Robinson");
        long countDays = addressBookService.getDaysDifference(bill.get(), paul.get());

        logger.info(String.format("Results::::::::: Days difference between Paul and Bill are %s", countDays));
    }

    private void readFromFile() {
       contacts = fileReader.getContacts(Paths.get(FILE_PATH));
    }
}
