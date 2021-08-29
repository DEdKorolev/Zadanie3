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
        Map<V, Collection<K>> resultMap = new HashMap<>();

        Set<K> keys = (Set<K>) map.keySet();
        for(K key : keys){
            V value = map.get(key);
            resultMap.compute(value, (v, ks) -> {
                if(ks == null){
                    ks = new HashSet<>();
                }
                ks.add(key);
                return ks;
            });
        }

        return resultMap;
    }
}
