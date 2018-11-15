package by.etc.karamach.restaurant.mcdonald.customer;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
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
        if (logger.isInfoEnabled()) {
            logger.info("<" + name + "> came to the restaurant and took a turn to <" + cashWindow.getName() + ">");
        }
        ReentrantLock lockerDelivery = cashWindow.getLockerForDelivery();
        ReentrantLock lockForAll = cashWindow.getLockerAllVisitors();

        Condition condition = cashWindow.getLockForAllCondition();


        try {
            lockForAll.lock();

            while (lockerDelivery.isLocked()) {
                condition.await();
            }

            lockerDelivery.lock();

            if (logger.isInfoEnabled()) {
                logger.info("<" + name + "'s> turn came!");
            }
            cashWindow.handleCustomer(this);

            lockerDelivery.unlock();

        } catch (InterruptedException e) {
            logger.error("Customer: <" + name + "> left the restaurant!");
        } finally {
            lockForAll.unlock();
        }
    }
}
