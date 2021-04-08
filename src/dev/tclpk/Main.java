package dev.tclpk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger("dev.tclpk.main");
    private static final String DEFAULT_ADDRESS = "https://filesamples.com/samples/document/txt/sample1.txt";
    private static final String DEFAULT_DELIMITERS = "\n\r\t .,:;!?\"[]{}<>/'";
    private static ArrayList<String> addressList;
    private static String delimiters;
    private static boolean useScanner = false;
    public static void processArgs(String[] args){
        addressList = new ArrayList<>();
        String mode = "";
        for (String s: args) {
            logger.fine("Arg: " + s);
            if (s.equals("-d"))
                mode = "d";
            else if (s.equals("-s"))
                useScanner = true;
            else if (mode.equals("d")) {
                delimiters = s.replace("\\n", "\n")
                        .replace("\\r", "\r")
                        .replace("\\t", "\t")
                        .replace("\\\"", "\"");
                mode = "";
            } else
                addressList.add(s);
        }

        if (addressList.isEmpty())
            addressList.add(DEFAULT_ADDRESS);

        if (delimiters == null)
            delimiters = DEFAULT_DELIMITERS;
    }
    public static void main(String[] args) {

        processArgs(args);

        StreamIterator i;
        if (useScanner)
            i = new ScannerStreamIterator()
                    .setDelimiters(delimiters);
        else
            i = new SimpleStreamIterator()
                    .setDelimiters(delimiters);
        Splitter splitter = new Splitter(i);
        try {
            for (String address: addressList) {
                logger.info("Processing of " + address);
                splitter.processAddress(address);
                logger.info(address + " processed.");
            }
        } catch (IOException exception)
        {
            logger.severe("Error processing address");
            logger.severe(exception.toString());
        }
        if (!splitter.getWordList().isEmpty()) {
            logger.info("Result list:");
            splitter.getWordList().forEach((k,v) -> System.out.println(k + ": " + v));
            //splitter.getWordList().entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
        } else
            logger.info("Nothing to present");
    }
}
