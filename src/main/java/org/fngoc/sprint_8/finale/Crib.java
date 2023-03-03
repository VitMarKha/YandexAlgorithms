package org.fngoc.sprint_8.finale;

/*
-- ПРИНЦИП РАБОТЫ --
Реализовал поиск строки в наборе паттернов.

Создаю префиксное дерево из паттернов, после чего
благодаря динамическому программированию проверю,
состоит ли строка из паттернов в префиксном дереве.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
При чтении паттернов - добавляю их в префиксное дерево.

Префиксное дерево содержит в себе класс ноды,
который имеет 3 поля, ссылка на ребра, саму букву ноды
и индикатор, того что это терминальный узел.

Вставка происходит по принципу добавления нового чара
в map ребер (если его нет), и итерированное на следующую ноду
которой является текущий чар.

Проверка строки происходит благодаря динамическому программированию.
Создаю массив длиной строки, первый элемент true. Если текущий индекс
true, мы начинаем искать строку, по достижению терминального узла.
Последний элемент является ответом - это значит,
что строка состоит из паттернов.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
N - длинна строки.
L - общая длинна всех паттернов.
M - длинна самого длинного паттерна.

При добавлении всех паттернов мы тратим O(L)
или если считать длину по отдельности O(n^2),
где n - длинна одного среднего паттерна.

При поиске строки мы затрачиваем O(N * M).

В общем временная сложность программы O(L + (N * M)).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
В памяти мы держим 2N, строку и массив
для динамического программирования.

Префиксное дерево занимает O(L).

В общем пространственная сложность программы O(N + L).
Константы не учитываем.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Crib {

    private final static class Trie {

        private final static class TrieNode {

            private final Map<Character, TrieNode> children;
            private Character content;
            private boolean isWord;

            public TrieNode() {
                this.children = new HashMap<>();
                this.content = null;
                this.isWord = false;
            }

            public Map<Character, TrieNode> getChildren() {
                return children;
            }

            public boolean isWord() {
                return isWord;
            }

            public void setWord(boolean word) {
                isWord = word;
            }

            public void setContent(Character content) {
                this.content = content;
            }
        }

        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (!current.getChildren().containsKey(ch)) {
                    TrieNode newNode = new TrieNode();
                    newNode.setContent(ch);
                    current.getChildren().put(ch, newNode);
                }
                current = current.getChildren().get(ch);
            }
            current.setWord(true);
        }

        public boolean isPresent(String text) {
            boolean[] dp = new boolean[text.length() + 1];
            dp[0] = true;

            for (int i = 0; i < text.length(); i++) {
                TrieNode current = root;

                if(dp[i]) {
                    for (int j = i; j < text.length() + 1; j++) {
                        if (current.isWord())
                            dp[j] = true;

                        if (j == text.length() || !current.getChildren().containsKey(text.charAt(j)))
                            break;
                        current = current.getChildren().get(text.charAt(j));
                    }
                }
            }
            return dp[text.length()];
        }
    }

    private static final Trie trie = new Trie();

    public static void main(String[] args) throws IOException {
        output(trie.isPresent(input()));
    }

    private static void output(boolean isTrue) {
        if (isTrue)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static String input() throws IOException {
        String line;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine();

            int n = Integer.parseInt(reader.readLine());

            for (int i = 0; i < n; i++)
                trie.insert(reader.readLine());
        }
        return line;
    }
}
