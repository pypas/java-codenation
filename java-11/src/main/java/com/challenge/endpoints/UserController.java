package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return this.userService.findById(id).get();
    }

    @GetMapping
    public List<User> findAll(@PathParam("accelerationName") String accelerationName, @PathParam("companyId") Long companyId) {
        if(accelerationName != null) {
            return this.userService.findByAccelerationName(accelerationName);
        }
        if(companyId != null) {
            return this.userService.findByCompanyId(companyId);
        }
        return null;
    }


}
