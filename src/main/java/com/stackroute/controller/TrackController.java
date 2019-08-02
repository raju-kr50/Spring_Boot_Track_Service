package com.stackroute.controller;

import com.stackroute.domain.Track;
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

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        Track savedTrack=trackService.saveTrack(track);
        responseEntity= new ResponseEntity<Track>(savedTrack, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable("id") int id) {
        Track showTrack = trackService.getTrackById(id);
        return new ResponseEntity<Track>(showTrack,HttpStatus.OK);
    }

    @GetMapping("track")
    public List<Track> getAllTracks() {
        List<Track> showAllTracks = trackService.getAllTracks();
        return showAllTracks;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) {
        ResponseEntity responseEntity;
        trackService.deleteTrackById(id);
        responseEntity = new ResponseEntity("Deleted track successfully", HttpStatus.CREATED);
        return responseEntity;
    }

    @PatchMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@RequestBody Track track, @PathVariable("id") int id) {
        Track updatedTrack = trackService.updateTrackById(track, id);
        return new ResponseEntity<>(updatedTrack, HttpStatus.OK);
    }
}
