package com.mercadolibre.Controller;

import com.mercadolibre.Model.Person;
import com.mercadolibre.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author david
 */
@RestController
@RequestMapping("/")
public class PersonController {
    
    @Autowired
    PersonService personService;
    
    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<String> isMutant(@RequestBody Person person) {
        if (personService.isMutant(person))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.FORBIDDEN);
    }
    
}