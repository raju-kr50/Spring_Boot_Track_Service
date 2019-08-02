package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TrackController {
    private TrackService trackService;

    // Auto-wiring parameterized constructor
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    // Post mapping method to save a track
    @PostMapping("track")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            Track savedTrack =  trackService.saveTrack(track);
            responseEntity = new ResponseEntity<Track>(savedTrack, HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException te) {
            responseEntity = new ResponseEntity<String>(te.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    // Get mapping method to get a track by id
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable("id") int id) {
        ResponseEntity responseEntity;
        try {
            Track track= trackService.getTrackById(id);
            responseEntity= new ResponseEntity<Track>(track, HttpStatus.OK);
        } catch (TrackNotFoundException te) {
            responseEntity = new ResponseEntity<String>(te.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    // Get mapping method to retrieve all tracks
    @GetMapping("tracks")
    public ResponseEntity<?>getAllTracks() {
        ResponseEntity responseEntity;
        List<Track> showAllTracks;
        try {
            showAllTracks = trackService.getAllTracks();
            responseEntity= new ResponseEntity<List<Track>>(showAllTracks,HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    // Delete mapping method to delete a track by id
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrackById(id);
            responseEntity = new ResponseEntity("Deleted track successfully", HttpStatus.CREATED);
        } catch (TrackNotFoundException te) {
            responseEntity = new ResponseEntity(te.getMessage(), HttpStatus.OK);
        }

        return responseEntity;
    }

    // Patch mapping method to update a track by id
    @PatchMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@RequestBody Track track, @PathVariable("id") int id) {
        Track updatedTrack = trackService.updateTrackById(track, id);
        return new ResponseEntity<>(updatedTrack, HttpStatus.OK);
    }

    // Get mapping method to get track by name
    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable("name") String name) {
        ResponseEntity responseEntity;
        try {
            List<Track> retrievedTrack = trackService.getTrackByName(name);
            return new ResponseEntity<List<Track>>(retrievedTrack, HttpStatus.OK);
        } catch (TrackNotFoundException te) {
            responseEntity = new ResponseEntity<String>(te.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
