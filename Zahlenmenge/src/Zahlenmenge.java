import java.util.TreeSet;

public class Zahlenmenge<T extends Number & Comparable<T>> {
    private TreeSet<T> set;

    public Zahlenmenge() {
        set = new TreeSet<>();
    }

    public void set(T val) { set.add(val); }

    public boolean get(T val) { return set.contains(val); }

    public int size() { return set.size(); }

    public void remove(T val) { set.remove(val); }

    public Zahlenmenge<T> clone() {
        Zahlenmenge<T> res = new Zahlenmenge<>();
        res.set = (TreeSet<T>) set.clone();
        return res;
    }

    public void print() {
        System.out.println(set);
    }

    @Override
    public String toString() {
        return set.toString();
    }

    public Zahlenmenge<T> intersect(Zahlenmenge<T> s) {
        Zahlenmenge<T> res = clone();
        res.set.retainAll(s.set);
        return res;
    }

    public Zahlenmenge<T> union(Zahlenmenge<T> s) {
        Zahlenmenge<T> res = clone();
        res.set.addAll(s.set);
        return res;
    }

    public Zahlenmenge<T> diff(Zahlenmenge<T> s) {
        Zahlenmenge<T> res = clone();
        res.set.removeAll(s.set);
        return res;
    }

    public Zahlenmenge<T> range(T from, T to) {
        Zahlenmenge<T> res = new Zahlenmenge<>();
        res.set.addAll(set.subSet(from, true, to, true));
        return res;
    }

    public double total() {
        double sum = 0;
        for (T element : set) {
            sum += element.doubleValue();
        }
        return sum;
    }

    public double average() {
        return total() / size();
    }

    public T min() { return set.first(); }

    public T max() { return set.last(); }

    public boolean equals(Zahlenmenge<T> s) {
        return this.set.equals(s.set);
    }

    public T zufallsZahl() {
        int index = (int) (Math.random() * size());
        return (T) set.toArray()[index];
    }

    public static void main(String[] args) {
        Zahlenmenge<Integer> s1 = new Zahlenmenge<>();
        s1.set(-9);
        s1.set(-5);
        s1.set(-4);
        s1.set(-3);
        s1.set(0);
        s1.set(2);
        s1.set(4);
        s1.set(10);
        Zahlenmenge<Integer> s2 = new Zahlenmenge<>();
        s2.set(-5);
        s2.set(-3);
        s2.set(0);
        s2.set(1);
        s2.set(2);
        s2.set(7);
        s2.set(9);

        System.out.println("Union          " + s1.union(s2).set);
        System.out.println("Intersect      " + s1.intersect(s2).set);
        System.out.println("Range (0, 10)  " + s1.range(0, 10).set);
        System.out.println("Range (-10, 0) " + s2.range(-10, 0).set);

        s2.remove(0);
        System.out.println("Remove         " + s2.set);

        s2.remove(2);
        System.out.println("Remove         " + s2.set);
        System.out.println("Intersect      " + s1.intersect(s2).set);
        System.out.println("Diff           " + s1.diff(s2).set);

        System.out.println("\n/-----------------------------------\\\n");

        System.out.println("Total          " + s1.total());
        System.out.println("Average        " + s1.average());
        System.out.println("Min            " + s1.min());
        System.out.println("Max            " + s1.max());
        System.out.println("Equals         " + s1.equals(s2));
        System.out.println("Random         " + s1.zufallsZahl());

        s1.print();
    }
}