package pl.dariuszzbyrad.javanumerical.ode;

import java.util.Arrays;

public class ODEEulerSolver {

    /**
     * Solve an initial value problem for a system of ODEs using EULER method.
     * <p>
     * Example (Lorenz System):
     * <pre>
     * public class LorenzSystem implements Ifun {
     *
     *     {@code @Override}
     *     public double[] calculate(double h, double[] state, double[] params) {
     *         double x = state[0];
     *         double y = state[1];
     *         double z = state[2];
     * <p>
     *         double sigma = params[0];
     *         double rho = params[1];
     *         double beta = params[2];
     * <p>
     *         return new double[]{
     *                 sigma * (y - x),
     *                 x * (rho - z) - y,
     *                 x * y - beta * z
     *         };
     *     }
     * }
     * <p>
     * Ifun f = new LorenzSystem();
     * double[] xyz0 = [1, 1, 1];
     * double[] timeSpace = SeqGenerator.linspace(0, 25, 0.02);
     * double[] args = [10, 28, 8.0/3.0]; //sigma, rho, beta
     * <p>
     * ODESolver odeSolver = new ODESolver();
     * double[][] solution = odeSolver.solve(f, xyz0, timeSpace, args);
     * <p>
     * for (double[] state : solution) {
     *  System.out.println(state[0] + ";" + state[1] + ";" + state[2]);
     * }
     *</pre>
     * @param fun Right-hand side of the system. The calling signature is ``fun(t, y, args)``.
     * @param initialState The initial state
     * @param tEval Steps for interval of integration. The solver starts with first element of `tEval` and
     *         stop with the last element of `tEval`
     * @param args Additional arguments to pass to the user-defined functions.
     *
     * @return Values of the solution.
     */
    public double[][] solve(Ifun fun, double[] initialState, double[] tEval, double[] args) {
        int numberOfStates = tEval.length;
        int numberOfEquations = initialState.length;
        double[][] states = new double[numberOfStates][numberOfEquations]; //init states array
        states[0] = Arrays.copyOf(initialState, initialState.length); //copy initial state as first result (state)

        for (int n=0; n<numberOfStates-1; n++) { //calculate for all states
            double h = tEval[n+1] - tEval[n]; //step size for current calculated state
            double[] slopes = fun.calculate(tEval[n], states[n], args);
            for (int k=0; k<numberOfEquations; k++) {
                //y_{n+1} = y_n + h * f(t, state[n])
                states[n+1][k] = states[n][k] + h * slopes[k]; //Euler method
            }
        }

        return states;
    }
}
