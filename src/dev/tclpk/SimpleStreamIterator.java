package dev.tclpk;

import java.io.*;

public class SimpleStreamIterator implements StreamIterator {
    private Reader reader;
    private int maxWordSize;
    private boolean caseSensitivity;
    private String delimiters = "\n\r\t .,:;!?\"[]{}";
    private StringBuilder word;

    public SimpleStreamIterator(){
        this(false);
    }

    public SimpleStreamIterator(boolean caseSensitivity)
    {
        this(caseSensitivity, 1000);
    }

    public SimpleStreamIterator(boolean caseSensitivity, int maxWordSize)
    {
        this.caseSensitivity = caseSensitivity;
        this.maxWordSize = maxWordSize;
    }

    private boolean isDelimiter(int ch) {
        return getDelimiters().indexOf(ch) != -1;
    }

    public String getNext() throws IOException {
        if (reader == null)
            return "";

        if (word == null)
            word = new StringBuilder(this.maxWordSize);
        else
            word.setLength(0);
        boolean gotWord = false;
        while (!gotWord) {
            int ch = reader.read();

            if (ch == -1) {
                gotWord = true;
                reader.close();
                reader = null;
            }
            else if (isDelimiter(ch)) {
                if (word.length() > 0)
                    gotWord = true;
            }
            else
                word.append((char) ch);

            if (word.length() >= this.maxWordSize)
                gotWord = true;
        }

        String result = word.toString();

        if (!this.caseSensitivity)
            result = result.toLowerCase();
        return result;
    }

    public String getDelimiters() {
        return delimiters;
    }

    public SimpleStreamIterator setDelimiters(String delimiters) {
        this.delimiters = delimiters;
        return this;
    }

    public SimpleStreamIterator setStream(InputStream stream) {
        this.reader = new BufferedReader(new InputStreamReader(stream));
        return this;
    }
}
