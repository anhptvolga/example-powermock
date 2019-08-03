package test.com.kpanic.example;

import com.kpanic.example.Group;
import com.kpanic.example.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by anh.phan3.
 * Date: 2019-08-03
 * Time: 12:16
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(Group.class)
public class GroupTest {
    public Group group;

    @Before
    public void before() {
        group = new Group();
    }

    @Test
    public void addOlderThan18 () {
        var anh = new Student("anh.phan3", 26);

        group.addMember18Plus(anh);

        List<Student> members = Whitebox.getInternalState(group, "members");
        assertThat(members.size(), is(1));
        assertThat(members.get(0), is(anh));
    }


    @Test
    public void addDuplicated() {

        var anh = new Student("anh.phan3", 26);

        var initMembers = new ArrayList<Student>();
        initMembers.add(anh);
        Whitebox.setInternalState(group, "members", initMembers);

        group.addMember18Plus(anh);

        List<Student> members = Whitebox.getInternalState(group, "members");
        assertThat(members.size(), is(1));
        assertThat(members.get(0), is(anh));
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