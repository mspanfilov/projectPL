
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 *
 * @author panfilov_ms
 */
public class MainClass2 {
    
    	public static void main(String[] args) {
                
            	System.out.println("begin");
                System.out.println("quantity eaters: "  + args[0]);
                System.out.println("quantity candies: "  + args[1]);
                System.out.println("quantity flavours: "  + args[2]);
                System.out.println("time(sec.) in which is eaten one candy: "  + args[3]);
                
                //create set eaters of candy
                Set<CandyEater> candyEaters = new HashSet<CandyEater>();
                for(int i=0; i<Integer.parseInt(args[0]); i++){
                    candyEaters.add(new CandyEaterImpl());
                }
                
                //create array flavours
                List<Flavour> flavours = new ArrayList<Flavour>();
                for(int i=0; i<Integer.parseInt(args[2]); i++){
                    flavours.add(new FlavourImpl());
                }
                
                //create queue candies
                int j = 0;
                BlockingQueue<Candy> candies = new LinkedBlockingQueue<Candy>();
                System.out.println("queue candies:");
                for(int i=0; i<Integer.parseInt(args[1]); i++){
                    CandyImpl candy = new CandyImpl();
                    //assign flavors to candies in order
                    candy.setFlavour(flavours.get(j));
                    if (j < flavours.size()-1){
                            j++;
                    }else{
                        j = 0;
                    }
                    System.out.println("candy with flavour " + candy.getFlavour().hashCode());
                    candies.add(candy);
                }
                
		CandyEatingFacilityImpl cefi = new CandyEatingFacilityImpl();

		cefi.launch(candies, candyEaters);
                
                //cefi.shutdown();
                
		System.out.println("end");
		
	}
    
}
