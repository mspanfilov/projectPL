
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 * @author panfilov_ms
 */
public class CandyEatingFacilityImpl implements CandyEatingFacility{
    
    ExecutorService service;    
    
    @Override
    public void launch(BlockingQueue<Candy> candies, Set<CandyEater> candyEaters) {
        service = Executors.newFixedThreadPool(candyEaters.size()); // pool of quantity eaters of candy
        // сделать барьер
        // поставить барьер
        for(CandyEater i : candyEaters){
            service.execute(i);
        }
        // открыть барьер
        System.out.println("All CandyEaters started");
    }

    @Override
    public void shutdown() {
        service.shutdown();
    }
    
}
