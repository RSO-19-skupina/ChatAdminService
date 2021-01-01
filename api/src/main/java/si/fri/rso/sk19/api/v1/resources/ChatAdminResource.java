package si.fri.rso.sk19.api.v1.resources;

import org.eclipse.jetty.server.Authentication;
import si.fri.rso.sk19.models.entities.User;
import si.fri.rso.sk19.services.beans.AdminBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("admin")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatAdminResource {

    @Inject
    private AdminBean adminBean;

    @GET
    public Response getAllUsers(){
        List<User> users = adminBean.getAllUsers();
        System.out.println(users);
        return Response.ok(users).build();
    }

    @DELETE
    @Path("{user_id}")
    public Response deleteUser(@PathParam("user_id") Integer user_id){
        if(adminBean.deleteUser(user_id)){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
