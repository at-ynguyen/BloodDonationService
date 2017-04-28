package com.congybk.controller;

import com.congybk.entity.FindBlood;
import com.congybk.entity.User;
import com.congybk.response.FindBloodBody;
import com.congybk.service.FindBloodService;
import com.congybk.service.StorageService;
import com.congybk.service.UserService;
import com.congybk.utlis.UserUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

/**
 * @Author YNC on 19/04/2017.
 */
@RestController
@RequestMapping(value = "/api/find")
public class ApiFindBloodController {
    @Autowired
    FindBloodService findBloodService;
    @Autowired
    UserService userService;
    @Autowired
    StorageService storageService;

    @GetMapping(value = "/{page}")
    public ResponseEntity<?> getFindBloodByPage(@PathVariable int page) {
        int start = page * 10 - 10;
        int end = 10;
        return new ResponseEntity<Object>(findBloodService.getFindBlood(start, end), HttpStatus.OK);
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<?> getEventById(@PathVariable int id) {
        return new ResponseEntity<Object>(findBloodService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/create/no_image")
    public ResponseEntity<?> createPost(@RequestBody Map<String, String> data) {
        System.out.print("NO IMAGE//////////////////");
        String bloodType = data.get("blood_type");
        String address = data.get("address");
        String postContent = data.get("post_content");
        String postName = data.get("post_name");
        User user = this.userService.findByEmail(UserUtils.getAccountCodeByAuthorization());
        FindBlood fd = new FindBlood(0, user, postName, postContent, bloodType, address, "", false, new Date());
        return new ResponseEntity<Object>(findBloodService.create(fd), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> createPostImage(@RequestParam("body") String data, @RequestParam("file") MultipartFile file) {
        System.out.println(data);
        Gson gson = new Gson();
        FindBloodBody findBloodBody = gson.fromJson(data, FindBloodBody.class);
        User user = this.userService.findByEmail(UserUtils.getAccountCodeByAuthorization());
        String pathFile = "";
        try {
            storageService.store(file);
            pathFile = "/api/find/files/" + file.getOriginalFilename();

        } catch (Exception e) {
            System.out.print(e.fillInStackTrace());
            return new ResponseEntity<Object>("Loi roi", HttpStatus.EXPECTATION_FAILED);
        }
        FindBlood fd = new FindBlood(0, user, findBloodBody.getPostName(),
                findBloodBody.getPostContent(), findBloodBody.getBloodType(),
                findBloodBody.getAddress(), pathFile, false, new Date());
        return new ResponseEntity<Object>(findBloodService.create(fd), HttpStatus.OK);
    }


    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        System.out.println("FILE:" + file.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
