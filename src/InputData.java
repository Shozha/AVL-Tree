import java.util.*;

public class InputData {

    public static void DoAVLTree () {
        // Генерация уникальных чисел
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = new Random();
        int size = 10000;
        while (uniqueNumbers.size() < size) {
            uniqueNumbers.add(random.nextInt());
        }
        int[] array = uniqueNumbers.stream().mapToInt(i -> i).toArray();

        AVLTree tree = new AVLTree();

        // Добавление элементов
        List<Long> insertTimes = new ArrayList<>();
        List<Integer> insertOperations = new ArrayList<>();
        for (int key : array) {
            long start = System.nanoTime();
            tree.insert(key);
            insertTimes.add(System.nanoTime() - start);
            insertOperations.add(tree.getLastOperationCount());
        }

        // Поиск 100 элементов
        List<Integer> searchKeys = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            searchKeys.add(array[random.nextInt(array.length)]);
        }

        List<Long> searchTimes = new ArrayList<>();
        List<Integer> searchOperations = new ArrayList<>();
        for (int key : searchKeys) {
            long start = System.nanoTime();
            tree.search(key);
            searchTimes.add(System.nanoTime() - start);
            searchOperations.add(tree.getLastOperationCount());
        }

        // Удаление 1000 элементов
        List<Integer> deleteKeys = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);
        for (int i = 0; i < 1000; i++) {
            deleteKeys.add(array[indices.get(i)]);
        }

        List<Long> deleteTimes = new ArrayList<>();
        List<Integer> deleteOperations = new ArrayList<>();
        for (int key : deleteKeys) {
            long start = System.nanoTime();
            tree.delete(key);
            deleteTimes.add(System.nanoTime() - start);
            deleteOperations.add(tree.getLastOperationCount());
        }

        // Вычисление средних значений
        double avgInsertTime = calculateAverage(insertTimes);
        double avgInsertOps = calculateAverageInt(insertOperations);
        double avgSearchTime = calculateAverage(searchTimes);
        double avgSearchOps = calculateAverageInt(searchOperations);
        double avgDeleteTime = calculateAverage(deleteTimes);
        double avgDeleteOps = calculateAverageInt(deleteOperations);

        System.out.println("Среднее время вставки 10000 элементов (нс): " + avgInsertTime);
        System.out.println("Среднее количество операций вставки 10000 элементов: " + avgInsertOps);
        System.out.println("Среднее время поиска 100 элементов (нс): " + avgSearchTime);
        System.out.println("Среднее количество операций поиска 100 элементов: " + avgSearchOps);
        System.out.println("Среднее время удаления 1000 элементов (нс): " + avgDeleteTime);
        System.out.println("Среднее количество операций удаления 1000 элементов: " + avgDeleteOps);
    }

    private static double calculateAverage(List<Long> list) {
        return list.stream().mapToLong(Long::longValue).average().orElse(0);
    }

    private static double calculateAverageInt(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

}
