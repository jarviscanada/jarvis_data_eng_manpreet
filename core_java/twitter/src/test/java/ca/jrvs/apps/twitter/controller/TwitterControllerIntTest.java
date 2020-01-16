package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.Models.Tweet;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class TwitterControllerIntTest {
    private Controller controller;
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
        Service twitterService = new TwitterService(dao);
        controller = new TwitterController(twitterService);

        String text = "hi tweet";
        String POST = "post";
        double lon = 28.7041;
        double lat = 77.1025;
        double[] coordinates = new double[] {lon,lat};

        Tweet postedTweet = controller.postTweet(
                new String[] {POST, text, String.format("%f:%f", coordinates[0], coordinates[1])});

        tweetId = postedTweet.getId_str();

        assertEquals(text, postedTweet.getText());
        assertEquals(lon, postedTweet.getCoordinates().getCoordinates()[0],0);
        assertEquals(lat, postedTweet.getCoordinates().getCoordinates()[1],0);
    }

    @Test
    public void showTweet() throws IOException, URISyntaxException {
        String SHOW = "show";

        Tweet tweet = new Tweet();
        tweet.setId_str(tweetId);

        Tweet showTweet = controller.showTweet(new String[]{SHOW, tweetId});
        assertEquals(tweetId, showTweet.getId_str());
    }

    @After
    public void deleteTweet() throws IOException, URISyntaxException { //change tweet text
        final String  COMMA = ",";
        String DELETE = "delete";
        String[] tweetIdArray = tweetId.split(COMMA);
        List<Tweet> tweets = new ArrayList<Tweet>(){{
            add(new Tweet(tweetId));
        }};

        List<Tweet> deleteTweet = controller.deleteTweet(new String[]{DELETE, tweetId});
        assertEquals(tweetIdArray.length, deleteTweet.size());

        String[] actualIds = new String[deleteTweet.size()];
        for (int i = 0; i < actualIds.length; i++) {
            actualIds[i] = deleteTweet.get(i).getId_str();
        }
        assertArrayEquals(tweetIdArray, actualIds);


    }
}