/*
Разработаем простое консольное приложение для учета ГСМ на предприятии.
	На вход приложения подается массив следующих значений {"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000", "C300_2-200-45", "C400_2-10-20", "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28", "C100_1-300", "C200_1-100-750", "C300_1-32-15"}
Формат данных - C(CODE_CAR)_гос номер-Пробег-(доп. параметр)
Расшифровка кодов транспортных средств:
100 - легковой авто
200 - грузовой авто - объем перевезенного груза см. куб.
300 - пассажирский транспорт - число перевезенных пассажиров
400 - тяжелая техника(краны) - вес поднятых грузов тонн

Для каждого типа транспортного средства, своя стоимость литра топлива:
100 - 46.10
300 - 47.50
200, 400 - 48.90

Для каждого типа транспортного средства свой расход топлива на 100 км:
100 - 12.5
200 - 12
300 - 11.5
400 - 20

Необходимо вывести:
- общую стоимость расходов на ГСМ, так и расходы на каждый класс авто;
- тип авто имеющий наибольшую стоимость расходов;
- тип авто имеющий наименьшую стоимость расходов;
- реализовать функции которые в разрезе каждого типа авто выводят информацию о каждом авто (тип, номер, пробег, доп. параметр), с сортировкой по пробегу и доп параметру.
 */

package task1;

import java.util.*;

import static java.util.Collections.*;

public class Task1 {

    public static void main(String[] args) {

        // Массив входных данных
        String[] cars = {"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000",
                "C300_2-200-45", "C400_2-10-20", "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28",
                "C100_1-300", "C200_1-100-750", "C300_1-32-15"};

        // Массив цен на топливо
        double[] cost = {46.1, 48.9, 47.5, 48.9};

        // Массив расхода топлива на 100 км
        double[] fuel = {12.5, 12, 11.5, 20};

        // Массивы для заполнения данными по типам авто
        ArrayList<Cars> cars100 = new ArrayList<>();
        ArrayList<Cars> cars200 = new ArrayList<>();
        ArrayList<Cars> cars300 = new ArrayList<>();
        ArrayList<Cars> cars400 = new ArrayList<>();

        // Map для учёта общего расхода по типам транспорта
        HashMap<String, Double> map = new HashMap<>();


        // Разбивка входных данных на массивы по типу транспорта
        for (String car : cars) {
            String[] parts = car.split("[-_]");
            switch (parts[0]) {
                case "C100": {
                    Cars car1 = new Cars(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), 0, cost[0], fuel[0]);
                    cars100.add(car1);
                    continue;
                }
                case "C200": {
                    Cars car1 = new Cars(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), cost[1], fuel[1]);
                    cars200.add(car1);
                    continue;
                }
                case "C300": {
                    Cars car1 = new Cars(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), cost[2], fuel[2]);
                    cars300.add(car1);
                    continue;
                }
                case "C400": {
                    Cars car1 = new Cars(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), cost[3], fuel[3]);
                    cars400.add(car1);
                }
            }
        }

        // Удаление дубликатов
        delDub(cars100);
        delDub(cars200);
        delDub(cars300);
        delDub(cars400);

        // Вывод стоимости расходов по типам траспорта
        System.out.println("Расходы на легковые авто: " + fullCost(cars100));
        System.out.println("Расходы на грузовые авто: " + fullCost(cars200));
        System.out.println("Расходы на пассажирский транспорт: " + fullCost(cars300));
        System.out.println("Расходы на тяжелой технике: " + fullCost(cars400));
        System.out.println("Общая сумма расходов: " + (fullCost(cars100) + fullCost(cars200) + fullCost(cars300) + fullCost(cars400)));
        System.out.println();

        // Занесение данных по типу транспорта и общему расходу в Map
        map.put("легковой транспорт", fullCost(cars100));
        map.put("грузовой транспорт", fullCost(cars200));
        map.put("пассажирский транспорт", fullCost(cars300));
        map.put("тяжелая техника", fullCost(cars400));

        // Нахождение наибольшего значения в Map
        Double max = max(map.values());

        System.out.println("Тип авто имеющий наибольшую стоимость расходов:");
        // Проверка на наличие нескольких ключей с максимальным значением
        for (HashMap.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue().equals(max))
                System.out.println(entry.getKey());
        }
        System.out.println();

        // Нахождение наименьшего значения в Map
        Double min = min(map.values());

        System.out.println("Тип авто имеющий наименьшую стоимость расходов:");
        // Проверка на наличие нескольких ключей с минимальным значением
        for (HashMap.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue().equals(min))
                System.out.println(entry.getKey());
        }
        System.out.println("\n");

        System.out.println("Сортировки по легковым авто:");
        allSort(cars100);

        System.out.println("Сортировки по грузовым авто:");
        allSort(cars200);

        System.out.println("Сортировки по пассажирскому транспорту:");
        allSort(cars300);

        System.out.println("Сортировки по тяжёлой технике:");
        allSort(cars400);
    }

    // Функция удаления дубликатов по номеру транспорта
    // Пробег и доп.параметр складываются
    public static void delDub(ArrayList<Cars> cars) {
        int numder;
        int mileage;
        int parameter;
        for (int j = 0; j < cars.size(); j++) {
            Cars cars1 = cars.get(j);
            numder = cars1.getNumber();
            mileage = cars1.getMileage();
            parameter = cars1.getParameter();
            for (int i = j + 1; i < cars.size(); i++) {
                Cars cars2 = cars.get(i);
                if (numder == cars2.getNumber()) {
                    mileage += cars2.getMileage();
                    parameter += cars2.getParameter();
                    cars1.setMileage(mileage);
                    cars.remove(cars.get(i));
                    i--; // чтобы проверил объект, который сдвинется на место удаленного
                }
            }
        }
    }

    // Метод для нахождения общей стоимости по типу авто
    public static double fullCost (ArrayList<Cars> cars) {

        double sum = 0;
        for (Cars car : cars) {
            sum += car.getMileage() * car.getCost() * car.getFuel() / 100;
        }
        return sum;
    }

    // Метод для сортировки пробега по возрастанию
    public static void sortMileageIncrease (ArrayList<Cars> cars) {

        Comparator<Cars> cmpMileageIncrease = new CmpMileageIncrease();
        cars.sort(cmpMileageIncrease);
    }

    // Метод для сортировки пробега по убыванию
    public static void sortMileageDecrease (ArrayList<Cars> cars) {

        Comparator<Cars> cmpMileageDecrease = new CmpMileageDecrease();
        cars.sort(cmpMileageDecrease);
    }

    // Метод для сортировки доп. параметра по возрастанию
    public static void sortParameterIncrease (ArrayList<Cars> cars) {

        Comparator<Cars> cmpParameterIncrease = new CmpParameterIncrease();
        cars.sort(cmpParameterIncrease);
    }

    // Метод для сортировки доп. параметра по убыванию
    public static void sortParameterDecrease (ArrayList<Cars> cars) {

        Comparator<Cars> cmpParameterDecrease = new CmpParameterDecrease();
        cars.sort(cmpParameterDecrease);
    }

    // Метод для вывода данных по транспорту из массива в консоль
    public static void print (ArrayList<Cars> cars) {

        System.out.println("Тип\t\tНомер\tПробег\tДоп.параметр");
        for (Cars car : cars) {
            System.out.println(car.getType() + "\t" + car.getNumber() + "\t\t" + car.getMileage() + "\t\t" + car.getParameter());
        }
        System.out.println();
    }

    // Метод, объединяющий все сортировки и выводящий данные в консоль
    public static void allSort (ArrayList<Cars> cars) {

        System.out.println("Пробег по возрастанию");
        sortMileageIncrease(cars);
        print(cars);
        System.out.println("Пробег по убыванию");
        sortMileageDecrease(cars);
        print(cars);
        System.out.println("Доп.параметр по убыванию");
        sortParameterIncrease(cars);
        print(cars);
        System.out.println("Доп.параметр по возрастанию");
        sortParameterDecrease(cars);
        print(cars);
        System.out.println();
    }
}

