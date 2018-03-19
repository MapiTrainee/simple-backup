package eu.mapidev.javafx.backup.service;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class SimpleBackupServiceImpl implements SimpleBackupService {

    private Thread copyThread = null;
    private int sleepTimeMillis = 10_000;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public SimpleBackupServiceImpl() {
    }

    @Override
    public boolean startBackup(final File source, final File destination) {
	if (copyThread == null && source != null && destination != null) {
	    copyThread = new Thread(new Runnable() {
		@Override
		public void run() {
		    try {
			//FileUtils.cleanDirectory();
			while (!Thread.currentThread().isInterrupted()) {
			    FileUtils.copyDirectory(source, destination, true);
			    Thread.sleep(sleepTimeMillis);
			}
		    } catch (IOException | InterruptedException ex) {
			logger.log(Level.SEVERE, null, ex);
		    }
		}
	    });
	    copyThread.start();
	    return true;
	}
	return false;
    }

    @Override
    public void stopBackup() {
	copyThread.interrupt();
	copyThread = null;
    }

}
