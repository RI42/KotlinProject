import java.util.Arrays;

public class LexCmp {


    static <T> int deepCompare0(T a, T b) throws IllegalArgumentException {
        if (a instanceof Comparable<?>) {
            return ((Comparable<T>) a).compareTo(b);
        } else if (a instanceof Object[]) {
            return deepCompare((Object[]) a, (Object[]) b);
        } else if (a instanceof int[]) {
            return Arrays.compare((int[]) a, (int[]) b);
        } // else if () {
        // other primitive types
        //}
        throw new IllegalArgumentException("cannot compare elements");
    }


    public static <T> int deepCompare(T[] a, T[] b) {
        int size = Math.min(a.length, b.length);
        for (int i = 0; i < size; ++i) {
            int cmp = deepCompare0(a[i], b[i]);
            if (cmp != 0) {
                return cmp;
            }
        }
        return a.length - b.length;
    }
}
