package macys.interview.android.filescanner.util;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.JarInputStream;

import macys.interview.android.filescanner.model.ScanFile;

/**
 * Created by Yee on 3/21/18.
 */

class SortUtil {

    private SortUtil() {
    }

    static void sizeSortUtil(List<ScanFile> biggestFileList) {
        Collections.sort(biggestFileList, new Comparator<ScanFile>() {
            @Override
            public int compare(ScanFile o1, ScanFile o2) {
                long r = o1.getSize() - o2.getSize();
                if (r < 0) {
                    return -1;
                } else if (r == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    static Map<String, Integer> extensionFrequencySortUtil(List<ScanFile> scanResults) {
        Map<String, Integer> map = new TreeMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (ScanFile scanFile : scanResults) {
            String extension = FileExtensionUtil.getExtension(scanFile.getName());
            if (map.containsKey(extension)) {
                int frequency = map.get(extension) + 1;
                map.put(extension, frequency);
            } else {
                map.put(extension, 1);
            }
        }
        return map;
    }
}
