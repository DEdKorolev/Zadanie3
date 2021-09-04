/*
Класс-сравнитель, для сортировки по доп.параметру по убыванию
 */

package task1;

import java.util.Comparator;

public class CmpParameterDecrease implements Comparator<Cars> {

    @Override
    public int compare(Cars cars1, Cars cars2) {
        return cars2.getParameter() - cars1.getParameter();
    }
}
