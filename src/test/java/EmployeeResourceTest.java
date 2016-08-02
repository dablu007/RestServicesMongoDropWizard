import net.vz.mongodb.jackson.JacksonDBCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class EmployeeResourceTest {

    private JacksonDBCollection collection;
    private EmployeeResource employeeResource;

    @Before
    public void setup(){
        collection = Mockito.mock(JacksonDBCollection.class);
        employeeResource = new EmployeeResource(collection);
    }

    @Test
    public void testShouldPublishNewEmployee() throws Exception {
        Employee employee = new Employee("title1","name1");
        employeeResource.publishNewEmployee(employee);

        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(collection).insert(argumentCaptor.capture());
        Employee argEmployee = argumentCaptor.getValue();

        Assert.assertEquals(employee.getId(),argEmployee.getId());
        Assert.assertEquals(employee.getName(),argEmployee.getName());
        Assert.assertEquals(employee.getTitle(),argEmployee.getTitle());
    }

}