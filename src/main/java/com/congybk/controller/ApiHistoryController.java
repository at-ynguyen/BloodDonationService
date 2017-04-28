package com.congybk.controller;

import com.congybk.entity.User;
import com.congybk.service.HistoryService;
import com.congybk.service.UserService;
import com.congybk.utlis.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YNC on 14/04/2017.
 */
@RestController
@RequestMapping(value = "/api/history")
public class ApiHistoryController {
    @Autowired
    HistoryService mHistoryService;

    @Autowired
    UserService mUserService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getListHistoryByUserId(@PathVariable int userId) {
        return new ResponseEntity<Object>(mHistoryService.getListHistoryByUser(mUserService.findById(userId)), HttpStatus.OK);
    }

    @GetMapping(value = "/top")
    public ResponseEntity<?> getTopHistory() {
        return new ResponseEntity<Object>(mHistoryService.getTopHistory(), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/user")
    public ResponseEntity<?> getHistory() {
        User user = mUserService.findByEmail(UserUtils.getAccountCodeByAuthorization());
        return new ResponseEntity<Object>(mHistoryService.getListHistoryByUser(user), HttpStatus.OK);
    }
}
