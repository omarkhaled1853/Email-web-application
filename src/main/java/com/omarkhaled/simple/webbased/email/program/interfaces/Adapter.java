package com.omarkhaled.simple.webbased.email.program.interfaces;

import java.io.IOException;
import java.nio.file.Path;

public interface Adapter<T> {
    void save(T object, String file) throws IOException;
    T load(String file) throws IOException;
}
