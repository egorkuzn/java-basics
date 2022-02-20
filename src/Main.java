import morse.*;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String input_name = "C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\Irtegov-OS-Unix-System-Calls.txt";
        String out_name = "C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\out_morse.txt";
        Morse m = new Morse(input_name, out_name);
        m.code();
        input_name = out_name;
        out_name = "out_latin.txt";
        m.changeIn(input_name);
        m.changeOut(out_name);
        m.decode();
    }
}
