import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class IndexResource {
    private JacksonDBCollection<Employee, String> collection;

    public IndexResource(JacksonDBCollection<Employee, String> employees) {
        this.collection = employees;
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public List<Employee> index() {
        DBCursor<Employee> dbCursor = collection.find();
        List<Employee> employees = new ArrayList<>();
        while (dbCursor.hasNext()) {
            Employee employee = dbCursor.next();
            employees.add(employee);
        }
        return employees;
    }
}