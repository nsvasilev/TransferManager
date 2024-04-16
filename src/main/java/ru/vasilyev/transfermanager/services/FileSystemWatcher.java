package ru.vasilyev.transfermanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.boot.devtools.filewatch.SnapshotStateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

public class FileSystemWatcher {
    private final boolean daemon;
    private final long pollInterval;
    private final long quietPeriod;
    private final SnapshotStateRepository snapshotStateRepository;
    private final List<FileChangeListener> listeners;
    private final Object monitor;
    private Thread watchThread;

public FileSystemWatcher(boolean daemon, long pollInterval, long quietPeriod, SnapshotStateRepository snapshotStateRepository, List<FileChangeListener> listeners, Object monitor) {
        this.daemon = daemon;
        this.pollInterval = pollInterval;
        this.quietPeriod = quietPeriod;
        this.snapshotStateRepository = snapshotStateRepository;
        this.listeners = listeners;

        this.monitor = monitor;
    }
    public void addListener(FileChangeListener fileChangeListener) {
        Assert.notNull(fileChangeListener, "FileChangeListener must not be null");
        synchronized(this.monitor) {
            this.checkNotStarted();
            this.listeners.add(fileChangeListener);
        }
    }

    private void checkNotStarted() {
        Assert.state(this.watchThread == null, "FileSystemWatcher already started");
    }
}
