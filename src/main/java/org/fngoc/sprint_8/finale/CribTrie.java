package org.fngoc.sprint_8.finale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CribTrie {

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

        public int isPresent(String text) {
            for (int i = 0; i < text.length(); i++) {
                TrieNode current = root;
                int offset = 0;
                boolean missMatchNotFound = true;

                while (missMatchNotFound && (i + offset) < text.length()) {
                    char ch = text.charAt(i + offset);

                    if (current.getChildren().containsKey(ch)) {
                        current = current.getChildren().get(ch);

                        if (current.isWord())
                            return offset + 1;
                        offset += 1;
                    } else
                        missMatchNotFound = false;
                }
            }
            return -1;
        }
    }

    private static final Trie trie = new Trie();
    private static String line;

    public static void main(String[] args) throws IOException {
        input();

        while (!line.isEmpty()) {
            int endMatch = trie.isPresent(line);
            if (endMatch != -1)
                line = line.substring(endMatch);
            else
                break;
        }

        output(line.isEmpty());
    }

    private static void output(boolean isTrue) {
        if (isTrue)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static void input() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine();

            int n = Integer.parseInt(reader.readLine());

            for (int i = 0; i < n; i++)
                trie.insert(reader.readLine());
        }
    }
}
