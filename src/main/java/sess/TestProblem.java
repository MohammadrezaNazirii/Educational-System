package sess;

public class TestProblem extends Problem{
    public TestProblem(String q, String a1, String a2, String a3, String a4, String sol) {
        Title = q;
        Solution = sol;
        Mode = 3;
        Option1 = a1;
        Option2 = a2;
        Option3 = a3;
        Option4 = a4;
    }
    @Override
    String Print(){
        String temp = "";
        temp += Title + "\n    1. " + Option1 + "\n    2. " + Option2 + "\n    3. " + Option3 + "\n    4. " + Option4;
        return temp;
    }
    @Override
    String PrintSol() {
        return "Ans: "+Solution;
    }
}
