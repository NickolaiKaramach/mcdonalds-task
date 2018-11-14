package by.etc.karamach.restaurant.mcdonald;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import by.etc.karamach.restaurant.mcdonald.customer.RegisteredVisitor;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;

import java.util.concurrent.TimeUnit;

public class Runner {
    public static void main(String[] args) throws InterruptedException {

        McdonaldCashWindow cashWindow1 = new McdonaldCashWindow("Window1");
        McdonaldCashWindow cashWindow2 = new McdonaldCashWindow("Window2");

        RestaurantVisitor visitor1 = new RestaurantVisitor(cashWindow1, "Customer1");
        RestaurantVisitor visitor2 = new RestaurantVisitor(cashWindow1, "Customer2");
        RestaurantVisitor visitor3 = new RestaurantVisitor(cashWindow1, "Customer3");

        RestaurantVisitor visitor4 = new RestaurantVisitor(cashWindow2, "Customer4");
        RestaurantVisitor visitor5 = new RestaurantVisitor(cashWindow2, "Customer5");
        RestaurantVisitor visitor6 = new RestaurantVisitor(cashWindow2, "Customer6");

        RegisteredVisitor registeredVisitor = new RegisteredVisitor(cashWindow1, "Registered1");
        Thread threadRegistred = new Thread(registeredVisitor);
        Thread thread1 = new Thread(visitor1);
        Thread thread2 = new Thread(visitor2);
        Thread thread3 = new Thread(visitor3);


        Thread thread4 = new Thread(visitor4);
        Thread thread5 = new Thread(visitor5);
        Thread thread6 = new Thread(visitor6);


        thread1.start();
        thread2.start();
        thread3.start();

        TimeUnit.SECONDS.sleep(2);

        thread4.start();
        thread5.start();
        thread6.start();

        TimeUnit.SECONDS.sleep(3);

        threadRegistred.start();
        System.out.println("All customers came!");

    }

}
