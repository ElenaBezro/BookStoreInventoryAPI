package com.bookstore.userManagement;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserResource {
    private static UserDao userDao = new UserDao();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(AddUserDTO user) {
        boolean isAdded = userDao.registerUser(user);
        if (isAdded) {
            return Response.status(Response.Status.CREATED)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //TODO: return User data
    //@Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(GetUserLoginPasswordDTO loginPassword) {
        try {
            boolean isLoggedIn = userDao.loginUser(loginPassword);
            if (isLoggedIn) {
                return Response.status(Response.Status.OK)
                        .build();
            }
            else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging purposes
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
