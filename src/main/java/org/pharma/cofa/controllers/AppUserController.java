package org.pharma.cofa.controllers;

import org.pharma.cofa.entities.EntityPersonne;
import org.pharma.cofa.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RequestMapping("/api")
@RestController
public class AppUserController {
    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/getUsers")
    public List<EntityPersonne> listUsers(){

        return accountService.listUsers();
    }

    @GetMapping(path="/usersById/{matricule}")
    public EntityPersonne getUser(@PathVariable(name = "matricule") String id){
        return accountService.getUser(id);
    }

    @PostMapping(path = "/Addusers")
    public EntityPersonne saveUser(@RequestBody EntityPersonne appUser){
        return accountService.addUser(appUser);
    }

    @PutMapping(path = "/usersUpdate/{matricule}")
    public EntityPersonne updateUser(@RequestBody EntityPersonne appUser, @PathVariable(name = "matricule") String id){
        return accountService.updateUser(appUser,id);
    }
    @DeleteMapping(path = "/usersDelete/{matricule}")
    void deleteUser(@PathVariable(name="matricule") String cne ){
        accountService.deleteUser(cne);
    }


}
