package eu.mapidev.javafx.backup.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileServiceImpl implements FileService {

    private Properties properties = new Properties();
    private static final String SRC_KEY = "src";
    private static final String DEST_KEY = "dest";

    private File source;
    private File destination;

    private static final String CONFIG_FILENAME = "backup.config";
    private File config = new File(CONFIG_FILENAME);

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public FileServiceImpl() {
	try (InputStream inputStream = new FileInputStream(createConfigFileIfNotExist())) {

	    properties.load(inputStream);
	    if (properties.containsKey(SRC_KEY)) {
		source = new File(properties.getProperty(SRC_KEY));
	    }
	    if (properties.containsKey(DEST_KEY)) {
		destination = new File(properties.getProperty(DEST_KEY, System.getProperty("user.dir")));
	    }
	} catch (FileNotFoundException ex) {
	    logger.log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    logger.log(Level.SEVERE, null, ex);
	}
    }

    private File createConfigFileIfNotExist() throws IOException {
	if (!config.exists()) {
	    config.createNewFile();
	}
	return config;
    }

    private void savePropertiesToConfigFile() {
	try (OutputStream outputStream = new FileOutputStream(config)) {
	    properties.store(outputStream, null);
	} catch (FileNotFoundException ex) {
	    logger.log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    logger.log(Level.SEVERE, null, ex);
	}
    }

    @Override
    public void setSource(File source) {
	if (source != null) {
	    properties.setProperty(SRC_KEY, source.getAbsolutePath());
	    savePropertiesToConfigFile();
	    this.source = source;
	}
    }

    @Override
    public File getSource() {
	return this.source;
    }

    @Override
    public void setDestination(File destination) {
	if (destination != null) {
	    properties.setProperty(DEST_KEY, destination.getAbsolutePath());
	    savePropertiesToConfigFile();
	    this.destination = destination;
	}
    }

    @Override
    public File getDestination() {
	return this.destination;
    }

}
