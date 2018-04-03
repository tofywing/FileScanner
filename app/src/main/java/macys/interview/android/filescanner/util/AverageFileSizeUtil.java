package macys.interview.android.filescanner.util;

/**
 * Created by Yee on 3/21/18.
 */

public class AverageFileSizeUtil {

    private long size = 0;
    private long count = 0;
    private long average = 0;

    private AverageFileSizeUtil() {
    }

    public void updateAverage(long size) {
        this.count++;
        this.size += size;
        this.average = this.size / count;
    }

    public long getAverage() {
        return average;
    }

    public static AverageFileSizeUtil builder() {
        return new AverageFileSizeUtil();
    }
    public void setAverage(long average) {
        this.average = average;
    }
}
