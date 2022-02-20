package morse;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class MorseDictionaryLatin {
    Map<String, Character> dictionary = new HashMap<String, Character>();

    final private static String path = "C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\extern_morse_dict.txt";

    public MorseDictionaryLatin(){
        //Latin letters
        this.dictionary.put(".-",       'A');
        this.dictionary.put("-...",     'B');
        this.dictionary.put("-.-.",     'C');
        this.dictionary.put("-..",      'D');
        this.dictionary.put(".",        'E');
        this.dictionary.put("..-.",     'F');
        this.dictionary.put("--.",      'G');
        this.dictionary.put("....",     'H');
        this.dictionary.put("..",       'I');
        this.dictionary.put(".---",     'J');
        this.dictionary.put("-.-",      'K');
        this.dictionary.put(".-..",     'L');
        this.dictionary.put("--",       'M');
        this.dictionary.put("-.",       'N');
        this.dictionary.put("---",      'O');
        this.dictionary.put(".--.",     'P');
        this.dictionary.put("--.-",     'Q');
        this.dictionary.put(".-.",      'R');
        this.dictionary.put("...",      'S');
        this.dictionary.put("-",        'T');
        this.dictionary.put("..-",      'U');
        this.dictionary.put("...-",     'V');
        this.dictionary.put(".--",      'W');
        this.dictionary.put("-..-",     'X');
        this.dictionary.put("-.--",     'Y');
        this.dictionary.put("--..",     'Z');
        //Arabian nums
        this.dictionary.put("-----",    '0');
        this.dictionary.put(".----",    '1');
        this.dictionary.put("..---",    '2');
        this.dictionary.put("...--",    '3');
        this.dictionary.put("....-",    '4');
        this.dictionary.put(".....",    '5');
        this.dictionary.put("-....",    '6');
        this.dictionary.put("--...",    '7');
        this.dictionary.put("---..",    '8');
        this.dictionary.put("----.",    '9');
        //Punctuation marks
        this.dictionary.put(".-.-.-",   '.');
        this.dictionary.put("--..--",   ',');
        this.dictionary.put("---...",   ':');
        this.dictionary.put("..--..",   '?');
        this.dictionary.put("-...-",    '=');
        this.dictionary.put("-....-",   '-');
        this.dictionary.put("-.--.",    '(');
        this.dictionary.put("-.--.-",   ')');
        this.dictionary.put(".-..-.",  '\"');
        this.dictionary.put(".----.",   '\'');
        this.dictionary.put("-..-.",    '/');
        this.dictionary.put(".--.-.",   '@');
        this.dictionary.put("-.-.--",   '!');
        this.dictionary.put(" ",        ' ');

        loadExternDictionary();
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
