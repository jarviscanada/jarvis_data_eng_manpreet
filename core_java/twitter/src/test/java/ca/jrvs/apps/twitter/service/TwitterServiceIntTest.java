package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.Models.Coordinates;
import ca.jrvs.apps.twitter.Models.Tweet;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TwitterServiceIntTest {
    private Service service;
    private String tweetId;

    @Before
    public void postTweet() throws IOException, URISyntaxException {
        //set up variables
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        //setting up dependencies
        HttpHelper httpHelper = new TwitterHttpHelper();
        ((TwitterHttpHelper) httpHelper).setupContext(consumerKey,consumerSecret,accessToken,tokenSecret);
        CrdDao dao = new TwitterDao(httpHelper);
        service = new TwitterService(dao);

        String text = "15 tweet";
        double lon = 28.7041;
        double lat = 77.1025;
        double[] coordinates = new double[] {lon,lat};
        Tweet tweet = new Tweet();
        tweet.setText(text);
        Coordinates coord= new Coordinates();
        coord.setCoordinates(coordinates);
        tweet.setCoordinates(coord);

        Tweet postedTweet = service.postTweet(tweet);

        tweetId = postedTweet.getId_str();

        assertEquals(text, postedTweet.getText());
        assertEquals(lon, postedTweet.getCoordinates().getCoordinates()[0],0);
        assertEquals(lat, postedTweet.getCoordinates().getCoordinates()[1],0);
    }

    @Test
    public void showTweet() throws IOException, URISyntaxException {
        Tweet tweet = new Tweet();
        tweet.setId_str(tweetId);

        Tweet showTweet = service.showTweet(tweet.getId_str(),null);

        assertEquals(tweetId, showTweet.getId_str());

    }

    @After
    public void deleteTweets() throws IOException, URISyntaxException {
        String[] tweetIdArray = new String[] {tweetId};

        List<Tweet> deleteTweet = service.deleteTweets(tweetIdArray);

        assertEquals(tweetIdArray.length, deleteTweet.size());
        String[] actualIds = new String[deleteTweet.size()];
        for (int i = 0; i < actualIds.length; i++) {
            actualIds[i] = deleteTweet.get(i).getId_str();
        }

        assertArrayEquals(tweetIdArray, actualIds);
    }
}