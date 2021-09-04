/*
Класс-сравнитель, для сортировки по доп.параметру по возрастанию
 */

package task1;

import java.util.Comparator;

public class CmpParameterIncrease implements Comparator<Cars> {

    @Override
    public int compare(Cars cars1, Cars cars2) {
        return cars1.getParameter() - cars2.getParameter();
    }
}
