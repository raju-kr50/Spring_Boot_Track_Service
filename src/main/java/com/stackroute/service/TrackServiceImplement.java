package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
//@Primary
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
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getId())) {
            throw  new TrackAlreadyExistsException("Track already exists");
        }
        Track savedTrack = trackRepository.save(track);
        if (savedTrack==null) {
            throw new TrackAlreadyExistsException("Track is null");
        }
        return savedTrack;
    }

    //Override getTrackById method
    @Override
    public Track getTrackById(int id) throws TrackNotFoundException{
        if (trackRepository.existsById(id)) {
            Track track = trackRepository.findById(id).get();
            return track;
        } else {
            throw new TrackNotFoundException("Track not found");
        }
    }

    // Override getTrackByName method
    @Override
    public List<Track> getTrackByName(String name) throws TrackNotFoundException{
        if (trackRepository.findByName(name).isEmpty()) {
            throw new TrackNotFoundException("Track not found");
        }
        return trackRepository.findByName(name);
    }

    //Override getAllTracks method
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    //Override deleteTrackById method
    @Override
    public Track deleteTrackById(int id) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            Optional<Track> optionalTrack = trackRepository.findById(id);
            trackRepository.deleteById(id);
            return optionalTrack.get();
        } else {
            throw new TrackNotFoundException("Track not found");
        }
    }

    //Override updateTrackById method
    @Override
    public Track updateTrackById(Track track, int id) {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        trackRepository.deleteById(id);
        return trackRepository.save(track);

    }
}