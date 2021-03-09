package com.foxminded.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileToListConverter {

    public List<String> read(String fileName) throws URISyntaxException, IOException {
        URL fileUrl = this.getClass().getClassLoader().getResource(fileName);

        Stream<String> fileLines;
        fileLines = Files.lines(Paths.get(fileUrl.toURI()));
        return fileLines.collect(Collectors.toList());
    }

}

