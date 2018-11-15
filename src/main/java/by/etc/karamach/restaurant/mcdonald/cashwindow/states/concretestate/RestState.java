package by.etc.karamach.restaurant.mcdonald.cashwindow.states.concretestate;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.State;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.StateName;
import by.etc.karamach.restaurant.mcdonald.customer.RegisteredVisitor;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class RestState implements State {
    private static final Logger logger = LogManager.getLogger("default");
    private static final int GREETING_TIME_DURATION = 2;

    private final McdonaldCashWindow cashWindow;


    public RestState(McdonaldCashWindow cashWindow) {
        this.cashWindow = cashWindow;
    }


    public void handleCustomer(RestaurantVisitor visitor) {

        try {

            TimeUnit.SECONDS.sleep(GREETING_TIME_DURATION);

            cashWindow.changeState(StateName.PROCESSING);


            if (logger.isInfoEnabled()) {
                logger.info("<" + visitor.getName() + "> came to <" +
                        cashWindow.getName() + ">. Trying to serve visitor.");
            }

            cashWindow.handleCustomer(visitor);


        } catch (InterruptedException e) {

            logger.error("<" + visitor.getName() + "> don't wait");

            cashWindow.changeState(StateName.REST);
            logger.error("<" + visitor.getName() + "> leave restaurant");
        }

    }

    public void handleCustomer(RegisteredVisitor visitor) {
        cashWindow.changeState(StateName.DELIVERY);

        cashWindow.handleCustomer(visitor);
    }
}
