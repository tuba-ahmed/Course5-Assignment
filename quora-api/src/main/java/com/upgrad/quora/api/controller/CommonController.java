package com.upgrad.quora.api.controller;


import com.upgrad.quora.api.model.UserDetailsResponse;
import com.upgrad.quora.service.business.UserBusinessService;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 This endpoint is used to get the details of any user
  Also this endpoint can be accessed by any user in the application.

  @return all the details of the user from the database in the JSON response with the corresponding HTTP status.
 */
@RestController
@RequestMapping("/")
public class CommonController {

    @Autowired
    private UserBusinessService userBusinessService;

    @RequestMapping(method = RequestMethod.GET, path = "/userprofile/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDetailsResponse>getUser(@PathVariable("userId") final String userId , @RequestHeader("authorization") final String authorization) throws AuthorizationFailedException, UserNotFoundException {

        final UserEntity user = userBusinessService.getUser(userId ,authorization);

        // mapping all the user details.

        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
        userDetailsResponse.firstName(user.getFirstName());
        userDetailsResponse.lastName(user.getLastName());
        userDetailsResponse.userName(user.getUserName());
        userDetailsResponse.emailAddress(user.getEmail());
        userDetailsResponse.country(user.getCountry());
        userDetailsResponse.aboutMe(user.getAboutMe());
        userDetailsResponse.dob(user.getDob());
        userDetailsResponse.contactNumber(user.getContactNumber());

        /*
         *  Returning response with all the details of the user from the database
         *  in the JSON response with the corresponding HTTP status.
         */
        return new ResponseEntity<UserDetailsResponse>(userDetailsResponse, HttpStatus.OK);


    }
}
