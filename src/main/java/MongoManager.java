import com.mongodb.Mongo;
import com.yammer.dropwizard.lifecycle.Managed;

public class MongoManager implements Managed {

    private Mongo mongo;

    public MongoManager(Mongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        mongo.close();
    }

}
