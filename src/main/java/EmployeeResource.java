import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.JacksonDBCollection;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employees")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private JacksonDBCollection<Employee, String> collection;

    public EmployeeResource(JacksonDBCollection<Employee, String> employees) {
        this.collection = employees;
    }

    @POST
    @Timed
    public Response publishNewEmployee(@Valid Employee employee) {
        collection.insert(employee);
        return Response.noContent().build();
    }
}