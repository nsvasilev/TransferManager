package ru.vasilyev.transfermanager.services;

import org.springframework.stereotype.Service;

@Service
public class XlsxFileProcessService implements IFileProcessService {
    @Override
    public void processFile(String fileName) throws Exception {

    }

    @Override
    public String getExtension() {
        return "xlsx";
    }
}
