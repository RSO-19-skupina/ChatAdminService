package si.fri.rso.sk19.services.beans;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.glassfish.jersey.server.ContainerRequest;
import si.fri.rso.sk19.models.entities.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class AdminBean {

    @Inject
    ContainerRequest request;

    @Inject
    @Metric(name="usersDbCall")
    private Counter dbCounter;

    private Client httpClient;
    private String usersUrl;

    @PostConstruct
    private void init(){
        usersUrl = "http://20.73.25.179/api";
        httpClient = ClientBuilder.newClient().register(request);
    }

    private Logger log = Logger.getLogger(AdminBean.class.getName());

    public List<User> getAllUsers(){
        return httpClient
                .target(usersUrl + "/v1/users")
                .request()
                .get(new GenericType<List<User>>(){});
    }

    public boolean deleteUser(Integer user_id){
        Response response = httpClient
                                .target(usersUrl + "/v1/users/" + user_id)
                                .request()
                                .delete();
        if (response.getStatus() == 200){
            return true;
        } else{
            return false;
        }
    }

}
