package com.main.util;

import com.main.model.Employee;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FileUtils {
    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

    public static List<Employee> loadEmployeesFromCsvFile(String filePath) {

        List<Employee> employees = new ArrayList<>();

        File file = new File(filePath);

        try (Stream<String> stream = Files.lines(Paths.get(file.toURI()))) {

            employees = Arrays.stream(stream.toArray(String[]::new))
                    .map(line -> line.split(","))
                    .map(dataArr -> new Employee(Integer.valueOf(dataArr[0]), dataArr[1], Integer.valueOf(dataArr[2])))
                    .collect(toList());

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return employees;
    }
}
