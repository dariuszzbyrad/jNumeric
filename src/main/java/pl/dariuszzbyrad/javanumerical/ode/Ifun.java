package pl.dariuszzbyrad.javanumerical.ode;

public interface Ifun {
    double[] calculate(double t, double[] state, double[] params);
}
