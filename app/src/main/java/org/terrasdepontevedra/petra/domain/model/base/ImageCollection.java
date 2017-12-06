package org.terrasdepontevedra.petra.domain.model.base;

import android.os.Parcel;

import org.terrasdepontevedra.petra.util.CollectionBase;

import java.util.ArrayList;
import java.util.List;

public class ImageCollection extends CollectionBase<Image> {

    public ImageCollection() {
        super();
    }

    private ImageCollection(Parcel in) {
        mList = new ArrayList<>();
        in.readList(mList, Image.class.getClassLoader());
    }

    public static final Creator<ImageCollection> CREATOR = new Creator<ImageCollection>() {
        @Override
        public ImageCollection createFromParcel(Parcel in) {
            return new ImageCollection(in);
        }

        @Override
        public ImageCollection[] newArray(int size) {
            return new ImageCollection[size];
        }
    };

    public static ImageCollection from(List<String> images) {
        ImageCollection imageCollection = new ImageCollection();
        for (String image : images) {
            imageCollection.add(Image.from(image));
        }
        return imageCollection;
    }


    public ArrayList<String> asStringList() {
        ArrayList<String> strings = new ArrayList<>();
        for (Image image : asList()) {
            strings.add(image.getUrl().asString());
        }

        return strings;
    }

}
