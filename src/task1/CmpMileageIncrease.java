/*
Класс-сравнитель, для сортировки по пробегу по возрастанию
 */

package task1;

import java.util.Comparator;

public class CmpMileageIncrease implements Comparator<Cars> {

    @Override
    public int compare(Cars cars1, Cars cars2) {
        return cars1.getMileage() - cars2.getMileage();
    }
}
