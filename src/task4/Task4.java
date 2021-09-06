/*
Метод, который получает на вход Map<K, V> и возвращает Map, где ключи и значения поменяны местами.
Так как значения исходной Map могут совпадать, то тип ключа в Map будет уже не K,
а Collection<K>: Map<V, Collection<K>>
 */

package task4;

import java.util.*;
import java.util.Set;

public class Task4 {

    public static void main(String[] args) {

        // Map для тестирования
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 2);
        map.put("d", 3);

        // Вызов метода перестановки и вывод данных в консоль
        System.out.println(inverse(map));
    }

    // Метод перестановки ключей и значений с учётом возможного дублирования значений
    public static <K, V> Map<V, Collection<K>> inverse(Map<? extends K, ? extends V> map){

        // Создаем карту, в которой ключем будут значения, а значения коллекций из ключей
        Map<V, Collection<K>> resultMap = new HashMap<>();

        // Создаем сет ключей из map
        Set<K> keys = (Set<K>) map.keySet();

        // Перебираем ключи
        for(K key : keys){
            V value = map.get(key); // Запрашиваем значение у ключа из map и присваиваем значение переменной value

            // Сопоставляет новый ключ и новый сет значений
            // Если ключ (значение value) не повторяется keySet обнуляется
            // Если ключ (значение value) повторяется keySet не обнуляется
            resultMap.compute(value, (val, keysSet) -> {

                // Если keySet не существует, то создать новый keySet
                if(keysSet == null){
                    keysSet = new HashSet<>();
                }

                keysSet.add(key); // Добавить в keySet значение

                return keysSet; // Вернуть keySet
            });
        }
        return resultMap; // Вернуть resultMap
    }
}
