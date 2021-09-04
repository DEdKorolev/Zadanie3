/*
Класс-сравнитель, для сортировки по пробегу по убыванию
 */

package task1;

import java.util.Comparator;

public class CmpMileageDecrease implements Comparator<Cars> {

    @Override
    public int compare(Cars cars1, Cars cars2) {
        return cars2.getMileage() - cars1.getMileage();
    }
}
