package CodingGames.design_pattern;

/*
 * Classic singleton thread-safe implemation with lazy-load
 */
public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                // recheck value
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

/*
 * Bill Pugh singleton using private final to restrict the instance
 * thread-safe without synchronized block
 */
class BillPughSingleton {
    private BillPughSingleton() {
    }

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
