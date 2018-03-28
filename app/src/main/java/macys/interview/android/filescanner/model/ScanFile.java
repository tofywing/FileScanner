package macys.interview.android.filescanner.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yee on 3/21/18.
 */

public class ScanFile implements Parcelable {

    private String name;
    private long size;
    private String extension;

    public ScanFile(String name, long size, String extension) {
        this.name = name;
        this.size = size;
        this.extension = extension;
    }

    private ScanFile(Parcel in) {
        name = in.readString();
        size = in.readLong();
        extension = in.readString();
    }

    public static final Creator<ScanFile> CREATOR = new Creator<ScanFile>() {
        @Override
        public ScanFile createFromParcel(Parcel in) {
            return new ScanFile(in);
        }

        @Override
        public ScanFile[] newArray(int size) {
            return new ScanFile[size];
        }
    };

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(size);
        dest.writeString(extension);
    }
}
