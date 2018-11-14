package by.etc.karamach.restaurant.mcdonald.cashwindow.states;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class ProcessingState implements State {
    private static final Logger logger = LogManager.getLogger("default");
    private final McdonaldCashWindow cashWindow;

    public ProcessingState(McdonaldCashWindow cashWindow) {
        this.cashWindow = cashWindow;
    }

    public void handleCustomer(RestaurantVisitor visitor) {
        try {
            TimeUnit.SECONDS.sleep(5);
            cashWindow.changeState(StateName.valueOf("DELIVERY"));
            logger.info(visitor.getName() + " make an order. Visitor is waiting for delivery of products");
            cashWindow.handleCustomer(visitor);

        } catch (InterruptedException e) {
            logger.info(visitor.getName() + "don't wait.");
            cashWindow.changeState(StateName.valueOf("REST"));
            logger.info(visitor.getName() + " leave restaurant");
        }
    }
}
