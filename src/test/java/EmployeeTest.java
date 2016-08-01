import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmployeeTest {

    @Test
    public void testShouldCheckWhetherTheEmployeeDetailsAreSame() {
        Employee employee = new Employee("some title", "some name");

        assertThat(employee.getName(), is("some name"));
    }

}
