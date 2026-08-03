import java.util.*;

public class Mydatastructure<T extends Comparable<T>> {
    private Comparable<T>[] data=new Comparable<T>[0];

    public Mydatastructure() {
    }

    public boolean add(T t) {
        // find index
        int index=0;
        for(int i=0; i<data.length-1; i++) {
            if (data[i].compareTo(data[i+1])>0) {
                index=i;
            }
        }
        // extend array
        Object[] arrcp=new Object[data.length+1];
        for(int i=0; i<data.length; i++) {
            arrcp[i]=data[i];
        }
        data=new Object[arrcp.length];
        // pull items
        for (int i=data.length-2; i>0; i--) {
            if (i==index)
                data[i]=t;
            else
                data[i+1]=arrcp[i];
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        for(Object o: data) {
            builder.append(o);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Mydatastructure<String> dat=new Mydatastructure<String>();
        dat.add("abc");
        dat.add("cdf");
        dat.add("fgh");
        System.out.println(dat);
    }
}