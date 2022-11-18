package org.vitmarkha;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    ///////////////////Скобочная последовательность///////////////////
    private static final Map<Character, Character> mapBracket = new HashMap<>();

    static {
        mapBracket.put('(', ')');
        mapBracket.put('{', '}');
        mapBracket.put('[', ']');
    }

    public static void bracketSequence() throws IOException {
        //input
        String line;
        Stack<Character> stack = new Stack<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine().strip();

            for (int i = 0; i < line.length(); i++)
                stack.push(line.charAt(i));
        }

        //program and output
        System.out.println(isBracketSequence(stack));
    }

    private static String isBracketSequence(Stack<Character> stack) {
        Stack<Character> buffer = new Stack<>();
        char bracket;

        while (!stack.isEmpty()) {
            bracket = stack.pop();

            if (isCloseBracket(bracket))
                buffer.push(bracket);
            else if (isOpenBracket(bracket)) {
                if (buffer.empty() || !compareBracket(bracket, buffer.pop()))
                    return "False";
            } else
                return "False";
        }
        return "True";
    }

    private static boolean compareBracket(char openBracket, char closeBracket) {
        return mapBracket.get(openBracket).equals(closeBracket);
    }

    private static boolean isCloseBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean isOpenBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    ///////////////////Стек - Max и Стек - MaxEffective///////////////////
    private static List<Integer> stack = new ArrayList<>();
    private static List<Integer> stackMax = new ArrayList<>();

    public static void stackMax() throws IOException {
        //input, output and program
        int N;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine().strip());
            StringTokenizer tokenizer;

            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String command = tokenizer.nextToken();
                if (command.equals("push")) {
                    int num = Integer.parseInt(tokenizer.nextToken());
                    push(num);
                } else if (command.equals("pop"))
                    pop();
                else
                    get_max();
            }
        }
    }

    private static void push(int num) {
        if (stackMax.isEmpty() || num >= stackMax.get(stackMax.size() - 1))
            stackMax.add(num);
        stack.add(num);
    }

    private static void pop() {
        if (stack.isEmpty())
            System.out.println("error");
        else {
            if (!stackMax.isEmpty() && Objects.equals(stack.get(stack.size() - 1), stackMax.get(stackMax.size() - 1)))
                stackMax.remove(stackMax.size() - 1);
            stack.remove(stack.size() - 1);
        }
    }

    private static void get_max() {
        if (stack.isEmpty())
            System.out.println("None");
        else
            System.out.println(stackMax.get(stackMax.size() - 1));
    }

    ///////////////////Всё наоборот///////////////////
    public static Node2<String> wayAround(Node2<String> head) {
        Node2<String> pointer = head;
        int len = 0;

        while (pointer.next != null) {
            pointer = pointer.next;
            len += 1;
        }

        Node2<String> resutlt = new Node2<>(pointer.value, null, null);
        Node2<String> headResutlt = resutlt;
        for (int i = 0; i < len; i++) {

            if (pointer.prev != null)
                resutlt.next = new Node2<>(pointer.prev.value, pointer.prev, pointer.next);
            if (pointer.next != null)
                resutlt.prev = new Node2<>(pointer.next.value, pointer.next, pointer.prev);

            resutlt = resutlt.next;
            pointer = pointer.prev;
            if (i + 1 == len)
                resutlt.next = null;
        }
        return headResutlt;
    }

    static class Node2<V> {
        public V value;
        public Node2<V> next;
        public Node2<V> prev;

        public Node2(V value, Node2<V> next, Node2<V> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    ///////////////////Заботливая мама///////////////////
    public static int caringMother(Node<String> head, String elem) {
        int index = 0;

        while (head != null) {
            if (head.value.equals(elem))
                return index;
            head = head.next;
            index += 1;
        }
        return -1;
    }

    ///////////////////Нелюбимое дело///////////////////
    public static Node<String> unlovedBusiness(Node<String> head, int idx) {
        Node<String> pointer = head;
        int delete = 1;

        if (idx == 0)
            return head.next;

        if (idx == lenList(head))
            delete = 2;

        for (int i = 0; i < idx - delete; i++)
            pointer = pointer.next;

        pointer.next = pointer.next.next;
        return head;
    }

    private static int lenList(Node<String> head) {
        int len = 0;

        while (head != null) {
            head = head.next;
            len += 1;
        }
        return len;
    }

    ///////////////////Список дел///////////////////
    static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void todoList(Node<String> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    ///////////////////Мониторинг///////////////////
    public static void monitoring() throws IOException {
        //input
        int N;
        int M;
        int[][] matrix;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine().strip());
            M = Integer.parseInt(reader.readLine().strip());

            StringTokenizer tokenizer;
            matrix = new int[N][M];
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < M; j++)
                    matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        //program
        int[][] reversMatrix = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                reversMatrix[i][j] = matrix[j][i];
            }
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                writer.write(String.valueOf(reversMatrix[i][j]));
                writer.append(' ');
            }
            writer.append('\n');
        }
        writer.flush();
    }

    ///////////////////Лишняя буква///////////////////
    public static void extraLetter() throws IOException {
        //input
        String S;
        String T;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            S = reader.readLine().strip();
            T = reader.readLine().strip();
        }

        //program and output
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < S.length(); i++)
            map.put(S.charAt(i), map.get(S.charAt(i)) == null ? 1 : map.get(S.charAt(i)) + 1);

        for (int i = 0; i < T.length(); i++) {
            Character ch = T.charAt(i);

            if (map.containsKey(ch) && map.get(ch) != 0) {
                map.put(ch, map.get(ch) - 1);
            } else {
                System.out.println(ch);
                return;
            }
        }
    }

    ///////////////////Списочная форма///////////////////
    public static void listForm() throws IOException {
        //input
        int X;
        int K;
        int formNumber;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            X = Integer.parseInt(reader.readLine().strip());

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            StringBuilder form = new StringBuilder();
            for (int i = 0; i < X; i++)
                form.append(tokenizer.nextToken());
            formNumber = Integer.parseInt(form.toString());

            K = Integer.parseInt(reader.readLine().strip());
        }

        //program
        String result = String.valueOf(formNumber + K);

        //output
        for (int i = 0; i < result.length(); i++) {
            System.out.print(result.charAt(i) + " ");
        }
    }

    ///////////////////Факторизация///////////////////
    public static void factorization() throws IOException {
        //input
        int N;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine().strip());
        }

        //program
        List<Integer> resultList = new ArrayList<>();
        int divider = 2;

        while (divider * divider <= N) {
            if (N % divider == 0) {
                resultList.add(divider);
                N /= divider;
                continue;
            }
            divider += 1;
        }

        if (N != 0 && N != 1 && isSimpleFast(N))
            resultList.add(N);

        //output
        Collections.sort(resultList);
        for (int i = 0; i < resultList.size(); i++) {
            System.out.print(resultList.get(i) + " ");
        }
    }

    private static boolean isSimpleFast(int number) {
        if (number == 1)
            return true;
        int i = 2;

        while (i * i <= number) {
            if (number % i == 0)
                return false;
            i += 1;
        }
        return true;
    }

    ///////////////////Степень четырёх///////////////////
    public static void powerOfFour() throws IOException {
        //input
        final int N;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine().strip());
        }

        //program and output
        if (N == 1 || N == 4) {
            System.out.println("True");
            return;
        }

        int base = 4;
        int temp = N;

        while (temp >= 1) {
            base *= 4;
            if (base == N) {
                System.out.println("True");
                return;
            }
            temp -= 1;
        }
        System.out.println("False");
    }

    public static void binarySystem() throws IOException {
        //input
        String firstLine;
        String secondLine;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            firstLine = reader.readLine().strip();
            secondLine = reader.readLine().strip();
        }

        //program
        StringBuilder result = new StringBuilder();

        StringBuilder max;
        StringBuilder min;
        int remember = 0;

        if (firstLine.length() != secondLine.length()) {
            max = new StringBuilder(firstLine.length() > secondLine.length() ? firstLine : secondLine);
            min = new StringBuilder(firstLine.length() < secondLine.length() ? firstLine : secondLine);
            int difference = max.length() - min.length();

            for (int i = 0; i < difference; i++) {
                min.insert(0,0);
            }
        } else {
            max = new StringBuilder(firstLine);
            min = new StringBuilder(secondLine);
        }

        ///////////////////Двоичная система///////////////////
        for (int i = max.length() - 1; i >= 0; i--) {
            if (max.charAt(i) == '0' && min.charAt(i) == '0') {
                if (remember > 0) {
                    result.append(1);
                    remember -= 1;
                } else
                    result.append(0);
            }
            else if (max.charAt(i) == '1' && min.charAt(i) == '0') {
                if (remember > 0)
                    result.append(0);
                else
                    result.append(1);
            }
            else if (max.charAt(i) == '0' && min.charAt(i) == '1') {
                if (remember > 0)
                    result.append(0);
                else
                    result.append(1);
            }
            else if (max.charAt(i) == '1' && min.charAt(i) == '1') {
                if (remember > 0) {
                    result.append(1);
                } else {
                    result.append(0);
                    remember += 1;
                }
            }
        }
        for (int i = 0; i < remember; i++) {
            result.append(1);
        }

        //output
        System.out.print(result.reverse().toString());
    }

    ///////////////////Работа из дома///////////////////
    public static void workFromHome() throws IOException {
        //input
        int decimal;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            decimal = Integer.parseInt(reader.readLine().strip());
        }

        //program
        if (decimal == 0) {
            System.out.print(0);
            return;
        }

        StringBuilder binary = new StringBuilder();

        while (decimal != 0) {
            if (decimal % 2 == 0)
                binary.append(0);
            else
                binary.append(1);
            decimal /= 2;
        }

        //output
        System.out.print(binary.reverse().toString());
    }

    ///////////////////Палиндром///////////////////
    public static void palindrome() throws IOException {
        //input
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine().strip();
        }

        //program and output
        int start = 0;
        int end = line.length() - 1;

        while (start < end) {
            if (!isLetterASCII(line.charAt(start)))
                start += 1;
            else if (!isLetterASCII(line.charAt(end)))
                end -= 1;
            else {
                if (line.charAt(start) == line.charAt(end)) {
                    start += 1;
                    end -= 1;
                    continue;
                }
                int one = line.charAt(start) - line.charAt(end);
                int two = line.charAt(end) - line.charAt(start);
                if (one == 32 || one == -32 || two == 32 || two == -32) {
                    start += 1;
                    end -= 1;
                } else {
                    System.out.println("False");
                    return;
                }
            }
        }
        System.out.print("True");
    }

    public static boolean isLetterASCII(char c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

    ///////////////////Самое длинное слово///////////////////
    public static void longestWord() throws IOException {
        //input
        int lenText;
        ArrayList<String> words;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            lenText = Integer.parseInt(reader.readLine().strip());
            words = new ArrayList<>(Arrays.asList(reader.readLine().strip().split(" ")));
        }

        //program
        String resultWord = null;
        int resultLen = 0;
        for (int i = words.size() - 1; i >= 0; i--) {
            int len = words.get(i).length();
            if (resultLen <= len) {
                resultWord = words.get(i);
                resultLen = len;
            }
        }

        //output
        System.out.println(resultWord);
        System.out.print(resultLen);
    }

    ///////////////////Хаотичность погоды///////////////////
    public static void randomWeather() throws IOException{
        //input
        int n;
        int[] days;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            days = new int[n];
            for (int i = 0; i < n; i++) {
                days[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        //program
        if (n == 1) {
            System.out.println(1);
            return;
        }

        int randomDays = 0;

        if (days[0] > days[1])
            randomDays += 1;
        if (days[days.length - 1] > days[days.length - 2])
            randomDays += 1;

        for (int i = 1; i < days.length - 1; i++) {
            if (days[i] > days[i + 1] && days[i] > days[i - 1])
                randomDays += 1;
        }

        //output
        System.out.print(randomDays);
    }

    ///////////////////Соседи///////////////////
    public static void neighbours() throws IOException {
        //input
        int vertical;
        int horizontal;
        int[][] matrix;
        int y;
        int x;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            vertical = Integer.parseInt(reader.readLine().strip());
            horizontal = Integer.parseInt(reader.readLine().strip());

            matrix = new int[vertical][horizontal];
            for (int i = 0; i < vertical; i++) {
                List<Integer> integers = Arrays.asList(reader.readLine().strip().split(" "))
                        .stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                for (int j = 0; j < horizontal; j++) {
                    matrix[i][j] = integers.get(j);
                }
            }

            y = Integer.parseInt(reader.readLine().strip());
            x = Integer.parseInt(reader.readLine().strip());
        }

        //program
        List<Integer> listResult = new ArrayList<>();

        try {
            listResult.add(matrix[y - 1][x]);
        } catch (ArrayIndexOutOfBoundsException e) {}
        try {
            listResult.add(matrix[y + 1][x]);
        } catch (ArrayIndexOutOfBoundsException e) {}
        try {
            listResult.add(matrix[y][x - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {}
        try {
            listResult.add(matrix[y][x + 1]);
        } catch (ArrayIndexOutOfBoundsException e) {}

        //output
        Collections.sort(listResult);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < listResult.size(); i++) {
            stringBuilder.append(listResult.get(i) + " ");
        }
        System.out.println(stringBuilder.toString());
    }

    ///////////////////Чётные и нечётные числа///////////////////
    public static void evenAndOddNumbers() throws IOException {
        //input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int a = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());

        //program and output
        if (a % 2 == 0 && b % 2 == 0 && c % 2 == 0)
            System.out.println("WIN");
        else if (a % 2 != 0 && b % 2 != 0 && c % 2 != 0)
            System.out.println("WIN");
        else
            System.out.println("FAIL");
    }

    ///////////////////Значения функции///////////////////
    public static void functionValues() throws IOException {
        //input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int a = Integer.parseInt(tokenizer.nextToken());
        int x = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());

        //program and output
        System.out.println(a * (x * x) + (b * x) + c);
    }

    ///////////////////Две фишки 2///////////////////
    public static void twoChipsFast() throws Exception {
        //input
        List<Integer> chips;
        int len;
        int X;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            len = Integer.parseInt(reader.readLine().strip());
            chips = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            X = Integer.parseInt(reader.readLine().strip());
        }

        //program
        Map<Integer, Integer> previous = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < chips.size(); i++) {
            int key = X - chips.get(i);

            if (previous.containsKey(key)) {
                result[0] = chips.get(i);
                result[1] = chips.get(previous.get(key));
                break;
            }
            else
                previous.put(chips.get(i), i);
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        if (result[0] == 0 && result[1] == 0) {
            writer.write("None");
            writer.flush();
            return;
        }

        for (int i = 0; i < result.length; i++) {
            writer.write(String.valueOf(result[i]));
            writer.write(" ");
        }
        writer.flush();
    }

    ///////////////////Две фишки///////////////////
    public static void twoChips() throws Exception {
        //input
        List<Integer> chips;
        int len;
        int X;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            len = Integer.parseInt(reader.readLine().strip());
            chips = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            X = Integer.parseInt(reader.readLine().strip());
        }

        //program
        int [] result = new int[2];

        for (int i = 0; i < chips.size(); i++) {
            int value = chips.get(i);

            for (int j = i + 1; j < chips.size(); j++) {
                if (value + chips.get(j) == X) {
                    result[0] = value;
                    result[1] = chips.get(j);
                }
            }
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        if (result[0] == 0 && result[1] == 0) {
            writer.write("None");
            writer.flush();
            return;
        }

        for (int i = 0; i < result.length; i++) {
            writer.write(String.valueOf(result[i]));
            writer.write(" ");
        }
        writer.flush();
    }

    ///////////////////Скользящее среднее///////////////////
    public static void movingAverageFastTask() throws IOException {
        //input
        List<Integer> times;
        int len;
        int K;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            len = Integer.parseInt(reader.readLine().strip());
            times = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            K = Integer.parseInt(reader.readLine().strip());
        }

        //program
        float[] result = new float[times.size() - (K - 1)];
        float currentSum = 0;

        for (int i = 0; i < K; i++) {
            currentSum += times.get(i);
        }
        result[0] = currentSum / K;

        for (int i = 0; i < times.size() - K; i++) {
            currentSum -= times.get(i);
            currentSum += times.get(K + i);
            result[i + 1] = currentSum / K;
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < result.length; i++) {
            writer.write(String.valueOf(result[i]));
            writer.write(" ");
        }
        writer.flush();
    }

    ///////////////////Застёжка-молния///////////////////
    public static void zipper() throws IOException, RuntimeException {
        //input
        List<Integer> arr1;
        List<Integer> arr2;
        int n;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            arr1 = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            arr2 = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }

        //program
        List<Integer> result = new ArrayList<>();
        int lenResultArr = n * 2;
        int mainIndex = 0;

        for (int i = 0; i < lenResultArr; i++) {
            if (i % 2 == 0) {
                result.add(arr1.get(mainIndex));
            } else {
                result.add(arr2.get(mainIndex));
                ++mainIndex;
            }
        }

        //output
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i : result) {
            writer.write(String.valueOf(i));
            writer.write(" ");
        }
        writer.flush();
    }
}
