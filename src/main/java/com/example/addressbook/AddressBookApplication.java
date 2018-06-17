package com.example.addressbook;

import com.example.addressbook.datatransformer.DataTransformer;
import com.example.addressbook.datatransformer.DataTransfornerImpl;
import com.example.addressbook.filereader.FileReader;
import com.example.addressbook.filereader.FileReaderImpl;
import com.example.addressbook.results.PrintResults;
import com.example.addressbook.service.AddressBookService;
import com.example.addressbook.service.AddressBookServiceImpl;

public class AddressBookApplication {
    public static void main(String[] args) {
        final DataTransformer dataTransformer = new DataTransfornerImpl();
        final FileReader inputReader = new FileReaderImpl(dataTransformer);
        final AddressBookService addressBookService = new AddressBookServiceImpl();

        final PrintResults addressBook = new PrintResults(inputReader, addressBookService);

        addressBook.printResults();
    }
}
