package lab4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    private Map<School, Set<Student>> data;

    public Solution() {
        data = new HashMap<>();
    }

    public void addSchool(School school) {
        if (data.containsKey(school)) {
            throw new IllegalArgumentException("School is already part of the solution");
        }
        data.put(school, new HashSet<>());
    }

    public void addStudentToSchool(School school, Student student) {
        if (!data.containsKey(school)) {
            throw new IllegalArgumentException("School is not part of the solution");
        }
        data.get(school).add(student);
    }
    
    public Set<Student> getStudentsOfSchool(School school) {
        if (!data.containsKey(school)) {
            throw new IllegalArgumentException("School is not part of the solution");
        }
        return data.get(school);
    }

    @Override
    public String toString() {
        return "Solution [" + data + "]";
    }
}
