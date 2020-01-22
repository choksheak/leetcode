import java.util.*;

/*
Given a list of itinerary 3-tuple events consisting of start time, end time and interest points,
return the maximum number of interest points that could be obtained by combining the events
such that there are no conflicting/overlapping events.
*/
public class Main {

    public static void main(String[] args) {
        System.out.println("Case 1 (expect 10) = " + ItineraryScorer.getMostInterestingItineraryScore(Arrays.asList(
                new Event(0, 2, 4),
                new Event(1, 2, 5),
                new Event(2, 4, 5)
        )));

        System.out.println("Case 2 (expect 20) = " + ItineraryScorer.getMostInterestingItineraryScore(Arrays.asList(
                new Event(2, 5, 5),
                new Event(3, 6, 6),
                new Event(5, 10, 2),
                new Event(4, 10, 8),
                new Event(8, 9, 5),
                new Event(13, 14, 1),
                new Event(13, 17, 5),
                new Event(14, 16, 8)
        )));

        System.out.println("Case 3 (expect 10) = " + ItineraryScorer.getMostInterestingItineraryScore(Arrays.asList(
                new Event(5, 10, 2),
                new Event(1, 11, 10),
                new Event(2, 5, 5),
                new Event(3, 6, 6)
        )));
    }
}

class Event {

    int start;
    int end;
    int interest;
    int index; // set and used by algorithm only: 0 .. (N-1)

    Event(int start, int end, int interest) {
        this.start = start;
        this.end = end;
        this.interest = interest;
    }
}

class ItineraryScorer {

    private final List<Event> events;
    private boolean[][] conflicts;
    private int maxInterest;

    private ItineraryScorer(List<Event> events) {
        this.events = events;
    }

    static int getMostInterestingItineraryScore(List<Event> events) {
        return new ItineraryScorer(events).getMostInterestingItineraryScoreInternal();
    }

    private int getMostInterestingItineraryScoreInternal() {
        prepopulateIndexes(events);

        // Pre-cache the conflict result.
        conflicts = getConflicts(events); // O(N^2)

        // Do combinations.
        maxInterest = 0;

        combinations(new ArrayList<Event>(), 0, 0);

        return maxInterest;
    }

    private static void prepopulateIndexes(List<Event> events) {
        for (int i = 0; i < events.size(); i++) {
            events.get(i).index = i;
        }
    }

    private void combinations(List<Event> tempEvents, int sumInterest, int startIndex) {
        maxInterest = Math.max(sumInterest, maxInterest);

        for (int i = startIndex; i < events.size(); i++) {
            Event e = events.get(i);

            // Check to make sure that there is no conflict between e and tempEvents.
            if (areConflicted(e, tempEvents)) {
                continue;
            }

            tempEvents.add(e);
            combinations(tempEvents, sumInterest + e.interest, i + 1);
            tempEvents.remove(tempEvents.size() - 1); // remove last element
        }
    }

    private static boolean[][] getConflicts(List<Event> events) {
        boolean[][] conflicts = new boolean[events.size()][events.size()];

        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = i + 1; j < events.size(); j++) {
                conflicts[i][j] = conflicts[j][i] = areConflicted(events.get(i), events.get(j));
            }
        }

        return conflicts;
    }

    private static boolean areConflicted(Event e1, Event e2) {
        return (e2.start <= e1.start && e1.start < e2.end)
                || (e2.start < e1.end && e1.end < e2.end)
                || (e1.start <= e2.start && e2.start < e1.end)
                || (e1.start < e2.end && e2.end < e1.end);
    }

    private boolean areConflicted(Event e, List<Event> events) {
        for (Event e2 : events) { // O(N) -- could be O(log N) if we do binary search
            if (conflicts[e.index][e2.index]) {
                return true;
            }
        }
        return false;
    }
}
