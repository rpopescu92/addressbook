package com.example.addressbook.filereader;

import com.example.addressbook.model.AddressBookContact;
import com.example.addressbook.datatransformer.DataTransformer;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.addressbook.model.Gender.MALE;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileReaderTest {

    public static final String VALID_FILE_PATH = "src/test/resources/valid_input.txt";
    public static final String INVALID_FILE_PATH = "src/test/resources/invalid_input.txt";

    private FileReaderImpl fileReader;
    private DataTransformer dataTransformer;

    @Before
    public void setup() {
        dataTransformer = mock(DataTransformer.class);
        fileReader = new FileReaderImpl(dataTransformer);
    }

    @Test
    public void testFileIsParsed() {
        Path path = Paths.get(VALID_FILE_PATH);
        final Optional<AddressBookContact> expectedContact = Optional.of(new AddressBookContact("James Bond", MALE, LocalDate.now()));

        when(dataTransformer.inputToAddressContact(anyString())).thenReturn(expectedContact);

        List<AddressBookContact> contacts = fileReader.getContacts(path);
        assertEquals(5, contacts.size());
    }

    @Test
    public void testFileIsNotParsedWhenInvalidContent() {
        Path path = Paths.get(INVALID_FILE_PATH);
        when(dataTransformer.inputToAddressContact(anyString())).thenReturn(Optional.empty());

        List<AddressBookContact> contacts = fileReader.getContacts(path);
        assertEquals(0, contacts.size());
    }

    @Test
    public void testFileIsNotParsedWhenInvalidPath() {
        Path path = Paths.get("/invalidpath");
        when(dataTransformer.inputToAddressContact(anyString())).thenReturn(Optional.empty());

        List<AddressBookContact> contacts = fileReader.getContacts(path);
        assertEquals(0, contacts.size());
    }

}
