package app.avg.computer;

import java.util.List;
import java.util.ArrayList;

public final class Computer {
    private final List<Double> mData = new ArrayList<>();

    public Computer() {
    }

    public void add(double v) {
        mData.add(v);
    }

    public double calculate() {
        if(mData.size()==0) {
            throw new IllegalStateException("Cannot calculate unexisting data");
        }
        double sum = 0.0;
        for(double d: mData) {
            sum += d;
        }
        return sum/mData.size();
    }
}