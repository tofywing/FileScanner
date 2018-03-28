package macys.interview.android.filescanner.util;

/**
 * Created by Yee on 3/22/18.
 */

public class FileExtensionUtil {

    private FileExtensionUtil() {
    }

    public static String getExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}
