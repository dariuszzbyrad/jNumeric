package pl.dariuszzbyrad.javanumerical.ode

import pl.dariuszzbyrad.javanumerical.utils.SeqGenerator
import spock.lang.Specification

class ODEEulerSolverSpec extends Specification {
    def "should calculate simple ODE by using EULUER method"() {
        given:
        Ifun exponentialDecay = new Ifun() {
            @Override
            double[] calculate(double t, double[] state, double[] params) {
               [-0.5 * state[0]]
            }
        }
        double[] y0 = [2]
        double[] timeSpace = SeqGenerator.linspace(0, 2, 0.5)
        double[] args = []

        when:
        ODEEulerSolver odeSolver = new ODEEulerSolver()
        double[][] solution = odeSolver.solve(exponentialDecay, y0, timeSpace, args)

        then:
        solution.length == 4
        solution[0].length == 1
        solution[0][0] == (double) 2.0
        solution[1][0] == (double) 1.5
        solution[2][0] == (double) 1.125
        solution[3][0] == (double) 0.84375
    }
}
