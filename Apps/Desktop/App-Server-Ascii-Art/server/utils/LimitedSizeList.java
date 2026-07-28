package server.utils;

import java.util.ArrayList;

/**
 * Extension of {@link ArrayList} with a limited size.<br>
 * When the size of the {@code list} reaches the limit 
 * item at index 0 gets removed to add a new element.
 */
public class LimitedSizeList<T> extends ArrayList<T> {
    private int mSizeLimit;

    public LimitedSizeList() {
        this(1);
    }

    public LimitedSizeList(int limit) {
        mSizeLimit = limit;
    }

    @Override
    public void add(int index, T t) {
        if(size()>=mSizeLimit)
            remove(0);
        super.add(index, t);
    }

    @Override
    public boolean add(T t) {
        if(size()>=mSizeLimit)
            remove(0);
        return super.add(t);
    }
}