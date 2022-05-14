package com.ajuncodes.coursework.controller;

import com.ajuncodes.coursework.model.UsersKotkovets;
import com.ajuncodes.coursework.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
   @Autowired
   private UsersService usersService;

   @PostMapping("/add")
   public boolean add(@RequestBody UsersKotkovets usersKotkovets){
       return usersService.saveUsers(usersKotkovets);
   }

   @PutMapping("/recover")
   public boolean recover(@RequestBody UsersKotkovets usersKotkovets) {
       return usersService.recover(usersKotkovets);
   }

   @PostMapping("/login")
   public Object login(@RequestBody UsersKotkovets usersKotkovets){
       return usersService.login(usersKotkovets);
   }

   @GetMapping("/getAll")
   public List<UsersKotkovets> getAllUsers(){
       return usersService.getAllUsers();
   }

   @DeleteMapping("/delete/{userId}")
   public String deleteUser(@PathVariable Integer userId) {
       usersService.deleteUser(userId);
       return "User deleted";
   }

   @PutMapping("/handler/role/{id}")
    public boolean roleHandler(@PathVariable Integer id) {
       usersService.roleHandler(id);
       return true;
   }

}
