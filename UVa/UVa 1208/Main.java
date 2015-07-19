import java.util.*;
import java.io.*;
import java.lang.Character;

class Main {

    ArrayList<Triple> edgeList = new ArrayList<Triple>();
    ArrayList<Triple> ansList = new ArrayList<Triple>();
    int[] cities;

    void run() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(scanner.readLine());

        for (int i = 1; i <= TC; ++i) {
            edgeList.clear();
            ansList.clear();
            int numCities = Integer.parseInt(scanner.readLine());
            cities = new int[numCities];

            for (int j = 0; j < numCities; ++j) {
                cities[j] = j;
                String[] inputs = scanner.readLine().split("\\s*,\\s*");
                char city = (char) (65 + j);
                for (int k = 0; k < numCities; ++k) {
                    int weight = Integer.parseInt(inputs[k]);
                    if (weight != 0) {
                        edgeList.add(new Triple(city, (char) (65 + k), weight));
                    }
                }
            }

            Collections.sort(edgeList);
            
            int numEdges = edgeList.size();
            for (int j = 0; j < numEdges; j++) {
                Triple edge = edgeList.get(j);
                int u = (int) (edge.u - 'A');
                int v = (int) (edge.v - 'A');
                if (find(u) != find(v)) {
                    ansList.add(edge);
                    cities[find(u)] = find(v);
                }
            }

            Collections.sort(ansList);
            System.out.println("Case " + i + ":");
            for (int j = 0; j < ansList.size(); ++j) {
                Triple edge = ansList.get(j);
                System.out.println(edge.u + "-" + edge.v + " " + edge.weight);
            }
        }
    }

    int find(int x) {
        if (cities[x] == x) return cities[x];

        return cities[x] = find(cities[x]);
    }

    public static void main(String[] args) throws Exception {
        Main program = new Main();
        program.run();
    }

    static class Triple implements Comparable<Triple> {

        char u;
        char v;
        int weight;

        public Triple(char u, char v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    
        public int compareTo(Triple o) {
            if (this.weight != o.weight) {
                return this.weight - o.weight;
            } else if (this.u != o.u) {
                return Character.getNumericValue(this.u) - Character.getNumericValue(o.u);
            } else {
                return Character.getNumericValue(this.v) - Character.getNumericValue(o.v);
            }
        }
    }
}
