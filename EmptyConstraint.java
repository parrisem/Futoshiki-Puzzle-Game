
package futoshikipuzzlegui;

/**
 *
 * @author pe79
 */
public class EmptyConstraint extends Constraint{
    private String symbol;
    
    public EmptyConstraint(boolean i) {
        this.symbol = " ";      
    }

    public String getSymbol() {
        return symbol;
    }
}
