import morse.*;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String input_name = "C:\\Users\\egork\\IdeaProjects\\java-basics\\texts\\output_morse.txt";
        Morse m = new Morse(input_name);

        m.viewStat("C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\stat_file.txt");
    }
}
