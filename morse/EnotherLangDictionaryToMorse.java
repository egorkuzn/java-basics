package morse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class EnotherLangDictionaryToMorse {
    Map<Character, String> dictionary = new HashMap<Character, String>();

    static String path = "C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\extern_morse_dict.txt";

    public EnotherLangDictionaryToMorse(String path){
        loadExternDictionary();
        this.path = path;
    }

    public String translate(char latin_char){
        if('a' <= latin_char && latin_char <= 'z'){
            latin_char -= 'a';
            latin_char += 'A';
        } else if(!this.dictionary.containsKey(latin_char)){
            if(!genMorseSymb(latin_char))
                return "\0";
        }


        return this.dictionary.get(latin_char);
    }

    private boolean genMorseSymb(char latin_char){
        Random rand = new Random();
        String str = "";
        MorseDictionaryToEnotherLang obj = new MorseDictionaryToEnotherLang(path);

        int counter = 0;
        do {
            ++counter;
            int i = rand.nextInt(10);
            int j = rand.nextInt(10);
            str = this.dictionary.get((char)(i + '0')) + this.dictionary.get((char)(j + '0'));
        } while (obj.contains(str, latin_char) && counter < 100);

        if(counter == 100)
            return false;

        this.dictionary.put(latin_char, str);
        return true;
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
        this.dictionary.put(c, str);
    }
}
