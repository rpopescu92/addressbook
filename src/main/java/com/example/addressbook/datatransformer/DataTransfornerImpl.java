package com.example.addressbook.datatransformer;

import com.example.addressbook.model.AddressBookContact;
import com.example.addressbook.model.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class DataTransfornerImpl implements DataTransformer {

    private static final String DATE_PATTERN = "dd/MM/yy";
    private static final String SEPARATOR = ",";
    private static Logger logger = LoggerFactory.getLogger(DataTransfornerImpl.class);

    @Override
    public Optional<AddressBookContact> inputToAddressContact(final String input) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            String[] splitInput = input.split(SEPARATOR);
            String name = splitInput[0].trim();
            Gender gender = Gender.stringToEnum(splitInput[1].trim());
            LocalDate dob = LocalDate.parse(splitInput[2].trim(), formatter);
            return Optional.of(new AddressBookContact(name, gender, dob));
        } catch (Exception ex) {
            logger.debug(String.format("Unable to parse string input %s", input));
        }

        return Optional.empty();
    }
}
