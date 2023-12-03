package com.bookstore.bookManagement;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/books")
public class BookResource {
    public static BookDao bookDao = new BookDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GetBookDTO> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") int id) {
        try {
            GetBookDTO book = bookDao.getBookById(id);
            if (book != null) {
                return Response.status(Response.Status.CREATED)
                        .entity(book)
                        .build();
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Book with ID " + id + " not found")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error retrieving book")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(BookDTO book) {
        boolean isAdded = bookDao.addBook(book);
        if (isAdded) {
            return Response.status(Response.Status.CREATED)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, BookDTO updatedBook) {
        boolean isUpdated = bookDao.updateBook(id, updatedBook);
        if (isUpdated) {
            return Response.status(Response.Status.CREATED)
                    .build();
        } else {
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
        if (isDeleted) {
            return Response.status(Response.Status.CREATED)
                    .entity("Deleted")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Bad request")
                    .build();
        }
    }

}


