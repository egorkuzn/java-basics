package morse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class LatinDictionaryMorse {
    Map<Character, String> dictionary = new HashMap<Character, String>();

    final private static String path = "C:\\Users\\egork\\IdeaProjects\\java-basics\\src\\morse\\extern_morse_dict.txt";

    public LatinDictionaryMorse(){
        //Latin letters
        this.dictionary.put('A',      ".-");
        this.dictionary.put('B',    "-...");
        this.dictionary.put('C',    "-.-.");
        this.dictionary.put('D',     "-..");
        this.dictionary.put('E',       ".");
        this.dictionary.put('F',    "..-.");
        this.dictionary.put('G',     "--.");
        this.dictionary.put('H',    "....");
        this.dictionary.put('I',      "..");
        this.dictionary.put('J',    ".---");
        this.dictionary.put('K',     "-.-");
        this.dictionary.put('L',    ".-..");
        this.dictionary.put('M',      "--");
        this.dictionary.put('N',      "-.");
        this.dictionary.put('O',     "---");
        this.dictionary.put('P',    ".--.");
        this.dictionary.put('Q',    "--.-");
        this.dictionary.put('R',     ".-.");
        this.dictionary.put('S',     "...");
        this.dictionary.put('T',       "-");
        this.dictionary.put('U',     "..-");
        this.dictionary.put('V',    "...-");
        this.dictionary.put('W',     ".--");
        this.dictionary.put('X',    "-..-");
        this.dictionary.put('Y',    "-.--");
        this.dictionary.put('Z',    "--..");
        //Arabian nums
        this.dictionary.put('0',   "-----");
        this.dictionary.put('1',   ".----");
        this.dictionary.put('2',   "..---");
        this.dictionary.put('3',   "...--");
        this.dictionary.put('4',   "....-");
        this.dictionary.put('5',   ".....");
        this.dictionary.put('6',   "-....");
        this.dictionary.put('7',   "--...");
        this.dictionary.put('8',   "---..");
        this.dictionary.put('9',   "----.");
        //Punctuation marks
        this.dictionary.put('.',  ".-.-.-");
        this.dictionary.put(',',  "--..--");
        this.dictionary.put(':',  "---...");
        this.dictionary.put('?',  "..--..");
        this.dictionary.put('=',   "-...-");
        this.dictionary.put('-',  "-....-");
        this.dictionary.put('(',   "-.--.");
        this.dictionary.put(')',  "-.--.-");
        this.dictionary.put('\"', ".-..-.");
        this.dictionary.put('\'', ".----.");
        this.dictionary.put('/',   "-..-.");
        this.dictionary.put('@',  ".--.-.");
        this.dictionary.put('!',  "-.-.--");
        this.dictionary.put(' ',       " ");

        loadExternDictionary();
    }

    public String translate(char latin_char){
        if('a' <= latin_char && latin_char <= 'z'){
            latin_char -= 'a';
            latin_char += 'A';
        }

        if(!this.dictionary.containsKey(latin_char))
            if(!genMorseSymb(latin_char))
                return "\0";


        return this.dictionary.get(latin_char);
    }

    private boolean genMorseSymb(char latin_char){
        Random rand = new Random();
        String str = "";
        MorseDictionaryLatin obj = new MorseDictionaryLatin();

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
