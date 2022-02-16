package morse;

import java.io.*;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Morse {
    private Reader reader = null;
    private Writer result = null;
    private HashMap<Character, String> morse_dictionary;
    private HashMap<Character, Integer> stat;
    private String buffer;

    public enum Mode{
        CODE_MODE,
        DECODE_MODE
    }

    public Morse(String file_name, Mode mode){
        try{
            buffer = "";
            reader = new InputStreamReader(new FileInputStream(file_name));
        } catch(IOException e){
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }

        try {
            int c;
            while ((c = reader.read()) != -1) {
                switch (mode) {
                    case CODE_MODE: code((char)c);
                    case DECODE_MODE: decode((char)c);
                }
                statTaker(c);
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace(System.err);
        }

    }

    private void statTaker(int c){
        if(stat.containsKey(c)){
            int count = stat.get(c);
            stat.remove(c);
            ++count;
            stat.put((char) c, count);
            System.out.println(stat.size() + "\n");
        } else{
            stat.put((char) c, 1);
        }
    }

    private void setMorse_dictionary(){
        BufferedReader lib_getter;
        try {
            lib_getter = new BufferedReader(new FileReader("morse_dict.txt"));
            String line = null;
            while ((line = lib_getter.readLine()) != null){
                String[] arr = line.split(" ");
                morse_dictionary.put(arr[0].charAt(0), arr[1]);
            }
        } catch (IOException e){
            e.printStackTrace(System.err);
        }
    }
    
    private void code(char symb){
        String morse_line = morse_dictionary.get(symb);
        try {
            result.append('\n' + morse_line);
        } catch(IOException e){
            e.printStackTrace(System.err);
        }
    }

    private void decode(char symb){
        if(symb != '\n') {
            buffer = buffer + (char)symb;
        } else{
            Character letter = null;
            for(Map.Entry<Character, String> elem: morse_dictionary.entrySet())
                if(elem.getValue() == buffer)
                    letter = elem.getKey();
            try{
                result.append(letter);
            }catch (IOException e){
                e.printStackTrace(System.err);
            } finally {
                buffer = "";
            }
        }
    }

}
