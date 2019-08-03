package com.kpanic.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh.phan3.
 * Date: 2019-08-03
 * Time: 12:12
 */

public class Group {
    private final List<Student> members;

    public Group() {
        members = new ArrayList<>();
    }

    private Group(List<Student> members) {
        this.members = members;
    }

    public void addMember18Plus(Student student) {
        if (student.getAge() >= 18 && !members.contains(student)) {
            members.add(student);
        }
    }

    public Student getOldest() {
        return members.stream().max(this::compare).get();
    }

    private int compare(Student a, Student b) {
        if (a.getAge() == b.getAge())
            return 0;
        if (a.getAge() < b.getAge())
            return -1;
        return 1;
    }
}
