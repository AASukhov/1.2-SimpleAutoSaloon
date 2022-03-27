public class Seller {
    private Shop shop;
    private static final int RECEIVE_TIME = 1000;
    private static final int CAR_INPUT = 3000;
    private static final int CARS = 10;


    public Seller(Shop shop) {
        this.shop = shop;
    }

    public synchronized Car buyCar() {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
        try {
            while (shop.cars.size() == 0) {
                    System.out.println("Машин нет");
                    wait();
            }
            Thread.sleep(RECEIVE_TIME);
            System.out.println("Покупатель " + Thread.currentThread().getName() + " уехал на новеньком авто");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return shop.cars.remove(0);
    }

    public void acceptCar () {
        for (int i = 0; i < CARS; i++) {
            try {
                System.out.println("Производитель toyota выпустил 1 авто");
                Thread.sleep(CAR_INPUT);
                synchronized (this) {
                    shop.cars.add(new Car());
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
