package morse;

import java.io.*;

public class Code {
    private String input_name = "";
    private String out_name = "";
    private String dictionary_path = "";
    private BufferedReader reader = null;
    private boolean isNotFirstTime = false;

    public Code(String input_name, String out_name, String dictionary_path){
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
        EnotherLangDictionaryToMorse dictionaryMorse = new EnotherLangDictionaryToMorse(dictionary_path);
        String sub_string = "";

        for(int i = 0; i < string_buffer.length(); ++i){
            char latin_char = string_buffer.charAt(i);
            sub_string += dictionaryMorse.translate(latin_char) + " ";
        }

        writeLatinLine(sub_string);
    }
}
