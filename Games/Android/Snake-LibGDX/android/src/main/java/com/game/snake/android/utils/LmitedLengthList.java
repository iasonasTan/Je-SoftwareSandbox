package com.game.snake.android.utils;

import java.util.ArrayList;
import java.util.Collection;

public class LmitedLengthList<T> extends ArrayList<T> {
    private int mMaxLength;

    public LmitedLengthList() {
        this(Integer.MAX_VALUE);
    }

    public LmitedLengthList(int maxLength) {
        if(maxLength<1)throw new IllegalArgumentException("Length limit must be greater than 1.");
        mMaxLength = maxLength;
    }

    public void setMaxLength(int len) {
        if(len<1)throw new IllegalArgumentException("Length limit must be greater than 1.");
        mMaxLength = len;
        Collection<T> backup = new ArrayList<>(this);
        clear();
        addAll(backup);
    }

    @Override
    public boolean add(T t) {
        if(size() < mMaxLength)
            return super.add(t);
        return false;
    }

    @Override
    public void add(int index, T element) {
        if(size() < mMaxLength)
            super.add(index, element);
    }

    private boolean addAt(int idx, T ele) {
        if(size() < mMaxLength) {
            super.add(idx, ele);
            return true;
        }
        return false;
    }

    @Override
    public void addFirst(T element) {
        throw new UnsupportedOperationException("Operation is unsupported. Use 'boolean add(...)' instead.");
    }

    @Override
    public void addLast(T element) {
        throw new UnsupportedOperationException("Operation is unsupported. Use 'boolean add(...)' instead.");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean allAdded = true;
        for (T t: c) {
            allAdded = add(t)&&allAdded;
        }
        return allAdded;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean allAdded = true;
        T[] objects = (T[])c.toArray(new Object[0]);
        for (int i = 0; i < c.size(); i++) {
            allAdded = allAdded&&addAt(index, objects[i]);
            index++;
        }
        return allAdded;
    }
}
