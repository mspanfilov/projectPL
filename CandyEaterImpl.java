
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author panfilov_ms
 */
public class CandyEaterImpl implements CandyEater {

    public CandyEaterImpl() {
        System.out.println("create eater " + hashCode());
    }

    @Override
    public void eat(Candy candy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {
        System.out.println("start eater " + hashCode());
    }
    
}
