import java.util.ArrayList;
import java.util.List;

public class Result {


    private List<ReccomendModel> result = new ArrayList<>();

    public List<ReccomendModel> getResult() {
        return result;
    }

    public void setResult(List<ReccomendModel> result) {
        this.result = result;
    }

    public static void main(String[] args) {
        double avg = 3.41231235;
        avg = avg*100;
        avg = (double)((int) avg);
        avg = avg /100;
        System.out.println(((double)((int) avg * 100)) / 100);
    }
}
