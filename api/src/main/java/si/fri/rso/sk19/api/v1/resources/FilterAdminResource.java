package si.fri.rso.sk19.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.jetty.server.Authentication;
import si.fri.rso.sk19.models.entities.User;
import si.fri.rso.sk19.services.beans.AdminBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("filter")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS", exposedHeaders = "X-Total-Count, Content-Disposition, X-Export-Filename, Origin, X-Requested-With, Content-Type, Accept, Authorization, Access-Control-Allow-Origin")
public class FilterAdminResource {

    @Inject
    private AdminBean adminBean;

    @GET
    @Path("{username}")
    public Response getUserByUsername(@PathParam("username") String username){
        List<User> users = adminBean.getAllUsers();
        List<User> filtered = new ArrayList<>();
        for(User user : users){
            System.out.println(user.getUsername());
            if(user.getUsername().equals(username)){
                filtered.add(user);
                return Response.ok(filtered).build();
            }
        }
        return Response.ok(filtered).build();
    }
}
