package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.Models.Coordinates;
import ca.jrvs.apps.twitter.Models.Tweet;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class TwitterDaoIntTest {

    TwitterHttpHelper helper;

    private TwitterDao dao;

    private String tweetId;
    private String text = "Twitter Dao Int test 1";
    private double[] coordinates = new double[] {28.7041, 77.1025};

    {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String accessTokenSecret = System.getenv("accessTokenSecret");

        helper = new TwitterHttpHelper();
        dao = new TwitterDao(helper);
        helper.setupContext(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }

    @Before
    public void create() throws IOException, URISyntaxException {
        Tweet tweet = new Tweet(text, new Coordinates(coordinates));
        Tweet postedTweet = dao.create(tweet);
        tweetId = postedTweet.getId_str();
        asserts(postedTweet);
    }

    @Test
    public void findById() throws IOException, URISyntaxException {
        Tweet foundTweet = dao.findById(tweetId);
        asserts(foundTweet);
    }

    @After
    public void deleteById() throws IOException, URISyntaxException {
        Tweet deletedTweet = dao.deleteById(tweetId);
        asserts(deletedTweet);
    }

    public void asserts(Tweet responseTweet) {
        assertNotNull(responseTweet.getId_str());
        assertEquals(text, responseTweet.getText());
        assertEquals(coordinates[1], responseTweet.getCoordinates().getCoordinates()[1],0);
        assertEquals(coordinates[0], responseTweet.getCoordinates().getCoordinates()[0],0);
    }
}