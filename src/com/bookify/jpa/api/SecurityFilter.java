/**
 * @Authors: Anna, Madeleine, Andreas, Simon, Lucie
 * @version 1.0
 * **/

package com.bookify.jpa.api;


import com.bookify.jpa.models.ApiUsers;
import com.bookify.jpa.repositrories.ApiUsersRepository;
import sun.misc.BASE64Decoder;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Adds a authorization for the api.
 */

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    @Inject
    private ApiUsersRepository aur;
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String SECURED_URL_CONTAINS = "secured";

    //ApiUsers apiUsers = new ApiUsers();

    public Boolean authorized (String hash, String salt, String password) throws Exception {
        Authorization a = new Authorization();

        if (a.generator(password,salt).equals(hash)) {
            return true;
        }
        return false;
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        if (containerRequestContext.getUriInfo().getPath().contains(SECURED_URL_CONTAINS)) {
            List<String> authHeader = containerRequestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

            if (authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                byte[] credDecoded = new BASE64Decoder().decodeBuffer(String.valueOf(authToken));
                String credentials = new String(credDecoded);

                StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();


                ApiUsers aUser = aur.findByApiUserName(username);

                if(!aUser.equals("")){
                    try {
                        if(authorized(aUser.getApiUserPassword(), aUser.getApiUserSalt(), password)) {
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            //if login fails
            Response unautherizedStatus = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Cannot access the resource!")
                    .build();
            containerRequestContext.abortWith(unautherizedStatus);
        }
    }
}
