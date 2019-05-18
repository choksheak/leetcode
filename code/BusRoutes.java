import java.util.*;

/**
 * Bus Routes (https://leetcode.com/problems/bus-routes/)
 *
 * Given a list of bus routes (either array of array or list of lists), each routes[i] is a bus
 * route that the i-th bus repeats forever (either in a loop or back-and-forth, the sequence of the
 * stops does not matter). Write a method to return the minimum number of buses one needs to take
 * to travel from the `start' stop to the `end' stop. Return -1 if it is not possible.
 */
class BusRoutes {
    // Maps from stops to buses. Maybe slightly less efficient.
    public static int numBusesToDestination1(int[][] routes, int start, int end) {
        if (start == end) {
            return 0; // no need to take any buses
        }

        // Map from stops to buses.
        Map<Integer, Set<Integer>> stopToBuses = new HashMap<>();
        int numBuses = 0;

        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop : routes[bus]) {
                Set<Integer> buses = stopToBuses.computeIfAbsent(stop, k -> new HashSet<>());
                buses.add(bus);
            }
        }

        if (!stopToBuses.containsKey(start) || !stopToBuses.containsKey(end)) {
            return -1; // start or end stop not found
        }

        Set<Integer> seen = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            numBuses++;
            for (int i = queue.size(); i > 0; i--) {
                int stop = queue.removeFirst();

                for (int bus : stopToBuses.get(stop)) {
                    if (seen.contains(bus)) {
                        continue;
                    }
                    seen.add(bus);

                    for (int stop2 : routes[bus]) {
                        if (stop2 == end) {
                            return numBuses;
                        }
                        queue.addLast(stop2);
                    }
                }
            }
        }

        return -1; // no traceable route
    }

    // Maps from stops to stops. Should be slightly more efficient.
    public static int numBusesToDestination2(int[][] routes, int start, int end) {
        if (start == end) {
            return 0; // no need to take any buses
        }

        // Map from stops to stops.
        Map<Integer, Set<Integer>> stopToStops = new HashMap<>();
        int numBuses = 0;

        for (int[] route : routes) {
            for (int stop : route) {
                Set<Integer> stops = stopToStops.computeIfAbsent(stop, k -> new HashSet<>());
                for (int stop2 : route) {
                    stops.add(stop2);
                }
            }
        }

        if (!stopToStops.containsKey(start) || !stopToStops.containsKey(end)) {
            return -1; // start or end stop not found
        }

        Set<Integer> seen = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            numBuses++;
            for (int i = queue.size(); i > 0; i--) {
                int stop = queue.removeFirst();

                for (int stop2 : stopToStops.get(stop)) {
                    if (seen.contains(stop2)) {
                        continue;
                    }
                    seen.add(stop2);

                    if (stop2 == end) {
                        return numBuses;
                    }
                    queue.addLast(stop2);
                }
            }
        }

        return -1; // no traceable route
    }

    public static void main(String[] args) {
        int[][] routes = {
            {1, 3, 6, 9},
            {2, 6, 10, 13},
            {4, 5, 13, 16},
        };

        System.out.println("1. Num buses = " + numBusesToDestination1(routes, 1, 16));
        System.out.println("2. Num buses = " + numBusesToDestination2(routes, 1, 16));
    }
}
