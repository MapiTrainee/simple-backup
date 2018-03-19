package eu.mapidev.javafx.backup.service;

import java.io.File;

public interface FileService {

    File getDestination();

    File getSource();

    void setDestination(File destination);

    void setSource(File source);

}
