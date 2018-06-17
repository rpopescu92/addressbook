package com.example.addressbook.filereader;

import com.example.addressbook.model.AddressBookContact;

import java.nio.file.Path;
import java.util.List;

/**
 * Parses the content of the file and returns it in a list of AddressBookContacts
 */
public interface FileReader {

    /**
     *
     * @param path  the file path containing the data
     * @return      list of AddressBookContact
     */
    List<AddressBookContact> getContacts(final Path path);
}
