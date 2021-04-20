
package futoshikipuzzlegui;

/**
 *
 * @author pe79
 */
public class GreaterThan extends Constraint {

    private String symbol;

    public GreaterThan(boolean row) {
        if (row) {
            this.symbol = ">";
        } else {
            this.symbol = "^";
        }        
    }
    
    public String getSymbol() {
        return symbol;
    }
    
}
