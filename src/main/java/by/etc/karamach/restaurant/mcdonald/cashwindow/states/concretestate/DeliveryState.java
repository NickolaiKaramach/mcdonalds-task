package by.etc.karamach.restaurant.mcdonald.cashwindow.states.concretestate;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.State;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.StateName;
import by.etc.karamach.restaurant.mcdonald.customer.Customer;
import by.etc.karamach.restaurant.mcdonald.customer.RegisteredVisitor;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class DeliveryState implements State {
    private static final Logger logger = LogManager.getLogger("default");

    private static final int COLLECTION_OF_PRODUCTS_DURATION = 5;
    private static final int DELIVERY_OF_PREPARED_PRODUCTS_DURATION = 3;

    private final McdonaldCashWindow cashWindow;


    public DeliveryState(McdonaldCashWindow cashWindow) {
        this.cashWindow = cashWindow;
    }


    public void handleCustomer(RestaurantVisitor visitor) {

        serveVisitor(visitor, COLLECTION_OF_PRODUCTS_DURATION);
    }

    public void handleCustomer(RegisteredVisitor visitor) {

        serveVisitor(visitor, DELIVERY_OF_PREPARED_PRODUCTS_DURATION);
    }

    private void serveVisitor(Customer visitor, int waitingTime) {
        try {

            TimeUnit.SECONDS.sleep(waitingTime);

            if (logger.isInfoEnabled()) {
                logger.info("<" + visitor.getName() + "> got products");
            }

        } catch (InterruptedException e) {
            logger.error("<" + visitor.getName() + "> don't wait");
        } finally {

            cashWindow.changeState(StateName.REST);
            if (logger.isInfoEnabled()) {
                logger.info("<" + visitor.getName() + "> leave restaurant");
            }
        }
    }
}
