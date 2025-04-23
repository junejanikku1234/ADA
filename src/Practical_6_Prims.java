import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Practical_6_Prims {

    static class Edge {
        int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public class PrimMST {

        public static void primMST(List<List<Edge>> graph, int V) {
            boolean[] visited = new boolean[V];
            PriorityQueue<Edge[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1].weight));
            pq.offer(new Edge[] { new Edge(0, 0), new Edge(-1, 0) }); // (vertex, parent)

            int totalCost = 0;

            while (!pq.isEmpty()) {
                Edge[] current = pq.poll();
                int u = current[0].dest;

                if (visited[u]) continue;
                visited[u] = true;
                totalCost += current[1].weight;

                for (Edge edge : graph.get(u)) {
                    if (!visited[edge.dest]) {
                        pq.offer(new Edge[] { new Edge(edge.dest, edge.weight), new Edge(u, edge.weight) });
                    }
                }
            }

            System.out.println("Total weight of MST (Prim): " + totalCost);
        }

        public static void main(String[] args) {
            int V = 5;
            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i < V; i++)
                graph.add(new ArrayList<>());

            // Add undirected edges
            graph.get(0).add(new Edge(1, 2));
            graph.get(1).add(new Edge(0, 2));

            graph.get(0).add(new Edge(3, 6));
            graph.get(3).add(new Edge(0, 6));

            graph.get(1).add(new Edge(2, 3));
            graph.get(2).add(new Edge(1, 3));

            graph.get(1).add(new Edge(3, 8));
            graph.get(3).add(new Edge(1, 8));

            graph.get(1).add(new Edge(4, 5));
            graph.get(4).add(new Edge(1, 5));

            graph.get(2).add(new Edge(4, 7));
            graph.get(4).add(new Edge(2, 7));

            primMST(graph, V);
        }
    }
    //Prim's	O(E log V) (with Min Heap)
    //E = Number of edges, V = Number of vertices

}