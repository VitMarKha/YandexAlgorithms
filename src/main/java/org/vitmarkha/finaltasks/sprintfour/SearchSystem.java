package org.vitmarkha.finaltasks.sprintfour;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SearchSystem {

    private static final List<Map<String, Integer>> docs = new ArrayList<>();
    private static final List<Set<String>> requests = new ArrayList<>();

    private static final Map<String, Map<Integer, Integer>> relevanceWords = new HashMap<>();
    private static final Map<Integer, Map<Integer, Integer>> result = new HashMap<>();

    private static final StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        countRelevanceDocumentsByRequests();
        sortMapInRequests();
        output();
    }

    private static void sortMapInRequests() {
        for (Map.Entry<Integer, Map<Integer, Integer>> e : result.entrySet()) {

            List<Integer> list = e.getValue().entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder((x, y) ->  {
                        if (x.getValue() - y.getValue() == 0)
                            return y.getKey() - x.getKey();
                        else
                            return x.getValue() - y.getValue();
                    }))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            for (int i = 0; i < list.size(); i++) {
                if (i > 4)
                    break;
                output.append(list.get(i)).append(' ');
            }
            output.append('\n');
        }
    }

    private static void countRelevanceDocumentsByRequests() {
        for (int indexRequests = 0; indexRequests < requests.size(); indexRequests++) {

            for (String word : requests.get(indexRequests)) {
                if (!relevanceWords.containsKey(word))
                    continue;

                if (!result.containsKey(indexRequests)) {
                    Map<Integer, Integer> map = new HashMap<>(relevanceWords.get(word));
                    result.put(indexRequests, map);
                }
                else
                    sumMap(indexRequests, word);
            }
        }
    }

    private static void sumMap(int indexRequests, String word) {
        Map<Integer, Integer> resultMap = result.get(indexRequests);
        Map<Integer, Integer> relevanceWordsMap = relevanceWords.get(word);

        for (Map.Entry<Integer, Integer> map : relevanceWordsMap.entrySet()) {
            if (!resultMap.containsKey(map.getKey()))
                resultMap.put(map.getKey(), map.getValue());
            else
                resultMap.put(map.getKey(), resultMap.get(map.getKey()) + map.getValue());
        }
    }

    private static void wordRelevanceCountByDocuments(String word) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int indexDoc = 0; indexDoc < docs.size(); indexDoc++) {
            if (docs.get(indexDoc).containsKey(word)) {
                map.put(indexDoc + 1, docs.get(indexDoc).get(word));
                relevanceWords.put(word, map);
            }
        }
    }

    private static void input() throws IOException {
        final int N;
        final int M;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            parsDocs(N, reader);
            M = Integer.parseInt(reader.readLine());
            parsRequest(M, reader);
        }
    }

    private static void parsDocs(final int N, final BufferedReader reader) throws IOException {
        StringTokenizer tokenizer;

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            Map<String, Integer> map = new HashMap<>();
            while (tokenizer.hasMoreTokens()) {
                String str = tokenizer.nextToken();
                if (!map.containsKey(str))
                    map.put(str, 1);
                else
                    map.put(str, map.get(str) + 1);
            }
            docs.add(map);
        }
    }

    private static void parsRequest(final int N, final BufferedReader reader) throws IOException {
        StringTokenizer tokenizer;
        final Set<String> usedWords = new HashSet<>();

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            Set<String> set = new HashSet<>();
            while (tokenizer.hasMoreTokens()) {
                String str = tokenizer.nextToken();

                if (!usedWords.contains(str)) {
                    wordRelevanceCountByDocuments(str);
                    usedWords.add(str);
                }
                set.add(str);
            }
            requests.add(set);
        }
    }

    private static void output() throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(output.toString());
        writer.flush();
    }
}
