package morse;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class MorseDictionaryToEnotherLang {
    Map<String, Character> dictionary = new HashMap<String, Character>();

    private String path = "C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\extern_morse_dict.txt";

    public MorseDictionaryToEnotherLang(String path){
        loadExternDictionary();
        this.path = path;
    }

    public char translate(String morse_char){
        if(this.dictionary.containsKey(morse_char))
            return this.dictionary.get(morse_char);
        else
            return '\0';
    }

    public boolean contains(String str, char latin_char){
        if(this.dictionary.containsKey(str))
            return true;
        else{
            this.dictionary.put(str, latin_char);
            putInExternDictionary(str, latin_char);
            return false;
        }
    }

    void putInExternDictionary(String str, char latin_char){
        BufferedWriter writer = null;

        try{
            writer = new BufferedWriter(new FileWriter(this.path, true));
            writer.write(str + " " + latin_char + "\n");
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

    private void loadExternDictionary(){
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader(this.path));
            String string_buffer;

            while ((string_buffer = reader.readLine()) != null)
                getString(string_buffer);

        } catch (IOException e){
            System.err.println("Error while reading file: \"" + this.path + "\"" + e.getLocalizedMessage());
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

    private void getString(String string_buffer){
        String str = string_buffer.substring(0, string_buffer.indexOf(' '));
        Character c = string_buffer.charAt(string_buffer.length() - 1);
        this.dictionary.put(str, c);
    }
}
