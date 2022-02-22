import morse.*;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String input_name = "C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\out_morse.txt";
        String out_name = "C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\The Blind Man by D. H. Lawrence.txt";
        Morse m = new Morse(input_name, out_name);
        m.viewStat("C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\stat_file.txt");
    }
}
