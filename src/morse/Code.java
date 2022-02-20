package morse;

import java.io.*;

public class Code {
    String input_name = "";
    String out_name = "";
    BufferedReader reader = null;

    public Code(String input_name, String out_name){
        this.input_name = input_name;
        this.out_name = out_name;
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
            writer = new BufferedWriter(new FileWriter(this.out_name, true));
            writer.write(sub_str + "\n");
        } catch (IOException e){
            System.err.println(e.getLocalizedMessage());
        } finally {
            try {
                writer.close();
            } catch (IOException e){
                System.err.println(e.getLocalizedMessage());
            }
        }

    }

    private void getString(String string_buffer){
        LatinDictionaryMorse dictionaryMorse = new LatinDictionaryMorse();
        String sub_string = "";

        for(int i = 0; i < string_buffer.length(); ++i){
            char latin_char = string_buffer.charAt(i);
            sub_string += dictionaryMorse.translate(latin_char) + " ";
        }

        writeLatinLine(sub_string);
    }
}
