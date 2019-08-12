package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
   private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp()
    {
       track = new Track();
        track.setId(10);
        track.setName("John");
        track.setComments("This is comment");
    }

    @After
    public void tearDown(){

        track=null;
    }


    @Test
    public void givenTrackShouldReturnSavedTrack(){
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(10,fetchUser.getId());
    }

    @Test
    public void givenTrackShouldReturnSavedTrackFailure(){
        Track testUser = new Track(2,"Track5","Comment-5");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testUser,track);
    }

    @Test
    public void givenTrackShouldReturnAllTracks(){
        Track u = new Track(15,"Track3","Comment-3");
        Track u1 = new Track(16,"Track4","Comment-4");
        trackRepository.save(u);
        trackRepository.save(u1);
        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Comment-3",list.get(8).getComments());
    }
}