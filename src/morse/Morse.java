package morse;

public class Morse {
    private String input_name;
    private String out_name;

    public Morse(String input_name, String out_name){
        this.input_name = input_name;
        this.out_name = out_name;
    }

    public void changeIn(String new_name){
        this.input_name = new_name;
    }

    public void changeOut(String new_name){
        this.out_name = new_name;
    }

    public void code(){
        Code obj = new Code(input_name, out_name);
        obj.translate();
    }

    public void decode(){
        Decode obj = new Decode(input_name, out_name);
        obj.translate();
    }
}
