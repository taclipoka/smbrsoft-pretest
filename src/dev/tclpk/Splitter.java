package dev.tclpk;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Splitter {
    private Map<String, Integer> wordList = new HashMap<String, Integer>();
    private StreamIterator wordIterator;

    public Splitter() {
        this(new HashMap<String, Integer>());
    }

    public Splitter(Map<String, Integer> baseMap) {
        this.wordList = baseMap;
        this.wordIterator = new SimpleStreamIterator();
    }

    public Splitter(Map<String, Integer> baseMap, StreamIterator wordIterator){
        this.wordList = baseMap;
        this.wordIterator = wordIterator;
    }

    public Splitter(StreamIterator wordIterator){
        this();
        this.wordIterator = wordIterator;
    }

    public Map<String, Integer> getWordList() {
        return this.wordList;
    }

    private void addWord(String word)
    {
        Integer wordCount = this.wordList.get(word);
        if (wordCount == null)
            wordCount = 1;
        else
            wordCount +=1;
        this.wordList.put(word, wordCount);

    }

    public void processAddress(String address) throws IOException {
        URL url = new URL(address);
        processStream(url.openStream());
    }


    public void processStream(InputStream stream) throws IOException
    {
        wordIterator.setStream(stream);

        String word;
        while ((word = wordIterator.getNext()) != "") {
            addWord(word);
        }
    }

}
