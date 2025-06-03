package com.springBoot.lms.service;

import com.springBoot.lms.exception.InvalidInputException;
import com.springBoot.lms.model.CModule;
import com.springBoot.lms.model.Video;
import com.springBoot.lms.repository.CModuleRepository;
import com.springBoot.lms.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoService {


    private VideoRepository videoRepository;
    private CModuleRepository cModuleRepository;

    public VideoService(VideoRepository videoRepository, CModuleRepository cModuleRepository) {
        this.videoRepository = videoRepository;
        this.cModuleRepository = cModuleRepository;
    }

    @Transactional
    public void addBatchVideo(List<Video> videoList, int moduleId) {
        CModule cModule = cModuleRepository.findById(moduleId)
                .orElseThrow(() -> new InvalidInputException("ModuleId is not found."));

        if(videoList.isEmpty()){
            throw new InvalidInputException("No data found");
        }

        videoList.parallelStream().forEach(v -> v.setcModule(cModule));

        videoRepository.saveAll(videoList);
    }
}
