package com.congybk.webapp.controller;

import com.congybk.service.HistoryService;
import com.congybk.service.UserService;
import com.congybk.webapp.model.data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YNC on 04/04/2017.
 */
@RestController
@RequestMapping(value = "/history")
public class RestHistoryController {
    @Autowired
    UserService mUserService;

    @Autowired
    HistoryService mHistoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getListHistoryByUser(@PathVariable int id) {
        System.out.print(mHistoryService.getListHistoryByUser(mUserService.findById(id)).get(0).getTime() + "");
        return new ResponseEntity<>(new data(mHistoryService.getListHistoryByUser(mUserService.findById(id))), HttpStatus.OK);
    }
}
