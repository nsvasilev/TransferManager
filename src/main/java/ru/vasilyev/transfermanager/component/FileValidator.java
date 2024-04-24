package ru.vasilyev.transfermanager.component;

import java.io.IOException;

public interface FileValidator {

    boolean checkStructure(String filename) throws IOException;

    boolean checkExtension(String filename);

}
