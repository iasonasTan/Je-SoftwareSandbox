package main;

import java.util.ArrayList;

public class StrictList<T> extends ArrayList<T> {
    private int nLimit;

    public StrictList(int nLimit) {
        this.nLimit = nLimit;
    }

    public void changeNLimit(int diffN) {
        nLimit+=diffN;
    }

    @Override
    public boolean add(T t) {
        super.add(t);

        while(size()>nLimit&&size()>0)
            removeFirst();

        return true;
    }

}
