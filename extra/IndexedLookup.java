/*
Indexed Lookup:
We need to create an efficient data lookup table with unique id (integer), record type (non-unique string), record region (non-unique string), and other data where we can efficiently lookup a piece of data by any combination of id, record type and record region. Write the data retrieval method for it.

The data retrieval method would look something like this:
List<Record> lookupRecords(Set<Integer> ids, Set<String> types, Set<String> regions)

The lookup with return the intersection of all records within each category. For example, if there are 10 records matching the types, and 20 records matching the regions, the lookup will return the intersection of records between these 10 and 20 records.

(a) Write the data insert method for it.
(b) Describe how you would write a range retrieval method that takes a range (from value, to value) of each of id, type, and region.
(c) Describe how you would improve the runtime performance of your algorithm (if any).
(d) Describe how you would implement the same lookup to support a dynamic number of unique and non-unique indexes.
*/

import java.util.*;
import java.util.stream.*;

public class IndexedLookup {

  // No need for interview candidate to write out this class.
  public static class Record {
    private final int id; // unique
    private final String type; // non-unique
    private final String region; // non-unique

    public Record(int id, String type, String region) {
      this.id = id;
      this.type = type;
      this.region = region;
    }

    public int getId() {
      return id;
    }

    public String getType() {
      return type;
    }

    public String getRegion() {
      return region;
    }
  }

  private final Map<Integer, Record> recordsById = new HashMap<>();
  private final Map<String, Map<Integer, Record>> recordsByType = new HashMap<>();
  private final Map<String, Map<Integer, Record>> recordsByRegion = new HashMap<>();

  // This method is not meaningfully difficult, so make this optional.
  public boolean addOrReplace(Record record) {
    Record oldRecord = recordsById.get(record.getId());
    recordsById.put(record.getId(), record);

    Map<Integer, Record> mapForType = recordsByType.get(record.getType());
    if (mapForType == null) {
      mapForType = new HashMap<Integer, Record>();
      recordsByType.put(record.getType(), mapForType);      
    }
    mapForType.put(record.getId(), record);

    Map<Integer, Record> mapForRegion = recordsByRegion.get(record.getType());
    if (mapForRegion == null) {
      mapForRegion = new HashMap<Integer, Record>();
      recordsByRegion.put(record.getRegion(), mapForRegion);      
    }
    mapForRegion.put(record.getId(), record);

    return oldRecord != null;
  }

  // This method is not meaningfully difficult, so make this optional.
  private static void intersect(
      Map<Integer, Record> map1,
      Map<Integer, Record> map2) {
    Set<Integer> ids = new HashSet<>(map1.keySet());

    for (Integer id : ids) {
      if (!map2.containsKey(id)) {
        map1.remove(id);
      }
    }
  }

  public List<Record> lookupRecords(Set<Integer> ids, Set<String> types, Set<String> regions) {
    // If there is no lookup criteria, then return everything.
    if (ids.isEmpty() && types.isEmpty() && regions.isEmpty()) {
      return new ArrayList<>(recordsById.values());
    }

    LinkedList<Map<Integer, Record>> recordsToIntersect = new LinkedList<>();

    if (!ids.isEmpty()) {
      Map<Integer, Record> matchingRecords = new HashMap<>();

      for (Integer id : ids) {        
        Record record = recordsById.get(id);
        if (record != null) {
          matchingRecords.put(id, record);
        }
      }

      if (matchingRecords.isEmpty()) {
        return Arrays.asList();
      }

      recordsToIntersect.add(matchingRecords);
    }

    if (!types.isEmpty()) {
      Map<Integer, Record> matchingRecords = new HashMap<>();
      
      for (String type : types) {
        Map<Integer, Record> records = recordsByType.get(type);
        if (records != null) {
          matchingRecords.putAll(records);
        }
      }

      if (matchingRecords.isEmpty()) {
        return Arrays.asList();
      }
      
      recordsToIntersect.add(matchingRecords);
    }

    if (!regions.isEmpty()) {
      Map<Integer, Record> matchingRecords = new HashMap<>();
      
      for (String region : regions) {
        Map<Integer, Record> records = recordsByRegion.get(region);
        if (records != null) {
          matchingRecords.putAll(records);
        }
      }

      if (matchingRecords.isEmpty()) {
        return Arrays.asList();
      }
      
      recordsToIntersect.add(matchingRecords);
    }

    // Reduce all the maps using intersection.
    while (recordsToIntersect.size() > 1) {
      Map<Integer, Record> map1 = recordsToIntersect.removeLast();
      Map<Integer, Record> map2 = recordsToIntersect.getLast();
      intersect(map2, map1);
    }

    return new ArrayList<>(recordsToIntersect.getFirst().values());
  }

  private static void test(
      List<Record> records,
      Set<Integer> ids,
      Set<String> types,
      Set<String> regions,
      List<Integer> expected) {
    IndexedLookup indexedLookup = new IndexedLookup();
    records.forEach(indexedLookup::addOrReplace);

    List<Record> actual = indexedLookup.lookupRecords(ids, types, regions);

    String actualString = actual.stream().map(Record::getId)
        .collect(Collectors.toList()).toString();

    String expectedString = expected.toString();

    System.out.println("Expect = " + expectedString);
    System.out.println("Actual = " + actualString);

    System.out.println(
        actualString.equals(expectedString) ? "Correct!" : "Wrong!");

    System.out.println();
  }

  public static void main(String[] args) {
    test(Arrays.asList(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
        Arrays.asList());

    test(Arrays.asList(new Record(1, "t1", "r1")), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), Arrays.asList(1));

    test(Arrays.asList(new Record(1, "t1", "r1"), new Record(2, "t2", "r2")),
        new HashSet<>(Arrays.asList(1)), new HashSet<>(), new HashSet<>(),
        Arrays.asList(1));

    test(Arrays.asList(new Record(1, "t1", "r1"), new Record(2, "t2", "r2"),
        new Record(3, "t2", "r2")),
        new HashSet<>(Arrays.asList(1, 2, 3, 4)),
        new HashSet<>(Arrays.asList("t2")),
        new HashSet<>(),
        Arrays.asList(2, 3));

    test(Arrays.asList(new Record(1, "t1", "r1"), new Record(2, "t2", "r2"),
        new Record(3, "t2", "r2")),
        new HashSet<>(Arrays.asList()),
        new HashSet<>(Arrays.asList("t2")),
        new HashSet<>(),
        Arrays.asList(2, 3));

    test(Arrays.asList(new Record(1, "t1", "r1"), new Record(2, "t2", "r2"),
        new Record(3, "t2", "r2"), new Record(4, "t3", "r3")),
        new HashSet<>(Arrays.asList(1, 3, 4)),
        new HashSet<>(Arrays.asList("t2")),
        new HashSet<>(Arrays.asList("r3")),
        Arrays.asList());
  }
}
