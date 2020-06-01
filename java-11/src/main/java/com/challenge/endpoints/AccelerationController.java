package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.entity.User;
import com.challenge.service.impl.AccelerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {
    @Autowired
    private AccelerationService accelerationService;

    @GetMapping("/{id}")
    public Acceleration findById(@PathVariable("id") Long id) {
        return this.accelerationService.findById(id).get();
    }

    @GetMapping
    public List<Acceleration> findAll(@PathParam("companyId") Long companyId) {
        if(companyId != null) {
            return this.accelerationService.findByCompanyId(companyId);
        }
        return null;
    }
}
