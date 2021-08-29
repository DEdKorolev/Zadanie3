/*
Метод, который на вход получает коллекцию объектов, а возвращает коллекцию уже без дубликатов
 */

package task3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Task3 {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");

        System.out.println(removeDuplicates(list));
    }

    // Метод, который на вход получает коллекцию объектов, а возвращает коллекцию уже без дубликатов
    public static <T> Collection<T> removeDuplicates(Collection<T> collection) {
        return new HashSet<>(collection);
    }
}
