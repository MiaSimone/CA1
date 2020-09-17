/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.JokeFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Rasmus
 */
@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final JokeFacade FACADE =  JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   
    
    @Path("/populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populateDBJokes();
        return "{\"msg\":\"6 rows added\"}";
    }
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getJokeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMembers() {
        return GSON.toJson(FACADE.getAllJokes());
    }
    
    @Path("id/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeById(@PathParam("id") int id) {
        return GSON.toJson(FACADE.getJokeById(id));
    }
    
    @Path("random")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandomJoke() {
        return GSON.toJson(FACADE.getRandomJoke());
    }
    
    
    
    
}
