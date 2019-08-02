package com.stackroute.seeddata;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ComLineRunner implements CommandLineRunner {

    TrackRepository trackRepository;

    @Autowired
    public ComLineRunner(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Track track = new Track(3,"XfeYZ","commfwents");
        trackRepository.save(track);

    }
}
