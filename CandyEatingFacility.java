
import java.util.Set;
import java.util.concurrent.BlockingQueue;

/** Вкус. */
interface Flavour extends Comparable<Flavour> {
}

/** Конфета с каким-нибудь вкусом. */
interface Candy {
    Flavour getFlavour();
}

/** Поедатель конфет. */
interface CandyEater extends Runnable {     // !!! пришлось унаследоваться от Runnable, чтобы сделать потоки
    void eat(Candy candy);               
}

/** Поедает конфеты хором. */
interface CandyEatingFacility {

    /**
     * Запускает параллельное поедание конфет из очереди <code>candies</code>
     * поедателями <code>candyEaters</code>.
     * <p/>
     * Обеспечивает, что<br/>
     * &bull; в любой момент времени поедается не более одной конфеты каждого вкуса и<br/>
     * &bull; конфеты одного вкуса поедаются в той очерёдности, в которой они находились в очереди.
     * <p/>
     * Возвращает управление после запуска поедания.
     * <p/>
     * Переданные параметры, включая элементы коллекций и вкусы конфет,
     * должны быть не нул, это не проверяется.
     *
     * @param candies     очередь конфет
     * @param candyEaters набор поедателей конфет
     */
    void launch(BlockingQueue<Candy> candies, Set<CandyEater> candyEaters);

    /**
     * Прекращает выемку новых конфет из очереди и возвращает управление,
     * когда все уже вынутые из очереди конфеты съедены.
     */
    void shutdown();
}
