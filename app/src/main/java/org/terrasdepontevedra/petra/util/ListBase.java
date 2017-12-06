package org.terrasdepontevedra.petra.util;


import java.util.List;

interface ListBase<T> {
    int size();

    T get(int position);

    void add(T item);

    List<T> asList();

    boolean isEmpty();

    void addAll(List<T> collection);

    void add(int position, T item);

    boolean contains(T item);

    int indexOf(T item);

    void set(int position, T item);

    T get(T item);
}
