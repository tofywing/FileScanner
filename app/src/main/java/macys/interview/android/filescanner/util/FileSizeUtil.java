package macys.interview.android.filescanner.util;

import java.text.DecimalFormat;

/**
 * Created by Yee on 3/22/18.
 */

public class FileSizeUtil {

    private FileSizeUtil() {
    }

    public static String getReadableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
