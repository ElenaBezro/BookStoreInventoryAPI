package com.bookstore.bookManagement;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.List;

@Path("/books")
public class BookResource {
    public static BookDao bookDao = new BookDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("id") int id) {
        try {
            //TODO return status OK.
            return bookDao.getBookById(id);
        } catch (Exception e) {
            //TODO return status BAD_REQUEST.
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        boolean isAdded = bookDao.addBook(book);
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
    public Response updateBook(@PathParam("id") int id, Book updatedBook) {
        boolean isUpdated = bookDao.updateBook(id, updatedBook);
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
    public Response deleteBook(@PathParam("id") int id) {

        boolean isDeleted = false;
        try {
            isDeleted = bookDao.deleteBook(id);
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


