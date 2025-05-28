package com.tcs.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SFTPService {

    @Async
    public void callSFTPWorker(String pathOfMoc, String mocFileName, String errorFileName) {
        // This code will now run in the background
        new SFTPRunnable(pathOfMoc, mocFileName, errorFileName).run();
    }
}