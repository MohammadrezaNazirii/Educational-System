package sess;

public class TFProblem extends Problem{
    public TFProblem(String question, String sol) {
        Title = question;
        Solution = sol;
        Mode = 4;
    }

    @Override
    String Print() {
        String temp = "";
        temp += Title + " (TRUE or FALSE)";
        return temp;
    }
    @Override
    String PrintSol() {
        return "Ans: "+Solution;
    }
}
