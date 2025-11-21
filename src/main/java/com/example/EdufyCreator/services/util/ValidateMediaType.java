package com.example.EdufyCreator.services.util;

import com.example.EdufyCreator.models.enums.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//ED-348-SA
public class ValidateMediaType {

    //ED-217-SA
    public static boolean isValidMediaType(String mediaType) {
        try{
            MediaType.valueOf(mediaType);
            return true;
        }catch (IllegalArgumentException | NullPointerException e){
            return false;
        }
    }

    //ED-217-SA
    public static MediaType getMediaType(String mediaType) {
        if(mediaType==null || mediaType.isEmpty() || mediaType.equals(" ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Media type can't be null/empty");
        }

        String input = mediaType.toUpperCase();
        if(input.contains(" ")){
            input = input.trim().replaceAll(" ", "_");
        }

        if(!isValidMediaType(input)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid media type: [" + mediaType + "]");
        }

        return MediaType.valueOf(input);
    }

    //ED-348-SA
    public static MediaType getTypeFromClientCall(String input){
        if(!isValidMediaType(input)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid media type: [" + input + "]");
        }
        return MediaType.valueOf(input);
    }
}
