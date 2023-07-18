package sess;

public class DescriptiveProblem extends Problem{
    public DescriptiveProblem(String question, String sol) {
        this.Title = question;
        Solution = sol;
        Mode = 2;
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
