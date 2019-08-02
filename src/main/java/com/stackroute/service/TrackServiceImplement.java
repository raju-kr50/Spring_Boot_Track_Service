package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImplement implements TrackService {

    private TrackRepository trackRepository;

    public TrackServiceImplement() {
    }

    @Autowired
    public TrackServiceImplement(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //Override saveTrack method
    @Override
    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }

    //Override getTrackById method
    @Override
    public Track getTrackById(int id) {
        Track track = trackRepository.findById(id).get();
        return track;
    }

    //Override getAllTracks method
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    //Override deleteTrackById method
    @Override
    public Track deleteTrackById(int id) {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        trackRepository.deleteById(id);
        return optionalTrack.get();
    }

    //Override updateTrackById method
    @Override
    public Track updateTrackById(Track track, int id) {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        trackRepository.deleteById(id);
        return trackRepository.save(track);

    }

    @Override
    public List<Track> getTrackByName(String name) {
        return trackRepository.findByName(name);
    }
}