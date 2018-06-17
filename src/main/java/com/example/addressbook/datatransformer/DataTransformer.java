package com.example.addressbook.datatransformer;

import com.example.addressbook.model.AddressBookContact;

import java.util.Optional;

/**
 * Parses the string given and transforms it into a AddressBookContact object
 */
public interface DataTransformer {

    /**
     *
     * @param input  string format: (Name, gender, dob)
     * @return      AddressBookContact optional
     */
    Optional<AddressBookContact> inputToAddressContact(final String input);
}
