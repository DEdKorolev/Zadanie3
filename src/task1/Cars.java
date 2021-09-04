/*
Класс транспорт.
 */

package task1;

public class Cars {
    String type; // Тип транспорта
    int number; // Гос. номер
    int mileage; // Пробег
    int parameter; // Доп. параметр
    double cost; // Цена за литр топлива
    double fuel; // Расход топлива на 100 км

    // Контруктор, создает экземпляр класса с параметрами:
    // тип транспорта, гос.номер, пробег, доп.параметр, цена за литр топлива, расход топлива на 100 км
    public Cars(String type, int number, int mileage, int parameter, double cost, double fuel) {
        this.type = type;
        this.number = number;
        this.mileage = mileage;
        this.parameter = parameter;
        this.cost = cost;
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "Cars{" +
                "type='" + type + '\'' +
                ", number=" + number +
                ", mileage=" + mileage +
                ", parameter=" + parameter +
                ", cost=" + cost +
                ", fuel=" + fuel +
                '}';
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getParameter() {
        return parameter;
    }

    public double getCost() {
        return cost;
    }

    public double getFuel() {
        return fuel;
    }
}