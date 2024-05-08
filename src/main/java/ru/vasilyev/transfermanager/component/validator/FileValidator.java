package ru.vasilyev.transfermanager.component.validator;

import java.io.File;
import java.io.IOException;

public interface FileValidator {

    boolean checkStructure(File file) throws IOException;
}
