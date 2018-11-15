package by.etc.karamach.restaurant.mcdonald.customer;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RegisteredVisitor implements Customer, Runnable {
    private static final Logger logger = LogManager.getLogger("default");
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

            TimeUnit.SECONDS.sleep(4);
            if (logger.isInfoEnabled()) {
                logger.info("<" + name + "> place an order via the Internet");
            }
            TimeUnit.SECONDS.sleep(2);

            ReentrantLock locker = cashWindow.getLockerForDelivery();
            ReentrantLock lockerForAll = cashWindow.getLockerAllVisitors();
            if (logger.isInfoEnabled()) {
                logger.info("<" + name + "> arrived to the restaurant");
            }

            locker.lock();
            lockerForAll.lock();

            if (logger.isInfoEnabled()) {
                logger.info(name + " come to <" + cashWindow.getName() + "> to take his order");
            }

            cashWindow.handleCustomer(this);

            cashWindow.getLockForAllCondition().signalAll();

            locker.unlock();
            lockerForAll.unlock();


        } catch (InterruptedException e) {
            //TODO:QUESTION: can we simple don't catch exceptions, only log them?
            logger.error("<" + name + "> changed his mind");
        }

    }
}
