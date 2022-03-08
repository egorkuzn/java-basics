package morse;

import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class Statistics {
    String out_filename = "";
    String in_filename = "";
    Set<Symbol> data = new HashSet<Symbol>();

    public Statistics(String out_filename, String in_filename){
        this.out_filename = out_filename;
        this.in_filename = in_filename;
    }

    public void view (){
        genData();
        BufferedWriter writer = null;

        try{
            writer = new BufferedWriter(new FileWriter(this.out_filename));

            Iterator<Symbol> iterate = data.iterator();

            while(iterate.hasNext()) {
                writer.write(iterate.next().toString());
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
        Symbol C = new Symbol(c);
        if(!data.contains(C)) {
            data.add(C);
        }
    }
}
