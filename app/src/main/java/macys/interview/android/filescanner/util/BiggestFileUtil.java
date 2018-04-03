package macys.interview.android.filescanner.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import macys.interview.android.filescanner.model.ScanFile;

/**
 * Created by Yee on 3/21/18.
 */

public class BiggestFileUtil {

    /**
     * Desired amount of biggest tile
     *
     * @value current requires top 10 biggest file
     */
    private static final int DESIRED_SIZE = 10;

    private List<ScanFile> biggestFileList = new ArrayList<>();

    private BiggestFileUtil() {
    }

    public static BiggestFileUtil builder() {
        return new BiggestFileUtil();
    }

    public void updateBiggestFileList(ScanFile file) {
        if (biggestFileList.size() < DESIRED_SIZE) {
            biggestFileList.add(file);
            if (biggestFileList.size() == DESIRED_SIZE) {
                SortUtil.sizeSortUtil(biggestFileList);
            }
        } else {
            if (file.getSize() > biggestFileList.get(0).getSize()) {
                biggestFileList.remove(0);
                biggestFileList.add(file);
                SortUtil.sizeSortUtil(biggestFileList);
            }
        }
    }

    public List<ScanFile> getBiggestFileList() {
        return biggestFileList;
    }

    public void setBiggestFileList(List<ScanFile> biggestFileList) {
        this.biggestFileList = biggestFileList;
    }
}
