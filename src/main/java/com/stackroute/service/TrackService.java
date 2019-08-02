package com.stackroute.service;

import com.stackroute.domain.Track;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track);

    public Track getTrackById(int id);

    public List<Track> getAllTracks();

    public Track deleteTrackById(int id);

    public Track updateTrackById(Track track, int id);

    public List<Track> getTrackByName(String name);

}
