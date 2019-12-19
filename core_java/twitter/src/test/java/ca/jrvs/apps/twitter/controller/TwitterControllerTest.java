package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.Models.Coordinates;
import ca.jrvs.apps.twitter.Models.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerTest {

    @Mock
    private Service service;

    @InjectMocks
    private TwitterController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void postTweet() throws IOException, URISyntaxException {
         String text = "random tweet";
         String POST = "post";
         double lat = 77.1025;
         double lon = 28.7041;
         double[] coordinates = new double[] {lon,lat};
        Tweet tweet = new Tweet(text, new Coordinates(coordinates));
        when(service.postTweet(any(Tweet.class))).thenReturn(tweet);

         Tweet postedTweet = controller.postTweet(
                 new String[] {POST, text, String.format("%f:%f", coordinates[0], coordinates[1])});

        assertEquals(text, postedTweet.getText());
        assertEquals(lat, postedTweet.getCoordinates().getCoordinates()[1],0);
        assertEquals(lon, postedTweet.getCoordinates().getCoordinates()[0],0);

         try {
             controller.postTweet(new String[] { text });
             fail();
         }
         catch (IllegalArgumentException e) {
             assertTrue(true);
         }

    }

    @Test
    public void showTweet() throws IOException, URISyntaxException {
        String id = "1207088286646308864";
        String SHOW = "show";

        Tweet tweet = new Tweet();
        tweet.setId_str(id);
        when(service.showTweet(any(), any())).thenReturn(tweet);

        Tweet showTweet = controller.showTweet(new String[]{SHOW, id});
        assertEquals(id, showTweet.getId_str());

        try {
            controller.showTweet(new String[] { id });
            fail();
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void deleteTweet() throws IOException, URISyntaxException {
        final String  COMMA = ",";
        String ids = "1207088286646308864,1207089153961332737";
        String DELETE = "delete";
        String[] tweetIdArray = ids.split(COMMA);
        List<Tweet> tweets = new ArrayList<Tweet>(){{
            add(new Tweet(tweetIdArray[0]));
            add(new Tweet(tweetIdArray[1]));
        }};

        when(service.deleteTweets(any())).thenReturn(tweets);

        List<Tweet> deleteTweet = controller.deleteTweet(new String[]{DELETE, ids});
        assertEquals(tweetIdArray.length, deleteTweet.size());

        String[] actualIds = new String[deleteTweet.size()];
        for (int i = 0; i < actualIds.length; i++) {
            actualIds[i] = deleteTweet.get(i).getId_str();
        }

        assertArrayEquals(tweetIdArray, actualIds);

        try {
            controller.deleteTweet(new String[] { ids });
            fail();
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }

    }
}