/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import restful.Model.PersonaModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restful.Service.PersonaService;

/**
 *
 * @author Diegos
 */
@Path("personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaResource {
    
    PersonaService servicio = new PersonaService();

    @GET
    public ArrayList<PersonaModel> getPersonas() {

        return servicio.getPersonas();
    }

    @Path("/{identificacion}")
    @GET
    public PersonaModel getPersona(@PathParam("identificacion") int id) {

        return servicio.getPersona(id);
    }

    @POST
    public PersonaModel addPersona(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        PersonaModel persona = gson.fromJson(JSON, PersonaModel.class);
        return servicio.addPersona(persona);
    }

    @DELETE
    @Path("/{identificacion}")
    public String delPersona(@PathParam("identificacion") int id) {

        return servicio.delPersona(id);

    }

    @PUT
    public PersonaModel updatePersona(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        PersonaModel persona = gson.fromJson(JSON, PersonaModel.class);
        return servicio.updatePersona(persona);
    }
}
