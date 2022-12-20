package org.vitmarkha.finaltasks.sprintfour;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SearchSystem {

    private static final List<Map<String, Integer>> docs = new ArrayList<>();
    private static final List<Map<String, Integer>> request = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        String relevantDocuments = searchRelevantDocuments();
        output(relevantDocuments);

        System.out.println(docs);
        System.out.println(request);
    }

    private static String searchRelevantDocuments() {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, Integer> mapRelevance;
        for (Map<String, Integer> req : request) {
            int index = 1;
            mapRelevance = new HashMap<>();
            for (Map<String, Integer> doc : docs) {
                int countRelevance = 0;
                for (int i = 0; i < req.size(); i++) {
                    for (Map.Entry d : doc.entrySet()) {
                        if (req.containsKey(d.getKey()))
                            countRelevance += (int) d.getValue();
                    }
                }
                if (countRelevance > 0) {
                    mapRelevance.put(index, countRelevance);
//                    stringBuilder.append(index).append(' ');
                }
                index += 1;
            }

            List<Map.Entry<Integer, Integer>> list = mapRelevance.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
            for (int i = 0; i < list.size(); i++) {
                if (i > 4)
                    break;
                stringBuilder.append(list.get(i).getKey()).append(' ');
            }
            stringBuilder.append('\n');
//            System.out.println(mapRelevance);
        }
        return stringBuilder.toString();
    }

    private static void input() throws IOException {
        final int N;
        final int M;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
            readListMap(N, reader, docs);
            M = Integer.parseInt(reader.readLine());
            readListMap(M, reader, request);
        }
    }

    private static void readListMap(int n, BufferedReader reader, List<Map<String, Integer>> input) throws IOException {
        StringTokenizer tokenizer;

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            Map<String, Integer> map = new HashMap<>();

            while (tokenizer.hasMoreTokens()) {
                String str = tokenizer.nextToken();
                if (!map.containsKey(str))
                    map.put(str, 1);
                else
                    map.put(str, map.get(str) + 1);
            }
            input.add(map);
        }
    }

    private static void output(String output) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(output);
        writer.flush();
    }
}
