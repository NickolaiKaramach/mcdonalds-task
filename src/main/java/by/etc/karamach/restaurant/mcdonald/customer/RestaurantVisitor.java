package by.etc.karamach.restaurant.mcdonald.customer;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RestaurantVisitor implements Runnable {
    private McdonaldCashWindow cashWindow;
    private static final Logger logger = LogManager.getLogger("default");
    private String name;

    public RestaurantVisitor(McdonaldCashWindow cashWindow, String customerName) {
        this.cashWindow = cashWindow;
        this.name = customerName;
    }

    public void run() {
        ReentrantLock locker = cashWindow.getLocker();
        try {
            locker.lock();
            logger.info(name + " came and wait");
            TimeUnit.SECONDS.sleep(5);

            logger.info(name + " go away");
        } catch (InterruptedException e) {
            logger.info("Decided to go away");
        } finally {
            locker.unlock();
        }
    }
}
