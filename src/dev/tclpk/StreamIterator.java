package dev.tclpk;

import java.io.IOException;
import java.io.InputStream;

public interface StreamIterator {
    public StreamIterator setStream(InputStream stream);
    public String getNext() throws IOException;
}
