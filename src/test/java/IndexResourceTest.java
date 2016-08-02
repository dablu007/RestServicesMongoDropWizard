import com.mongodb.DBCollection;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JacksonDBCollection.class)
public class IndexResourceTest {
    private JacksonDBCollection collection;
    private IndexResource indexResource;
    private DBCursor dbCursor;
    private DBCollection dbCollection;

    @Before
    public void setup(){
        dbCursor = PowerMockito.mock(DBCursor.class);
        collection = PowerMockito.mock(JacksonDBCollection.class);
        PowerMockito.when(collection.find()).thenReturn(dbCursor);
        indexResource = new IndexResource(collection);
    }

    @Test
    public void index() throws Exception {
        Employee employee1 = new Employee("emp1","name1");
        Employee employee2 = new Employee("emp2","name2");

        Mockito.when(dbCursor.hasNext()).thenReturn(true,true,false);
        Mockito.when(dbCursor.next()).thenReturn(employee1, employee2);

        List<Employee> result = indexResource.index();
        Assert.assertEquals(2, result.size());

        Assert.assertEquals(employee1.getId(), result.get(0).getId());
        Assert.assertEquals(employee2.getId(), result.get(1).getId());

        Assert.assertEquals("emp1", result.get(0).getTitle());
        Assert.assertEquals("emp2", result.get(1).getTitle());
    }

}