package by.etc.karamach.restaurant.mcdonald.customer;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class RestaurantVisitor implements Runnable, Customer {
    private McdonaldCashWindow cashWindow;
    private static final Logger logger = LogManager.getLogger("default");
    private String name;

    public RestaurantVisitor(McdonaldCashWindow cashWindow, String customerName) {
        this.cashWindow = cashWindow;
        this.name = customerName;
    }

    public String getName() {
        return name;
    }

    public void run() {
        ReentrantLock locker = cashWindow.getLocker();

        locker.lock();

        cashWindow.handleCustomer(this);

        locker.unlock();

    }
}
