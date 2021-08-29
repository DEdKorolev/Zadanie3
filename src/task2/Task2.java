/* Приложение на вход которого поступает текст, а на выходе выводится частотный словарь букв латинского(английского) алфавита. */

package task2;

import java.util.Map;
import java.util.TreeMap;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class Task2 {

    public static void main(String[] args) {

        // Входной текст
        String text = "1233AAAaaabbbсссffffddddhhhhhhhhkkkzzzzzzzzzzzzzzggffлольлдьлд";

        // Перевод текста в заглавные буквы
        text = toUpperCase(text);

        // Map для занесения букв и количества их повторения в отсортированном по алфавиту порядке
        TreeMap<String, Double> frequencyAlphabet = new TreeMap<>();

        // Для подсчёта количества латинских букв
        int countLatin = 0;

        // Цикл для заполнения Map
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i); // Вытаскивает из text символ по индексу
            String s = String.valueOf(c); // Перевод вытащенного символа в String

            // Проверка на наличие латинских букв
            if(s.matches("[a-zA-Z]")) {

                countLatin++; // Подсчёт количества латинских букв

                // Проверка наличия ключа в Map (была ли ранее в переборе буква)
                if (frequencyAlphabet.containsKey(s)) {
                    double count = frequencyAlphabet.get(s);
                    frequencyAlphabet.put(s, count + 1); // увеличение значения на 1 у уже существующего ключа
                } else {
                    frequencyAlphabet.put(s, 1.0); // добавление нового ключа со значением 1

                }
            }
        }

        // Перевод значений количества повторений в их процентное соотношение
        // Вывод частотного словаря
        for (Map.Entry<String, Double> entry : frequencyAlphabet.entrySet()) {
            double i = entry.getValue();
            entry.setValue(i/countLatin);
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
