package pl.dariuszzbyrad.javanumerical.utils;

public class SeqGenerator {
    public static double[] linspace(double start, double stop, double step) {
        int size = (int) ((stop - start) / step);
        double[] result = new double[size];

        result[0] = start;
        for (int n=1; n<size; n++) {
            result[n] = result[n-1] + step;
        }

        return result;
    }
}
