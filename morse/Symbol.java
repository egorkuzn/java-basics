package morse;

/*
* This class is char wrapper, that allows us to use all java.util.Set methods and
* solve repeated symbols problem */
public class Symbol{
    public final char symbol;
    public int count = 0;
    private static long generalCount = 0;

    Symbol(char symbol){
        this.symbol = symbol;
        count++;
        generalCount++;
    }

    @Override
    public boolean equals(Object obj){
        Symbol newSymb = (Symbol) obj;
        boolean result = this.symbol == newSymb.symbol;

        count = Math.max(this.count, newSymb.count) + 1;
        newSymb.count = this.count;

        return result;
    }

    @Override
    public int hashCode(){
        return (int)symbol;
    }

    public final String toString(){
        String result = "";

        switch (symbol){
            case '\n':
                result += "\'\\n\'"; // 'Enter' symbol
                break;
            case ' ':
                result += "\' \'\t"; // 'Split' symbol
                break;
            default:
                result += symbol + "\t"; // Another symbols
        }

// taking percent from all letters
        double formatDouble = (double)(int)((double) count /(double) generalCount * 100000) / 1000;
        result += ":\t" + formatDouble + "%\n";
        return  result;
    }
}
