package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.springframework.data.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceTest {

    private Track track;

    //Create a mock for UserRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private TrackServiceImplement trackService;
    private List<Track> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setName("Track-1");
        track.setComments("This is track-1");
        track.setId(10);
        list = new ArrayList<>();
        list.add(track);
    }

    @After
    public void tearDown() {
        track = null;
        list = null;
    }

    @Test
    public void givenTrackShouldReturnSavedTrack() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track, savedTrack);

        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void givenTrackShouldReturnTrackFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track, savedTrack);
        verify(trackRepository, times(1)).save(track);

    }

    @Test
    public void givenTrackShouldReturnAllTracks() throws TrackNotFoundException{

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> trackList = trackService.getAllTracks();
        Assert.assertEquals(list, trackList);
        verify(trackRepository, times(1)).save(track);
    }

    @Test
    public void givenTrackShouldUpdatePreviousTrack() throws TrackNotFoundException{
        trackRepository.save(track);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.ofNullable(track));
        verify(trackRepository, times(1)).save(track);

    }

    @Test
    public void getAllTracksTestFailure(){
        trackRepository.save(track);
        when(trackRepository.findAll()).thenReturn(null);
        List<Track> list=trackService.getAllTracks();
        Assert.assertNotEquals(track,list);
    }

    @Test
    public void givenTrackIdShouldReturnTrack() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.findById(10)).thenReturn(Optional.of(track));
        Optional<Track> getTrack = trackRepository.findById(10);
        Assert.assertEquals(track, getTrack.get());
        verify(trackRepository, times(1)).findById(10);
    }
}