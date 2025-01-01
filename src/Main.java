import java.util.*;

public class Main {

    // Функция для нахождения кратчайших путей от стартовой вершины до всех других
    public static Map<String, Integer> bfsShortestPaths(Map<String, List<String>> graph, String start) {
        // Инициализация: расстояние до всех вершин бесконечность
        Map<String, Integer> distances = new HashMap<>();
        for (String vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);  // Изначально все расстояния бесконечность
        }
        distances.put(start, 0);  // Стартовая вершина имеет расстояние 0

        // Очередь для обхода вершин
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);  // Добавляем стартовую вершину в очередь

        while (!queue.isEmpty()) {
            String vertex = queue.poll();  // Извлекаем вершину из очереди
            int currentDistance = distances.get(vertex);  // Получаем расстояние до текущей вершины

            // Для каждого соседа текущей вершины
            for (String neighbor : graph.get(vertex)) {
                if (distances.get(neighbor) == Integer.MAX_VALUE) {  // Если сосед ещё не посещён
                    distances.put(neighbor, currentDistance + 1);  // Обновляем расстояние
                    queue.offer(neighbor);  // Добавляем соседа в очередь для дальнейшей обработки
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        // Пример графа
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D", "E"));
        graph.put("C", Arrays.asList("A", "F"));
        graph.put("D", Arrays.asList("B"));
        graph.put("E", Arrays.asList("B", "F"));
        graph.put("F", Arrays.asList("C", "E"));

        // Тестирование алгоритма
        String startVertex = "A";
        Map<String, Integer> distances = bfsShortestPaths(graph, startVertex);
        System.out.println("Расстояния от вершины " + startVertex + ": " + distances);
    }
}
