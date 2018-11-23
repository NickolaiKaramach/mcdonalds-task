package by.etc.karamach.restaurant.mcdonald.customer;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RegisteredVisitor implements Customer, Runnable {
    private static final Logger logger = LogManager.getLogger("default");
    private static final int TIME_TO_PLACE_ORDER = 4;//4
    private static final int TIME_TO_COME_TO_RESTAURANT = 2;//2
    private McdonaldCashWindow cashWindow;
    private String name;

    public RegisteredVisitor(McdonaldCashWindow cashWindow, String visitorName) {
        this.cashWindow = cashWindow;
        this.name = visitorName;
    }

    public String getName() {
        return name;
    }

    public void run() {
        try {

            TimeUnit.SECONDS.sleep(TIME_TO_PLACE_ORDER);

            if (logger.isInfoEnabled()) {
                logger.info("<" + name + "> placed an order via Internet");
            }

            ReentrantLock lockerForDelivery;
            ReentrantLock lockerAllVisitors;

            lockerAllVisitors = cashWindow.getLockerAllVisitors();
            lockerForDelivery = cashWindow.getLockerForDelivery();

            TimeUnit.SECONDS.sleep(TIME_TO_COME_TO_RESTAURANT);

            if (logger.isInfoEnabled()) {
                logger.info("<" + name + "> arrived to the restaurant");
            }

            lockerForDelivery.lock();
            lockerAllVisitors.lock();

            if (logger.isInfoEnabled()) {
                logger.info("<" + name + "> come to <" +
                        cashWindow.getName() + "> to take his order");
            }

            cashWindow.handleCustomer(this);

            cashWindow.getLockForAllCondition().signalAll();

            lockerForDelivery.unlock();
            lockerAllVisitors.unlock();


        } catch (InterruptedException e) {
            logger.error("<" + name + "> changed his mind");
        }

    }
}
