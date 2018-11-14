package by.etc.karamach.restaurant.mcdonald.cashwindow.states;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class DeliveryState implements State {
    private static final Logger logger = LogManager.getLogger("default");
    private final McdonaldCashWindow cashWindow;

    public DeliveryState(McdonaldCashWindow cashWindow) {
        this.cashWindow = cashWindow;
    }

    public void handleCustomer(RestaurantVisitor visitor) {
        try {

            TimeUnit.SECONDS.sleep(5);
            logger.info(visitor.getName() + " take products.");

        } catch (InterruptedException e) {
            logger.info(visitor.getName() + "don't wait.");
        } finally {

            cashWindow.changeState(StateName.valueOf("REST"));

            logger.info(visitor.getName() + " leave restaurant");
        }
    }
}
