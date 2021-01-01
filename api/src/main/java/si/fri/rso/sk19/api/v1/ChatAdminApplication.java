package si.fri.rso.sk19.api.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("v1")
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS", exposedHeaders = "X-Total-Count, Content-Disposition, X-Export-Filename, Origin, X-Requested-With, Content-Type, Accept, Authorization, Access-Control-Allow-Origin")
public class ChatAdminApplication extends Application {
}
