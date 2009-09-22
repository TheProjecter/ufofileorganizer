package utils.file;

import java.io.File;
import java.util.logging.Logger;

/**
 * @author Esko Luontola
 * @since 17.6.2008
 */
public class TempDirectory {

    private static final Logger log = Logger.getLogger(TempDirectory.class.getName());

    public static final String PREFIX = TempDirectory.class.getSimpleName() + ".";

    private File directory;

    public TempDirectory() {
        this(null);
    }

    public TempDirectory(File directory) {
        this.directory = directory;
    }

    public File getDirectory() {
        return directory;
    }

    public void create() {
        if (directory == null) {
            directory = nonExistingTempDir();
        }
        if (directory.exists()) {
            throw new IllegalArgumentException("Already exists: " + directory);
        }
        if (!directory.mkdir()) {
            throw new RuntimeException("Unable to create directory: " + directory);
        }
    }

    private static File nonExistingTempDir() {
        int i = 0;
        File dir;
        do {
            i++;
            dir = new File(System.getProperty("java.io.tmpdir"), PREFIX + i);
        } while (dir.exists());
        return dir;
    }

    public void dispose() {
        deleteRecursively(directory);
    }

    private static void deleteRecursively(File file) {
        if (file.isDirectory()) {
            for (File contained : file.listFiles()) {
                deleteRecursively(contained);
            }
        }
        if (!file.delete()) {
            log.warning("Unable to delete file: " + file);
            anotherDeleteAttempt(file);
        }
    }

    private static void anotherDeleteAttempt(File file) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        file.delete();
        file.deleteOnExit();
        if (!file.exists()) {
            log.info("File was deleted on a second try: " + file);
        }
    }
}

