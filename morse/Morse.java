package morse;

public class Morse {
    private String input_name;
    private String out_name = "C:\\Users\\egork\\IdeaProjects\\java-basics\\texts\\output.txt";
    private String dictionary_path = "C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\extern_morse_dict.txt";

    public Morse(String input_name){
        this.input_name = input_name;
    }

    public void changeIn(String new_name){
        this.input_name = new_name;
    }

    public void changeOut(String new_name){
        this.out_name = new_name;
    }

    public void code(){
        Code obj = new Code(input_name, out_name, dictionary_path);
        obj.translate();
    }

    public void decode(){
        Decode obj = new Decode(input_name, out_name, dictionary_path);
        obj.translate();
    }

    public void viewStat(String filename){
        Statistics obj = new Statistics(filename, this.input_name);
        obj.view();
    }

    public void changeDictionary(String dictionary_path){
        this.dictionary_path = dictionary_path;
    }
}
