package sess;

public class Problem {
    String Option1 = "";
    String Option2 = "";
    String Option3 = "";
    String Option4 = "";

    String Title;
    String Solution;
    int Mode; //1:Blank, 2:Description, 3:Test, 4:TF

    String Print(){
        return "";
    }
    String PrintSol(){
        return "";
    }

    @Override
    public String toString(){
        switch (Mode){
            case 1:
            case 2:
            case 4:
                return Mode + "\n" + Title + '\n' + Solution;
            case 3:
                return Mode + "\n" + Title + '\n' + Option1 + '\n' + Option2 + '\n' + Option3 + '\n' + Option4 + '\n' + Solution;
            default:
                return "";
        }
    }
}
