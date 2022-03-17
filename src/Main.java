public class Main {
    public static void main(String[] args) {
        final Shop shop = new Shop();

        new Thread(null, shop::acceptCar, "Производитель").start();

        for (int i = 1; i <= 10; i++) {
            try {
                new Thread(null, shop::buyCar, "Покупатель " + i).start();
                Thread.sleep(2500);
            } catch (InterruptedException E) {
                E.printStackTrace();
            }
        }
    }
}
