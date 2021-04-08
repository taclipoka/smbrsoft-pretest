package dev.tclpk;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ScannerStreamIterator implements StreamIterator{
    private Scanner scanner;
    private String delimiters = "[\n\t\r]";
    @Override
    public StreamIterator setStream(InputStream stream) {
        scanner = new Scanner(stream);
        scanner.useDelimiter(delimiters);
        return this;
    }

    @Override
    public String getNext() throws IOException {
        return scanner.next();
    }

    public StreamIterator setDelimiters(String delimiters) {
        this.delimiters = delimiters;
        return this;
    }

    public String getDelimiters() {
        return delimiters;
    }

}
