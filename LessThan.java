
package futoshikipuzzlegui;

/**
 *
 * @author pe79
 */
public class LessThan extends Constraint{
    
    private String symbol;

    public LessThan(boolean row) {
        if (row) {
            this.symbol = "<";
        } else {
            this.symbol = "V";
        }
    }
    
    public String getSymbol() {
        return symbol;
    }
    
}
