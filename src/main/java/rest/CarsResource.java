package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.CarsFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("cars")
public class CarsResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    private static final CarsFacade FACADE =  CarsFacade.getCarsFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("/populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populateDB();
        long count = FACADE.getCarsCount();
        return "{\"msg\":\"" + count + "rows added\"}";
    }
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarsCount() {
        long count = FACADE.getCarsCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCars() {
        return GSON.toJson(FACADE.getAllCars());
    }
    
    
}
