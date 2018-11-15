package by.etc.karamach.restaurant.mcdonald;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import by.etc.karamach.restaurant.mcdonald.customer.RegisteredVisitor;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Runner {
    private static final Logger logger = LogManager.getLogger("default");

    public static void main(String[] args) throws InterruptedException {

        McdonaldCashWindow cashWindow1 = new McdonaldCashWindow("Window 1");
        McdonaldCashWindow cashWindow2 = new McdonaldCashWindow("Window 2");

        System.out.println(cashWindow1);

        RestaurantVisitor visitor1 = new RestaurantVisitor(cashWindow1, "Customer 1");
        RestaurantVisitor visitor2 = new RestaurantVisitor(cashWindow1, "Customer 2");
        RestaurantVisitor visitor3 = new RestaurantVisitor(cashWindow1, "Customer 3");

        RestaurantVisitor visitor4 = new RestaurantVisitor(cashWindow2, "Customer 4");
        RestaurantVisitor visitor5 = new RestaurantVisitor(cashWindow2, "Customer 5");
        RestaurantVisitor visitor6 = new RestaurantVisitor(cashWindow2, "Customer 6");

        RegisteredVisitor registeredVisitor1 = new RegisteredVisitor(cashWindow1, "Registered 1");
        RegisteredVisitor registeredVisitor2 = new RegisteredVisitor(cashWindow1, "Registered 2");

        Thread threadRegistered1 = new Thread(registeredVisitor1);
        Thread threadRegistered2 = new Thread(registeredVisitor2);

        Thread thread1 = new Thread(visitor1);
        Thread thread2 = new Thread(visitor2);
        Thread thread3 = new Thread(visitor3);


        Thread thread4 = new Thread(visitor4);
        Thread thread5 = new Thread(visitor5);
        Thread thread6 = new Thread(visitor6);


        thread1.start();
        thread2.start();
        thread3.start();

        //Sleep to make difference in time for two groups
        TimeUnit.SECONDS.sleep(2);

        thread4.start();
        thread5.start();
        thread6.start();

        //Sleep to make difference in time for registered customers
        TimeUnit.SECONDS.sleep(3);

        threadRegistered1.start();
        threadRegistered2.start();

        if (logger.isInfoEnabled()) {
            logger.info("All customers started!");
        }

    }

}
