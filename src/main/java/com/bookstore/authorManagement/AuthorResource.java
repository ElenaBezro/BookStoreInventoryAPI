package com.bookstore.authorManagement;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/authors")
public class AuthorResource {
    public static AuthorDao authorDao = new AuthorDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Author getAuthorById(@PathParam("id") int id) {
        try {
            //TODO return status OK.
            return authorDao.getAuthorById(id);
        } catch (Exception e) {
            //TODO return status BAD_REQUEST.
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAuthor(Author author) {
        boolean isAdded = authorDao.addAuthor(author);
        if(isAdded) {
            return Response.status(Response.Status.CREATED)
                    .build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        boolean isUpdated = authorDao.updateAuthor(id, updatedAuthor);
        if(isUpdated) {
            return Response.status(Response.Status.CREATED)
                    .build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAuthor(@PathParam("id") int id) {

        boolean isDeleted = false;
        try {
            isDeleted = authorDao.deleteAuthor(id);
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        if(isDeleted) {
            return Response.status(Response.Status.CREATED)
                    .entity("Deleted")
                    .build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Bad request")
                    .build();
        }
    }

}


