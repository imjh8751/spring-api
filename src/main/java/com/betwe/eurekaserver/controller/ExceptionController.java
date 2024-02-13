package com.betwe.eurekaserver.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/throwException")
    public void throwException(@RequestParam String exceptionName) throws SQLException, IOException, TimeoutException, ClassNotFoundException, InterruptedException {
        switch (exceptionName) {
            case "NullPointerException":
                throw new NullPointerException("This is a NullPointerException.");
            case "IndexOutOfBoundsException":
                throw new IndexOutOfBoundsException("This is an IndexOutOfBoundsException.");
            case "NumberFormatException":
                throw new NumberFormatException("This is a NumberFormatException.");
            case "IllegalArgumentException":
                throw new IllegalArgumentException("This is an IllegalArgumentException.");
            case "ArrayIndexOutOfBoundsException":
                throw new ArrayIndexOutOfBoundsException("This is an ArrayIndexOutOfBoundsException.");
            case "ClassCastException":
                throw new ClassCastException("This is a ClassCastException.");
            case "ArithmeticException":
                throw new ArithmeticException("This is an ArithmeticException.");
            case "UnsupportedOperationException":
                throw new UnsupportedOperationException("This is an UnsupportedOperationException.");
            case "ConcurrentModificationException":
                throw new ConcurrentModificationException("This is a ConcurrentModificationException.");
            case "NoSuchElementException":
                throw new NoSuchElementException("This is a NoSuchElementException.");
            case "IllegalStateException":
                throw new IllegalStateException("This is an IllegalStateException.");
            case "FileNotFoundException":
                throw new FileNotFoundException("This is a FileNotFoundException.");
            case "IOException":
                throw new IOException("This is an IOException.");
            case "SQLException":
                throw new SQLException("This is an SQLException.");
            case "TimeoutException":
                throw new TimeoutException("This is a TimeoutException.");
            case "InterruptedException":
                throw new InterruptedException("This is an InterruptedException.");
            case "ClassNotFoundException":
                throw new ClassNotFoundException("This is a ClassNotFoundException.");
            case "NoClassDefFoundError":
                throw new NoClassDefFoundError("This is a NoClassDefFoundError.");
            case "StackOverflowError":
                throw new StackOverflowError("This is a StackOverflowError.");
            case "OutOfMemoryError":
                throw new OutOfMemoryError("This is an OutOfMemoryError.");
            default:
                throw new IllegalArgumentException("Invalid exception name provided.");
        }
    }
}
