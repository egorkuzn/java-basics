package morse;

import java.io.*;

public class Decode {
    private String input_name = "";
    private String out_name = "";
    private String dictionary_path = "";
    private BufferedReader reader = null;
    private boolean isNotFirstTime = false;

    public Decode(String input_name, String out_name, String dictionary_path){
        this.input_name = input_name;
        this.out_name = out_name;
        this.dictionary_path = dictionary_path;
    }

    public void translate(){
        try{
            reader = new BufferedReader(new FileReader(this.input_name));
            String string_buffer;

            while ((string_buffer = reader.readLine()) != null)
                getString(string_buffer);

        } catch (IOException e){
            System.err.println("Error while reading file: \"" + this.input_name + "\"" + e.getLocalizedMessage());
        } finally {
            if(reader != null){
                try{
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    private void writeLatinLine(String sub_str){
        BufferedWriter writer = null;

        try{
            writer = new BufferedWriter(new FileWriter(this.out_name, isNotFirstTime));
            isNotFirstTime = true;
            writer.write(sub_str + "\n");
        } catch (IOException e){
            System.err.println(e.getLocalizedMessage());
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }

    }

    private void getString(String string_buffer){
        MorseDictionaryToEnotherLang dictionaryLatin = new MorseDictionaryToEnotherLang(dictionary_path);
        String sub_str = "";

        while (!string_buffer.isEmpty()){
            int end_idx = string_buffer.indexOf(' ', 1);
            String morse_char = "";

            if(end_idx > 0) {
                morse_char = string_buffer.substring(0, end_idx);
                string_buffer = string_buffer.substring(end_idx + 1);
            } else{
                morse_char = string_buffer;
                string_buffer = "";
            }
            sub_str += dictionaryLatin.translate(morse_char);
        }

        writeLatinLine(sub_str);
    }
}
