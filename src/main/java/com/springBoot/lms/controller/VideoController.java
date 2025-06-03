package com.springBoot.lms.controller;

import com.springBoot.lms.model.Video;
import com.springBoot.lms.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /*
    * @path /api/video/add/{module_id}
    * @method post
    * @param @RequestBody List<Video>
    * @return ResponseEntity
    * */

    @PostMapping("add/{module_id}")
    public ResponseEntity<?> addVideo(@RequestBody List<Video> videoList,
                                      @PathVariable int module_id){

        videoService.addBatchVideo(videoList,module_id);

        return ResponseEntity.status(HttpStatus.OK).body("Video added Successfully!!");
    }
}
