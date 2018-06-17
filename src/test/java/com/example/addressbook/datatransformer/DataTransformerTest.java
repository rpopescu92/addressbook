package com.example.addressbook.datatransformer;

import com.example.addressbook.model.AddressBookContact;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.example.addressbook.model.Gender.MALE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DataTransformerTest {

    private DataTransfornerImpl dataTransforner;
    private static final String DATE_PATTERN = "dd/MM/yy";
    private DateTimeFormatter formatter;

    @Before
    public void setup() {
        dataTransforner = new DataTransfornerImpl();
        formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    }

    @Test
    public void testTransformFromCorrectInput() {
        String input = "Bill McKnight, Male, 16/03/77";
        Optional<AddressBookContact> optional = dataTransforner.inputToAddressContact(input);
        AddressBookContact contact = optional.get();

        assertNotNull(contact);
        assertEquals("Bill McKnight", contact.getName());
        assertEquals(MALE, contact.getGender());
        assertEquals(LocalDate.parse("16/03/77", formatter), contact.getDob());
    }

    @Test
    public void testTransformFromInvalidInput() {
        String input = " incorrectInput";
        Optional<AddressBookContact> optional = dataTransforner.inputToAddressContact(input);
        assertFalse(optional.isPresent());
    }

    @Test
    public void testTransformFromInvalidInputDate() {
        String input = "Bill McKnight, Male, 16/03";
        Optional<AddressBookContact> optional = dataTransforner.inputToAddressContact(input);
        assertFalse(optional.isPresent());
    }
}
