package com.stackroute.seeddata;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {

    private Track track;

    TrackRepository trackRepository;

    public AppListener() {
    }

    @Autowired
    public AppListener(Track track, TrackRepository trackRepository) {
        this.track = track;
        this.trackRepository = trackRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent cse) {
        track.setId(1);
        track.setName("Abc");
        track.setComments("this is a comment");
        Track track1 = new Track(2,"XYZ","comments");
        trackRepository.save(track);
        trackRepository.save(track1);
    }
}
