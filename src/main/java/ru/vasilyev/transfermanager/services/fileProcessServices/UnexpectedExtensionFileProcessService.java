package ru.vasilyev.transfermanager.services.fileProcessServices;

import ru.vasilyev.transfermanager.utils.FileMover;

import java.io.File;

public class UnexpectedExtensionFileProcessService extends AbstractFileProcessService{
    public UnexpectedExtensionFileProcessService(FileMover fileMover) {
        super(fileMover);
    }

    @Override
    public void processFile(File fileName) throws Exception {

    }

    @Override
    public String getExtension() {
        return "unexpected";
    }
}
