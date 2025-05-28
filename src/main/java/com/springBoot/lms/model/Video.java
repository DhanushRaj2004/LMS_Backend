package com.springBoot.lms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "video_title")
    private String videoTitle;

    @Column(name = "play_time")
    private float playTime;

    @ManyToOne
    private CModule cModule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public float getPlayTime() {
        return playTime;
    }

    public void setPlayTime(float playTime) {
        this.playTime = playTime;
    }

    public CModule getcModule() {
        return cModule;
    }

    public void setcModule(CModule cModule) {
        this.cModule = cModule;
    }
}
