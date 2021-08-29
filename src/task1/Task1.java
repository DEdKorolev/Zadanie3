package task1;

import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        String[] cars = {"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000",
                "C300_2-200-45", "C400_2-10-20", "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28",
                "C100_1-300", "C200_1-100-750", "C300_1-32-15"};
        total_cost(cars);
        infoCars(cars);
    }

    //общую стоимость расходов на ГСМ, так и расходы на каждый класс авто
    private static void total_cost(String[] cars) {
        final double cost100 = 46.10;
        final double cost200 = 48.90;
        final double cost300 = 47.50;
        final double cost400 = 48.90;

        final double fuel100 = 12.5;
        final double fuel200 = 12;
        final double fuel300 = 11.5;
        final double fuel400 = 20;

        double oneKm100 = fuel100*cost100/100;
        double oneKm200 = fuel200*cost200/100;
        double oneKm300 = fuel300*cost300/100;
        double oneKm400 = fuel400*cost400/100;

        double sumFuel100 = 0;
        double sumFuel200 = 0;
        double sumFuel300 = 0;
        double sumFuel400 = 0;

        // Расчет общего расходя топлива по категориям авто
        for (String str : cars) {
            String currentKm = str.substring(7).split("-")[0];
            switch (str.substring(1, 4)) {
                case "100": sumFuel100 += calcCost(currentKm, oneKm100);
                    break;
                case "200": sumFuel200 += calcCost(currentKm, oneKm200);
                    break;
                case "300": sumFuel300 += calcCost(currentKm, oneKm300);
                    break;
                case "400": sumFuel400 += calcCost(currentKm, oneKm400);
                    break;
                default:
            }
        }

        // Map для с общим расходом отдельно для каждого вида транспорта
        HashMap<String, Double> map = new HashMap<>();
        map.put("легковой транспорт", sumFuel100);
        map.put("грузовой транспорт", sumFuel200);
        map.put("пассажирский транспорт", sumFuel300);
        map.put("тяжелая техника", sumFuel400);

        // Нахождение наибольшего значения в Map
        Double max = Collections.max(map.values());

        System.out.println("Тип авто имеющий наибольшую стоимость расходов:");
        for (HashMap.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue().equals(max))
                System.out.println(entry.getKey());
        }
        System.out.println();

        // Нахождение наименьшего значения в Map
        Double min = Collections.min(map.values());

        System.out.println("Тип авто имеющий наименьшую стоимость расходов:");
        for (HashMap.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue().equals(min))
                System.out.println(entry.getKey());
        }
        System.out.println();


        System.out.println("Общая стоимость расходов на ГСМ: " + (sumFuel100 + sumFuel200 + sumFuel300 + sumFuel400));
        System.out.println("Общая стоимость расходов на ГСМ на легковой транспорт: " + sumFuel100);
        System.out.println("Общая стоимость расходов на ГСМ на грузовой транспорт: " + sumFuel200);
        System.out.println("Общая стоимость расходов на ГСМ на пассажирский транспорт: " + sumFuel300);
        System.out.println("Общая стоимость расходов на ГСМ на тяжелую технику(краны): " + sumFuel400);
    }

    // Расчет стоимости израсходованного топлива
    public static double calcCost(String oneKm, double costOneKm) {
        int a = Integer.parseInt(oneKm);
        return costOneKm * a;
    }

    // Сортировка транспорта
    public static void infoCars (String[] cars) {

        // Массив "Тип авто"
        ArrayList<String> car_argument1 = new ArrayList<>();
        for (String car : cars) {
            String[] parts = car.split("[-_]");
            car_argument1.add(parts[0]);
        }

        // Массив "Номер авто"
        ArrayList<String> car_argument2 = new ArrayList<>();
        for (String car : cars) {
            String parts = car.substring(5).split("[-_]")[0];
            car_argument2.add(parts);
        }

        // Массив "Тип авто"
        ArrayList<String> car_argument3 = new ArrayList<>();
        for (String car : cars) {
            String parts = car.substring(7).split("[-_]")[0];
            car_argument3.add(parts);
        }

        // Массив "Доп. параметры"
        ArrayList<String> car_argument4 = new ArrayList<>(); // отдельный лист с доп. параметром
        for (String car : cars) {
            String[] parts = car.split("[-_]");
            if (parts.length == 4)
                car_argument4.add(parts[3]);
            else
                car_argument4.add("0");
        }


        // Проверка дубликатов в массиве по номеру. Суммирование пробега у одинакого типа и номера транспорта.
        // Обнуление значений у дубликатов
        delDub(car_argument1, car_argument2, car_argument3);

        // Проверка дубликатов в массиве по номеру. Суммирование доп.параметра у одинакого типа и номера транспорта.
        // Обнуление значений у дубликатов
        delDub(car_argument1, car_argument2, car_argument4);

        System.out.println();


        // Удаление обнуленных значений из массивов
        for(int i=0; i<car_argument1.size(); i++) {
            if (car_argument3.get(i).equals("0")) {
                car_argument1.remove(i);
                car_argument2.remove(i);
                car_argument3.remove(i);
                car_argument4.remove(i);
                i--;
            }
        }


        // Массив "Номер легковых авто".
        ArrayList<String> car1_argument100 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C100"))
                car1_argument100.add(car_argument2.get(i));
        }

        // Массив "Пробег легковых авто".
        ArrayList<String> car2_argument100 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C100"))
                car2_argument100.add(car_argument3.get(i));
        }

        // Массив "Доп. параметр легковых авто".
        ArrayList<String> car3_argument100 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C100"))
                car3_argument100.add(car_argument4.get(i));
        }

        System.out.println("Вывод отсортированных данных по пробегу по легковым авто");
        sortCar(car1_argument100, car2_argument100, car3_argument100);


        // Массив "Номер грузовых авто".
        ArrayList<String> car1_argument200 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C200"))
                car1_argument200.add(car_argument2.get(i));
        }

        // Массив "Пробег грузовых авто".
        ArrayList<String> car2_argument200 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C200"))
                car2_argument200.add(car_argument3.get(i));
        }

        // Массив "Доп. параметр грузовых авто".
        ArrayList<String> car3_argument200 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C200"))
                car3_argument200.add(car_argument4.get(i));
        }

        System.out.println();
        System.out.println("Вывод отсортированных данных по пробегу по грузовым авто");
        sortCar(car1_argument200, car2_argument200, car3_argument200);

        System.out.println();
        System.out.println("Вывод отсортированных данных по доп. параметру по грузовым авто");
        sortCarDop(car1_argument200, car3_argument200, car2_argument200);


        // Массив "Номер пассажирского транспорта".
        ArrayList<String> car1_argument300 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C300"))
                car1_argument300.add(car_argument2.get(i));
        }

        // Массив "Пробег пассажирского транспорта".
        ArrayList<String> car2_argument300 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C300"))
                car2_argument300.add(car_argument3.get(i));
        }

        // Массив "Доп. параметр пассажиского траспорта".
        ArrayList<String> car3_argument300 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C300"))
                car3_argument300.add(car_argument4.get(i));
        }

        System.out.println();
        System.out.println("Вывод отсортированных данных по пробегу по пассажирскому транспорту");
        sortCar(car1_argument300, car2_argument300, car3_argument300);

        System.out.println();
        System.out.println("Вывод отсортированных данных по доп. параметру по пассажирскому транспорту");
        sortCarDop(car1_argument300, car3_argument300, car2_argument300);


        // Массив "Номер тяжелой техники".
        ArrayList<String> car1_argument400 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C400"))
                car1_argument400.add(car_argument2.get(i));
        }

        // Массив "Пробег тяжелой техники".
        ArrayList<String> car2_argument400 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C400"))
                car2_argument400.add(car_argument3.get(i));
        }

        // Массив "Доп. параметр тяжелой техники"
        ArrayList<String> car3_argument400 = new ArrayList<>();
        for(int i = 0; i < car_argument4.size(); i++) {
            if (car_argument1.get(i).equals("C400"))
                car3_argument400.add(car_argument4.get(i));
        }

        System.out.println();
        System.out.println("Вывод отсортированных данных по пробегу по тяжелой технике");
        sortCar(car1_argument400, car2_argument400, car3_argument400);

        System.out.println();
        System.out.println("Вывод отсортированных данных по доп. параметру по тяжелой технике");
        sortCarDop(car1_argument400, car3_argument400, car2_argument400);
    }

    // Проверка дубликатов в массиве по номеру. Суммирование параметра у одинакого типа и номера транспорта.
    // Обнуление значений у дубликатов
    public static void delDub (ArrayList<String> car_argument1, ArrayList<String> car_argument2, ArrayList<String> car_argument3) {
        for(int i=0; i<car_argument1.size(); i++) {
            for (int j=i+1; j<car_argument1.size(); j++) {
                if(car_argument1.get(i).equals(car_argument1.get(j))) {
                    if(car_argument2.get(i).equals(car_argument2.get(j))) {
                        car_argument3.set(i,  Integer.toString(Integer.parseInt(car_argument3.get(i)) + Integer.parseInt(car_argument3.get(j))));
                        car_argument3.set(j, "0");
                    }
                }
            }
        }
    }


    // Функция сортировки авто по пробегу
    public static void sortCar (ArrayList<String> car1_argument, ArrayList<String> car2_argument, ArrayList<String> car3_argument) {

        for (int i = 0; i < car2_argument.size(); i++) {
            int currentMin = Integer.parseInt(car2_argument.get(i));
            int currentMinIndex = i;
            int currentMin2 = 0;
            int currentMin3 = 0;

            for (int j = i + 1; j < car2_argument.size(); j++) {
                if (currentMin < Integer.parseInt(car2_argument.get(j))) {
                    currentMin = Integer.parseInt(car2_argument.get(j));
                    currentMinIndex = j;
                    currentMin2 = Integer.parseInt(car1_argument.get(j));
                    currentMin3 = Integer.parseInt(car3_argument.get(j));
                }
            }
            if (currentMinIndex != i) {
                car2_argument.set(currentMinIndex, Integer.toString(Integer.parseInt(car2_argument.get(i))));
                car2_argument.set(i, Integer.toString(currentMin));
                car1_argument.set(currentMinIndex, Integer.toString(Integer.parseInt(car1_argument.get(i))));
                car1_argument.set(i, Integer.toString(currentMin2));
                car3_argument.set(currentMinIndex, Integer.toString(Integer.parseInt(car3_argument.get(i))));
                car3_argument.set(i, Integer.toString(currentMin3));

            }
        }

        System.out.println("Номер\tПробег\tДоп.парметр");
        for (int i = 0; i < car1_argument.size(); i++)
            System.out.println(car1_argument.get(i) + "\t\t" + car2_argument.get(i) + "\t\t" + car3_argument.get(i));
    }


    // Функция сортировки авто по доп. параметру
    public static void sortCarDop (ArrayList<String> car1_argument, ArrayList<String> car3_argument, ArrayList<String> car2_argument) {

        for (int i = 0; i < car3_argument.size(); i++) {
            int currentMin = Integer.parseInt(car3_argument.get(i));
            int currentMinIndex = i;
            int currentMin2 = 0;
            int currentMin3 = 0;

            for (int j = i + 1; j < car3_argument.size(); j++) {
                if (currentMin < Integer.parseInt(car3_argument.get(j))) {
                    currentMin = Integer.parseInt(car3_argument.get(j));
                    currentMinIndex = j;
                    currentMin2 = Integer.parseInt(car1_argument.get(j));
                    currentMin3 = Integer.parseInt(car2_argument.get(j));
                }
            }
            if (currentMinIndex != i) {
                car3_argument.set(currentMinIndex, Integer.toString(Integer.parseInt(car3_argument.get(i))));
                car3_argument.set(i, Integer.toString(currentMin));
                car1_argument.set(currentMinIndex, Integer.toString(Integer.parseInt(car1_argument.get(i))));
                car1_argument.set(i, Integer.toString(currentMin2));
                car2_argument.set(currentMinIndex, Integer.toString(Integer.parseInt(car2_argument.get(i))));
                car2_argument.set(i, Integer.toString(currentMin3));
            }
        }

        System.out.println("Номер\tПробег\tДоп. параметр");
        for (int i = 0; i < car1_argument.size(); i++)
            System.out.println(car1_argument.get(i) + "\t\t" + car2_argument.get(i)+ "\t\t" + car3_argument.get(i));
    }
}

