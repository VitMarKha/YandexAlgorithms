package org.fngoc.finaltasks.sprintfour;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал поисковую систему при использовании HashMap.

Релевантность - это кол-во раз, которое встречается слово в документе.

Считываю документы в HashMap<Слово, HashMap<Номер_документа, Релевантность)>>,
во время считывания я так же подсчитываю кол-во (релевантность) каждого уникального слова.

После, начинаю считывать запросы. Каждое прочитанное слово запроса,
проверяется на нахождение в документах и в зависимости от нахождения,
вкладываю информацию в результирующую структуру HashMap<Номер_документа, Релевантность>.
Когда все слова в запросе проверены, я начинаю вытаскивать в цикле
не более 5 максимальных элементов в результирующей структуре.
Добавляю эти элементы в строку output.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Структура документов хранит все уникальные слова.

Результирующая структура запроса создается каждый раз новая,
при потере ссылки на нее, сборщик мусора ее очищает.

Для отбрасывания повторяющихся слов в запросе,
используется Set repetitionCheck.

Для поиска keyMaxRelevance в структуре запроса, был написан отдельный компоратор.
После нахождения keyMaxRelevance, мы его удаляем из результирующей структуры,
что бы найти следующий максимальный ключ.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Выполнение программы происходит во время считывания запросов
и завершается по окончанию чтения.

Получение данных из структур, происходит по ключу
в HashMap и HashSet за O(1). Установка данных в эти
структуры так же происходит за O(1).

Скорость работы программы зависит от ко-ва документов N,
кол-ва слов в документе n, кол-ва запросов M и кол-ва слов в запросе m.

Если считать, что все слова в документах и в запросах будут уникальными,
то это худший вариант на чтение данных: O(N * n * M * m).
А значит и худший вариант на выполнение программы.

В лучшем и среднем случае документы и запросы имеют одинаковые слова,
что позволяет пропускать их. Предположим, что у нас есть коэффициент
уникальности слов α, тогда в лучшем и среднем случае O((N * n * M * m) / α).

Так же у нас есть 5 итераций для поиска максимальных по релевантности ключей,
но мы их отбрасываем, так как это константа.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Программа, держит в памяти n уникальных элементов документов,
постоянная пространственная сложность O(n). Так как нам нужен
постоянный доступ к ним для расчета релевантности запросов.
*/

import java.io.*;
import java.util.*;

public class SearchSystem {

    private static final Map<String, Map<Integer, Integer>> docs = new HashMap<>();

    public static void main(String[] args) throws IOException {
        output(input());
    }

    private static String input() throws IOException {
        final int N;
        final int M;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            parsDocs(N, reader);
            M = Integer.parseInt(reader.readLine());
            return parsRequestAndMakeLine(M, reader);
        }
    }

    private static void parsDocs(final int N, final BufferedReader reader) throws IOException {
        StringTokenizer tokenizer;

        for (int indexDoc = 0; indexDoc < N; indexDoc++) {
            tokenizer = new StringTokenizer(reader.readLine());

            while (tokenizer.hasMoreTokens())
                putMapByWord(tokenizer.nextToken(), indexDoc);
        }
    }

    private static void putMapByWord(String word, int indexDoc) {
        Map<Integer, Integer> map;

        if (!docs.containsKey(word)) {
            map = new HashMap<>();
            map.put(indexDoc, 1);
            docs.put(word, map);
        } else {
            map = docs.get(word);
            if (!map.containsKey(indexDoc))
                map.put(indexDoc, 1);
            else
                map.put(indexDoc, map.get(indexDoc) + 1);
        }
    }

    private static String parsRequestAndMakeLine(final int N, final BufferedReader reader) throws IOException {
        StringBuilder output = new StringBuilder();
        StringTokenizer tokenizer;
        Set<String> repetitionCheck;
        Map<Integer, Integer> result;

        for (int indexRequests = 0; indexRequests < N; indexRequests++) {
            tokenizer = new StringTokenizer(reader.readLine());
            repetitionCheck = new HashSet<>();
            result = new HashMap<>();

            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();

                if (!docs.containsKey(word) || repetitionCheck.contains(word))
                    continue;
                if (result.isEmpty())
                    result.putAll(docs.get(word));
                else
                    sumMap(word, result);
                repetitionCheck.add(word);
            }
            setRequestIndexInLine(output, result);
        }
        return output.toString();
    }

    private static void setRequestIndexInLine(StringBuilder output, Map<Integer, Integer> map) {
        for (int i = 0; i < 5; i++) {
            if (map == null || map.isEmpty())
                break;

            int keyMaxRelevance = Collections.max(map.keySet(), (x, y) -> {
                if (map.get(x) - map.get(y) == 0)
                    return y - x;
                else
                    return map.get(x) - map.get(y);
            });

            output.append(keyMaxRelevance + 1).append(' ');
            map.remove(keyMaxRelevance);
        }
        output.append('\n');
    }

    private static void sumMap(String word, Map<Integer, Integer> result) {
        for (Map.Entry<Integer, Integer> map : docs.get(word).entrySet())
            result.merge(map.getKey(), map.getValue(), Integer::sum);
    }

    private static void output(String output) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(output);
        writer.flush();
    }
}
