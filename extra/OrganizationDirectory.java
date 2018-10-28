import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/*
Organization Directory:

A user directory can contain of entities that are either users or groups
(of users or subgroups). Write a method to check whether a user belongs to a
particular given group (by given user name and group name).

(a) Define the data structures to represent a directory.
(b) How do you do it without using recursion?
(c) Write another method to return all the users within a given group.
*/

public class OrganizationDirectory {

  public interface Entity {
    String getName();
  }

  // It is better to make these classes all immutable.
  private static abstract class BaseEntity implements Entity {
    private final String name;

    public BaseEntity(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  public static class User extends BaseEntity {
    public User(String name) {
      super(name);
    }
  }

  public static class Group extends BaseEntity {
    private final List<Entity> entities;

    public Group(String name, List<Entity> entities) {
      super(name);
      this.entities = entities;
    }

    public List<Entity> getEntities() {
      return entities;
    }
  }

  private final List<Entity> topLevelEntities;

  public OrganizationDirectory(List<Entity> topLevelEntities) {
    this.topLevelEntities = topLevelEntities;
  }

  public List<Entity> getTopLevelEntities() {
    return topLevelEntities;
  }

  // Recursive find group.
  public Optional<Group> findGroupByName(String groupName) {
    return findGroupByName(groupName, topLevelEntities);
  }

  private Optional<Group> findGroupByName(
      String groupName, List<Entity> entities) {
    for (Entity entity: entities) {
      if (entity instanceof Group) {
        Group group = (Group) entity;
        if (Objects.equals(group.getName(), groupName)) {
          // Found group.
          return Optional.of(group);
        }
        Optional<Group> optionalGroup =
            findGroupByName(groupName, group.getEntities());
        if (optionalGroup.isPresent()) {
          return optionalGroup;
        }
      }
    }
    return Optional.empty();
  }

  // Non-recursive find group.
  public Optional<Group> findGroupByNameNoRecurse(String groupName) {
    LinkedList<Entity> entities = new LinkedList<>(topLevelEntities);

    // Find group.
    while (!entities.isEmpty()) {
      Entity entity = entities.removeFirst();
      if (entity instanceof Group) {
        Group group = (Group) entity;
        if (Objects.equals(group.getName(), groupName)) {
          // Found group.
          return Optional.of(group);
        }
        entities.addAll(group.getEntities());
      }
    }
    return Optional.empty();
  }

  // Recursive solution.
  public boolean userBelongsToGroup(String userName, String groupName) {
    Optional<Group> optionalGroup = findGroupByName(groupName);
    if (optionalGroup.isEmpty()) {
      return false;
    }
    return userBelongsToGroup(userName, optionalGroup.get());
  }

  public boolean userBelongsToGroup(String userName, Group group) {
    // Find user.
    for (Entity entity: group.getEntities()) {
      if (entity instanceof User) {
        // Found user, so check if user name matches.
        User user = (User) entity;
        if (Objects.equals(user.getName(), userName)) {
          return true;
        }
      } else if (entity instanceof Group) {
        // Found child group, so check if user is member of child group.
        Group childGroup = (Group) entity;
        if (userBelongsToGroup(userName, childGroup)) {
          return true;
        }
      }
    }
    return false;
  }

  // Non-recursive solution.
  public boolean userBelongsToGroupNoRecurse(
      String userName, String groupName) {
    Optional<Group> optionalGroup = findGroupByNameNoRecurse(groupName);
    if (optionalGroup.isEmpty()) {
      return false;
    }

    LinkedList<Group> groups = new LinkedList<>();
    groups.add(optionalGroup.get());

    while (!groups.isEmpty()) {
      // removeFirst() -> BFS queue.
      // removeLast() -> DFS stack.
      Group group = groups.removeFirst();

      // Find user.
      for (Entity entity: group.getEntities()) {
        if (entity instanceof User) {
          // Found user, so check if user name matches.
          User user = (User) entity;
          if (Objects.equals(user.getName(), userName)) {
            return true;
          }
        } else if (entity instanceof Group) {
          // Found child group, so check if user is member of child group.
          Group childGroup = (Group) entity;
          groups.add(childGroup);
        }
      }
    }

    return false;
  }

  public static void test(
      OrganizationDirectory dir,
      String userName,
      String groupName,
      boolean expectedResult) {
    boolean actual1 = dir.userBelongsToGroup(userName, groupName);
    boolean actual2 = dir.userBelongsToGroupNoRecurse(userName, groupName);

    if (actual1 != expectedResult) {
      throw new AssertionError(
        "Test 1 failed: User " + userName + " in group " + groupName
        + ": expected = " + expectedResult);
    }

    if (actual2 != expectedResult) {
      throw new AssertionError(
        "Test 2 failed: User " + userName + " in group " + groupName
        + ": expected = " + expectedResult);
    }

    System.out.println(
      "Pass: User " + userName + " in group " + groupName
      + " -> " + expectedResult);
  }

  public static void main(String[] args) {
    OrganizationDirectory dir = new OrganizationDirectory(
      Arrays.<Entity>asList(
        new User("u1"),
        new Group(
          "g1",
          Arrays.<Entity>asList(
            new User("u2"),
            new Group(
              "g2",
              Arrays.<Entity>asList(
                new User("u3"),
                new Group(
                  "g3",
                  Arrays.<Entity>asList())))))));

    test(dir, "u1", "g1", false);
    test(dir, "u2", "g1", true);
    test(dir, "u2", "g2", false);
    test(dir, "u3", "g2", true);
    test(dir, "u2", "g3", false);
    test(dir, "u3", "g3", false);
    test(dir, "u3", "g1", true);
  }
}
