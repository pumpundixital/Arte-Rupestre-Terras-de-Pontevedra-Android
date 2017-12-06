package org.terrasdepontevedra.petra.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public abstract class CollectionBase<T> implements ListBase<T>, Parcelable {
    protected List<T> mList;

    protected CollectionBase() {
        mList = new ArrayList<>();
    }


    @Override
    public int size() {
        return mList.size();
    }

    @Override
    public T get(int index) {
        return mList.get(index);
    }

    @Override
    public void add(T item) {
        mList.add(item);
    }

    @Override
    public List<T> asList() {
        return mList;
    }

    @Override
    public boolean isEmpty() {
        return mList.isEmpty();
    }

    @Override
    public void addAll(List<T> collection) {
        mList.addAll(collection);
    }

    @Override
    public void add(int position, T item) {
        mList.add(position, item);

    }

    @Override
    public T get(T item) {
        int position = indexOf(item);
        return get(position);
    }

    @Override
    public boolean contains(T item) {
        return mList.contains(item);
    }

    @Override
    public int indexOf(T item) {
        return mList.indexOf(item);
    }

    @Override
    public void set(int position, T item) {
        mList.set(position, item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollectionBase)) return false;

        CollectionBase<?> that = (CollectionBase<?>) o;

        return mList.equals(that.mList);

    }

    @Override
    public int hashCode() {
        return mList.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mList);
    }
}
