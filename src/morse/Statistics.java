package morse;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class Statistics {
    String out_filename = "";
    String in_filename = "";
    Map<Character, Integer> data = new HashMap<Character, Integer>();

    public Statistics(String out_filename, String in_filename){
        this.out_filename = out_filename;
        this.in_filename = in_filename;
    }

    public void view (){
        genData();
        BufferedWriter writer = null;

        try{
            writer = new BufferedWriter(new FileWriter(this.out_filename));

            for(Map.Entry<Character, Integer> item : data.entrySet()) {
                if(item.getKey() == '\n')
                    writer.write("\\n" + ": " + item.getValue() + "\n");
                else
                    writer.write(item.getKey() + ": " + item.getValue() + "\n");
            }
        } catch(IOException e){
            System.err.println(e.getLocalizedMessage());
        } finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e){
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    private void genData(){
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader(this.in_filename));
            String string_buffer = "";

            while ((string_buffer = reader.readLine()) != null){
                addInData('\n');
                for (int i = 0; i < string_buffer.length(); ++i)
                    addInData(string_buffer.charAt(i));
            }

        } catch (IOException e){
            System.err.println(e.getLocalizedMessage());
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch(IOException e){
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    private void addInData(char c){
        if(data.containsKey(c))
            data.put(c, data.get(c) + 1) ;
        else
            data.put(c, 1);
    }
}
