/*
Функция, которая на вход получает массив строк, в формате имя_игрока количество_очков.
Выводит победителя данной игры. Победителем считается тот, кто раньше набрал максимальное количество очков.
Входные данные = "Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1"
 */

package task5;

import java.util.Collections;
import java.util.HashMap;

public class Task5 {

    public static void main(String[] args) {

        // Входной массив данных
        String[] players = {"Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1"};

        // Вызов функции для определения победителя
        Winner(players);
    }

    // Функция определения победителя
    public static void Winner (String[] players) {

        Integer max = 0; // Для поиска первого максимального значения очков
        HashMap<String, Integer> map = new HashMap<>(); // Для подсчёта очков
        String winner = new String(); // Для записи лидера

        // Заполнение Map
        for (String player: players) {
            String[] part = player.split("[ ]"); // Разделяем каждый элемент массива поочередно на две части

            // Проверка на наличие игрока в Map
            if (map.containsKey(part[0])) {
                int count = Integer.valueOf(part[1]) + map.get(part[0]);
                map.put(part[0], count); // при наличии увеличение количества очков
            } else {
                map.put(part[0], Integer.valueOf(part[1])); // добавление нового игрока в Map
            }

            // Поиск победителя
            if (max<Collections.max(map.values())){

                max = Collections.max(map.values());

                for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
                    if (entry.getValue().equals(max))
                        winner = entry.getKey();
                }
            }
        }

        // Вывод имени победителя
        System.out.println(winner);
    }
}
