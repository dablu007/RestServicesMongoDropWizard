import com.mongodb.DB;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import net.vz.mongodb.jackson.JacksonDBCollection;

public class EmployeeService extends Service<EmployeeConfiguration> {

    public static void main(String[] args) throws Exception {
        new EmployeeService().run(new String[] { "server" });
    }

    @Override
    public void initialize(Bootstrap<EmployeeConfiguration> bootstrap) {
        bootstrap.setName("employees");
    }

    @Override
    public void run(EmployeeConfiguration configuration, Environment environment) throws Exception {
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
        MongoManager mongoManaged = new MongoManager(mongo);
        environment.manage(mongoManaged);

        environment.addHealthCheck(new MongoHealthCheck(mongo));

        DB db = mongo.getDB(configuration.mongodb);
        JacksonDBCollection<Employee, String> employees = JacksonDBCollection.wrap(db.getCollection("employees"), Employee.class, String.class);

        environment.addResource(new IndexResource(employees));

        environment.addResource(new EmployeeResource(employees));
    }

}