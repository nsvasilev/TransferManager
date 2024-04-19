package ru.vasilyev.transfermanager.interfaces;

public interface FileValidator {

    boolean checkStructure(String filename);

    boolean checkExtension(String filename);

}
