package by.etc.karamach.restaurant.mcdonald;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import by.etc.karamach.restaurant.mcdonald.customer.RegisteredVisitor;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Runner {
    private static final Logger logger = LogManager.getLogger("default");
    private static final int TIME_BEFORE_REGISTERED_COME = 3;
    private static final int TIME_BEFORE_NEXT_CASH_WINDOW_OPENED = 2;

    public static void main(String[] args) throws InterruptedException {

        Initializer initializer = new Initializer().invoke();
        Thread registeredVisitor1 = initializer.getThreadRegistered1();
        Thread registeredVisitor2 = initializer.getThreadRegistered2();
        Thread visitor1 = initializer.getThread1();
        Thread visitor2 = initializer.getThread2();
        Thread visitor3 = initializer.getThread3();
        Thread visitor4 = initializer.getThread4();
        Thread visitor5 = initializer.getThread5();
        Thread visitor6 = initializer.getThread6();


        visitor1.start();
        visitor2.start();
        visitor3.start();

        //Sleep to make difference in time for two groups
        TimeUnit.SECONDS.sleep(TIME_BEFORE_NEXT_CASH_WINDOW_OPENED);

        visitor4.start();
        visitor5.start();
        visitor6.start();

        //Sleep to make difference in time for registered customers
        TimeUnit.SECONDS.sleep(TIME_BEFORE_REGISTERED_COME);

        registeredVisitor1.start();
        registeredVisitor2.start();

        if (logger.isInfoEnabled()) {
            logger.info("All customers started!");
        }

    }

    private static class Initializer {
        private Thread threadRegistered1;
        private Thread threadRegistered2;
        private Thread thread1;
        private Thread thread2;
        private Thread thread3;
        private Thread thread4;
        private Thread thread5;
        private Thread thread6;

        public Thread getThreadRegistered1() {
            return threadRegistered1;
        }

        public Thread getThreadRegistered2() {
            return threadRegistered2;
        }

        public Thread getThread1() {
            return thread1;
        }

        public Thread getThread2() {
            return thread2;
        }

        public Thread getThread3() {
            return thread3;
        }

        public Thread getThread4() {
            return thread4;
        }

        public Thread getThread5() {
            return thread5;
        }

        public Thread getThread6() {
            return thread6;
        }

        public Initializer invoke() {
            McdonaldCashWindow cashWindow1 = new McdonaldCashWindow("Window 1");
            McdonaldCashWindow cashWindow2 = new McdonaldCashWindow("Window 2");

            RestaurantVisitor visitor1 = new RestaurantVisitor(cashWindow1, "Customer 1");
            RestaurantVisitor visitor2 = new RestaurantVisitor(cashWindow1, "Customer 2");
            RestaurantVisitor visitor3 = new RestaurantVisitor(cashWindow1, "Customer 3");

            RestaurantVisitor visitor4 = new RestaurantVisitor(cashWindow2, "Customer 4");
            RestaurantVisitor visitor5 = new RestaurantVisitor(cashWindow2, "Customer 5");
            RestaurantVisitor visitor6 = new RestaurantVisitor(cashWindow2, "Customer 6");

            RegisteredVisitor registeredVisitor1 = new RegisteredVisitor(cashWindow1, "Registered 1");
            RegisteredVisitor registeredVisitor2 = new RegisteredVisitor(cashWindow1, "Registered 2");

            threadRegistered1 = new Thread(registeredVisitor1);
            threadRegistered2 = new Thread(registeredVisitor2);

            thread1 = new Thread(visitor1);
            thread2 = new Thread(visitor2);
            thread3 = new Thread(visitor3);


            thread4 = new Thread(visitor4);
            thread5 = new Thread(visitor5);
            thread6 = new Thread(visitor6);
            return this;
        }
    }
}
