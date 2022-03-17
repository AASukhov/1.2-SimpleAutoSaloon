public class Seller {
    private Shop shop;

    public Seller(Shop shop) {
        this.shop = shop;
    }

    public synchronized Car buyCar() {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
        try {
            while (shop.cars.size()==0) {
                System.out.println("Машин нет");
                wait();
            }
            Thread.sleep(1000);
            System.out.println("Покупатель " + Thread.currentThread().getName() + " уехал на новеньком авто");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return shop.cars.remove(0);
    }

    public void acceptCar () {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Производитель toyota выпустил 1 авто");
                Thread.sleep(3000);
                shop.cars.add(new Car());
                synchronized (this) {
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
