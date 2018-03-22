package macys.interview.android.filescanner.util;

import android.util.Log;

import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import macys.interview.android.filescanner.model.ScanFile;
import macys.interview.android.filescanner.model.ScanResult;
import macys.interview.android.filescanner.model.ScanResultWrapper;

/**
 * Created by Yee on 3/21/18.
 */

public class FrequentExtensionsUtil {

    /**
     * Desired amount of frequent extensions
     *
     * @value current requires top 5 most frequent extensions
     */
    public static final int DESIRED_SIZE = 5;

    private List<ScanFile> mExtensionsList = new ArrayList<>();

    private FrequentExtensionsUtil() {
    }

    public void updateExtensionsList() {
        List<ScanFile> results = ScanResultWrapper.getInstance().getScanResult().getScanFileList();
        Map<String, Integer> frequencyMap = SortUtil.extensionFrequencySortUtil(results);
        List<String> frequencyList = new ArrayList<>();
        int size = DESIRED_SIZE;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            String extension = entry.getKey();
            Log.d("12", extension);
            extension += (" " + entry.getValue());
            frequencyList.add(extension);
            size--;
            if (size == 0) {
                break;
            }
        }
        ScanResultWrapper.getInstance().setFrequentFileList(frequencyList);
    }

    public List<ScanFile> getExtensionsList() {
        return mExtensionsList;
    }

    public static FrequentExtensionsUtil builder() {
        return new FrequentExtensionsUtil();
    }
}
