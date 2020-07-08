package io.quarkus.examples.endpoints;

import io.quarkus.examples.entities.Person;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonEndpoints {

    @GET
    @Path("/getAllPersons")
    public Response getAllPersons() {
        return Response.ok(Person.listAll()).build();
    }

    @GET
    @Path("/getPersonByName")
    public Response getPersonByName(@QueryParam("name") String name) {
        return Response.ok(Person.list("name",name)).build();
    }

    @GET
    @Path("/getPersonById")
    public Response getPersonByName(@QueryParam("id") Long id) {
        return Response.ok(Person.findById(id)).build();
    }

    @GET
    @Path("/getNumberOfPersons")
    public Response getNumberOfPersons() {
        return Response.ok(Person.count()).build();
    }

    @POST
    @Path("/insertPerson")
    @Transactional
    public Response insertPerson(@QueryParam("name") String name, @QueryParam("age") Integer age) {
        Person person = new Person();
        person.name = name;
        person.age = age;
        person.persist();
        return Response.status(Response.Status.CREATED).build();
    }
}
