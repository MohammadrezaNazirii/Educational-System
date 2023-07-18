package sess;

public class BlankProblem extends Problem{
    public BlankProblem(String Qpart1, String Qpart2, String sol) {
        Title = Qpart1 + "........" + Qpart2;
        Solution = sol;
        Mode = 1;
    }

    @Override
    String Print() {
        return Title;
    }

    @Override
    String PrintSol() {
        return "Ans: "+Solution;
    }
}
