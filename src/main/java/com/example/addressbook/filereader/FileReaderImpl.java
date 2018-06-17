package com.example.addressbook.filereader;

import com.example.addressbook.model.AddressBookContact;
import com.example.addressbook.datatransformer.DataTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderImpl implements FileReader {

    private DataTransformer dataTransformer;
    private static Logger logger = LoggerFactory.getLogger(FileReaderImpl.class);

    public FileReaderImpl(DataTransformer dataTransformer) {
        this.dataTransformer = dataTransformer;
    }

    @Override
    public List<AddressBookContact> getContacts(Path path) {
        try{
            try(final Stream<String> lines = Files.lines(path)) {
               return lines.map(dataTransformer::inputToAddressContact)
                        .flatMap(optional -> optional.isPresent() ? Stream.of(optional.get()) : Stream.empty())
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
           logger.error(String.format("Cannot read file with path %s.", path));
        }

        return Collections.emptyList();
    }
}