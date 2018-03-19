package eu.mapidev.javafx.backup.service;

import java.io.File;

public interface SimpleBackupService {

    boolean startBackup(final File source, final File destination);

    void stopBackup();

}
