package com.kpanic.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { Group.class, System.class, ArrayList.class, Student.class } )
public class GroupTest {
    @Mock
    Group group;

    @Test
    public void addOlderThan18 () throws Exception {
        PowerMockito.whenNew(Group.class).withNoArguments().thenReturn(group);
        var anh = new Student("anh.phan3", 26);
        group.addMember18Plus(anh);
        Mockito.verify(group).addMember18Plus(anh);
    }

    @Test
    public void addDuplicated() throws Exception {
        var anh = new Student("anh.phan3", 26);
        var members = PowerMockito.mock(ArrayList.class);
        PowerMockito.when(members.contains(any(Student.class))).thenReturn(false);
        PowerMockito.whenNew(ArrayList.class).withNoArguments().thenReturn(members);

        //PowerMockito.whenNew(Group.class).withNoArguments().thenReturn(group);

        PowerMockito.mockStatic(System.class);

        Group gr = new Group();
        gr.addMember18Plus(anh);

        Mockito.verify(members).contains(anh);
        Mockito.verify(members).add(anh);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        var initMembers = new ArrayList<Student>();
        initMembers.add(new Student("a", 26));
        initMembers.add(new Student("b", 23));
        initMembers.add(new Student("2", 27));
        initMembers.add(new Student("b", 22));
        group = Whitebox.invokeConstructor(Group.class, initMembers);
        var actual = group.getOldest();
        assertThat(actual.getAge(), is(27));
    }

    @Test
    public void testCompare() throws Exception {
        var anh = new Student("a", 26);
        var ban = new Student("b", 32);
        int actual = Whitebox.<Integer>invokeMethod(group, "compare", anh, ban);
        assertThat(actual, is(-1));
    }
}