package lab4;

import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

/**
 * This class associates to a value a non-negative number representing the
 * priority. The smaller the number, the higher the priority.
 *
 * @param <T> the type of the values to which to assign priorities
 */
public class Preferences<T> {
    private SortedMap<Integer, Set<T>> prioritisedValues;

    public Preferences() {
        prioritisedValues = new TreeMap<>();
    }

    public boolean contains(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        for (var set : prioritisedValues.values()) {
            if (set.contains(element)) {
                return true;
            }
        }
        return false;
    }

    public Set<Integer> getPriorities() {
        return prioritisedValues.keySet();
    }

    public List<T> getAll() {
        return prioritisedValues.values().stream().flatMap(Collection<T>::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int getPriorityOf(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        for (var priority : prioritisedValues.keySet()) {
            if (prioritisedValues.get(priority).contains(element)) {
                return priority;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return prioritisedValues.isEmpty();
    }

    public int getHighestPriority() {
        if (prioritisedValues.isEmpty()) {
            throw new IllegalArgumentException("Preferences cannot be empty");
        }
        return prioritisedValues.firstKey();
    }

    public void add(T element, int priority) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (priority < 0) {
            throw new IllegalArgumentException("Priority cannot be negative");
        }
        if (!prioritisedValues.containsKey(priority)) {
            prioritisedValues.put(priority, new HashSet<>());
        }
        prioritisedValues.get(priority).add(element);
    }

    @Override
    public String toString() {
        return "Preferences [" + prioritisedValues + "]";
    }
}
