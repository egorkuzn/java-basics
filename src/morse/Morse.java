package morse;

import java.io.*;
import java.nio.CharBuffer;

public class Morse {
    private Reader reader = null;
    private Writer result = null , statictics = null;
    private String buffer = null;

    public enum Mode{
        CODE_MODE,
        DECODE_MODE
    }

    Morse(String file_name, Mode mode){
        try{
            reader = new InputStreamReader(new FileInputStream(file_name));

        } catch(IOException e){
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } finally {
            try {
                int c;

                while ((c = reader.read()) != -1) {
                    switch (mode) {
                        case CODE_MODE:
                            code((char)c);
                        case DECODE_MODE:
                            decode((char)c);
                    }
                }

                reader.close();
            } catch (IOException e){
                e.printStackTrace(System.err);
            }
        }
    }
    
    private static void code(char symb){
        String morse_line = find();
        result.append(morse_line);
    }

    private static void decode(char symb){
        if(symb == '\n'){

        } else{

        }
    }

}
