import java.util.*;
public class Practical_6_Kruskal {

    static class KruskalEdge implements Comparable<KruskalEdge> {
        int src, dest, weight;

        public KruskalEdge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(KruskalEdge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    public class KruskalMST {

        static int find(int[] parent, int i) {
            if (parent[i] != i)
                parent[i] = find(parent, parent[i]);
            return parent[i];
        }

        static void union(int[] parent, int[] rank, int x, int y) {
            int rootX = find(parent, x);
            int rootY = find(parent, y);
            if (rank[rootX] < rank[rootY])
                parent[rootX] = rootY;
            else if (rank[rootX] > rank[rootY])
                parent[rootY] = rootX;
            else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }

        public static void kruskalMST(int V, List<KruskalEdge> edges) {
            Collections.sort(edges);

            int[] parent = new int[V];
            int[] rank = new int[V];
            for (int i = 0; i < V; i++) parent[i] = i;

            int totalWeight = 0;

            for (KruskalEdge edge : edges) {
                int x = find(parent, edge.src);
                int y = find(parent, edge.dest);

                if (x != y) {
                    totalWeight += edge.weight;
                    union(parent, rank, x, y);
                }
            }

            System.out.println("Total weight of MST (Kruskal): " + totalWeight);
        }

        public static void main(String[] args) {
            int V = 5;
            List<KruskalEdge> edges = new ArrayList<>();
            edges.add(new KruskalEdge(0, 1, 2));
            edges.add(new KruskalEdge(0, 3, 6));
            edges.add(new KruskalEdge(1, 2, 3));
            edges.add(new KruskalEdge(1, 3, 8));
            edges.add(new KruskalEdge(1, 4, 5));
            edges.add(new KruskalEdge(2, 4, 7));

            kruskalMST(V, edges);
        }
    }
    //Kruskal's	O(E log E) + O(E α(V)) ≈ O(E log E)
    //E = Number of edges
    //V = Number of vertices
}
