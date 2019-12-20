package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.Models.Coordinates;
import ca.jrvs.apps.twitter.Models.Tweet;
import ca.jrvs.apps.twitter.dao.CrdDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceTest {


    @Mock
    CrdDao dao;

    @InjectMocks
    private TwitterService service;


    @Test
    public void postTweet() throws IOException, URISyntaxException{
        String text = "random tweet";
        double lat = 77.1025;
        double lon = 28.7041;
        double[] coordinates = new double[] {lon,lat};
        Tweet tweet = new Tweet(text, new Coordinates(coordinates));
        when(dao.create(any(Tweet.class))).thenReturn(tweet);

        Tweet postedTweet = service.postTweet(tweet);

        assertEquals(text, postedTweet.getText());
        assertEquals(lat, postedTweet.getCoordinates().getCoordinates()[1],0);
        assertEquals(lon, postedTweet.getCoordinates().getCoordinates()[0],0);
    }

    @Test
    public void showTweet() throws IOException, URISyntaxException {
        String id = "1207088286646308864";

        Tweet tweet = new Tweet();
        tweet.setId_str(id);
        when(dao.findById(anyString())).thenReturn(tweet);

        Tweet showTweet = service.showTweet(id,null);

        assertEquals(id, showTweet.getId_str());
    }

    @Test
    public void deleteTweets() throws IOException, URISyntaxException {
        String[] tweetIdArray = new String[] {"1207088286646308864", "1207089153961332737"};
        List<Tweet> tweets = new ArrayList<Tweet>(){{
            add(new Tweet(tweetIdArray[0]));
            add(new Tweet(tweetIdArray[1]));
        }};

        when(dao.deleteById(anyString())).thenAnswer(i -> {
            String id = i.getArgument(0);
            for (Tweet tweet: tweets) {
                if (tweet.getId_str().equals(id)) {
                    return tweet;
                }
            }
            return null;
        });

        List<Tweet> deleteTweet = service.deleteTweets(tweetIdArray);

        assertEquals(tweetIdArray.length, deleteTweet.size());

        String[] actualIds = new String[deleteTweet.size()];
        for (int i = 0; i < actualIds.length; i++) {
            actualIds[i] = deleteTweet.get(i).getId_str();
        }

        assertArrayEquals(tweetIdArray, actualIds);
    }

}