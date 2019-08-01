package com.stackroute.service;

import com.stackroute.domain.Track;

public interface TrackService {
    //save    getById    getAllTracks    deleteTracksById    updateTracks
    public Track saveTrack(Track track);

    public Track getTrackById(int id);

}
