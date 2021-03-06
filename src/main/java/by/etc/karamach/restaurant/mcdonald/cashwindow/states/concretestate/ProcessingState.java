package by.etc.karamach.restaurant.mcdonald.cashwindow.states.concretestate;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.State;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.StateName;
import by.etc.karamach.restaurant.mcdonald.customer.RegisteredVisitor;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class ProcessingState implements State {
    private static final Logger logger = LogManager.getLogger("default");
    private static final int ORDER_PLACING_DURATION = 5;//5

    private final McdonaldCashWindow cashWindow;


    public ProcessingState(McdonaldCashWindow cashWindow) {
        this.cashWindow = cashWindow;
    }


    public void handleCustomer(RegisteredVisitor visitor) {

        cashWindow.changeState(StateName.DELIVERY);

        cashWindow.handleCustomer(visitor);
    }

    public void handleCustomer(RestaurantVisitor visitor) {

        try {

            TimeUnit.SECONDS.sleep(ORDER_PLACING_DURATION);

            cashWindow.changeState(StateName.DELIVERY);

            loggerPrintInfo("<" + visitor.getName() +
                    "> put an order. Visitor is waiting for delivery of products");


            cashWindow.handleCustomer(visitor);

        } catch (InterruptedException e) {

            logger.error("<" + visitor.getName() + "> don't wait");

            cashWindow.changeState(StateName.REST);
            logger.error("<" + visitor.getName() + "> leave restaurant");
        }
    }

    private void loggerPrintInfo(String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }
}
