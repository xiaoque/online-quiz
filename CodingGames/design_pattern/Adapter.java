package CodingGames.design_pattern;/*
 *  @author xiaoque
 *  @create 2025-05-16-11:06
 *  @description
 */

public class Adapter {
    // previous code that can't change (3rd-party, legacy or complex)
    interface Car {
        void refuel();
    }
    // new class that will be used
    class ElectricCar {
        public void getCharged() {
            System.out.println("Electric car is charging...");
        }
    }
    // adapter to convert the data
    class ElectricCarAdapter implements Car {
        private ElectricCar electricCar;

        public ElectricCarAdapter(ElectricCar electricCar) {
            this.electricCar = electricCar;
        }

        @Override
        public void refuel() {
            electricCar.getCharged();
        }
    }
}
