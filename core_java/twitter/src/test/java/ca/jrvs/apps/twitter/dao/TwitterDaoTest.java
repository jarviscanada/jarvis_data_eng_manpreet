package ca.jrvs.apps.twitter.dao;
import ca.jrvs.apps.twitter.Models.Coordinates;
import ca.jrvs.apps.twitter.Models.Tweet;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import com.sun.jndi.toolkit.url.Uri;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.w3c.dom.Entity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoTest {

    @Mock
    HttpHelper helper;

    @InjectMocks
    private TwitterDao dao;

    private String tweetId = "1207779273412616192";
    private String createdAt = "Thu Dec 19 21:46:32 +0000 2019";
    private String text = "This is my text tweet";
    private double[] coordinates = new double[] {28.7041, 77.1025};
    private Integer retweetCount = 0;
    private Integer favoriteCount = 0;
    private Boolean favorited = false;
    private Boolean retweeted = false;


    private String tweetJson = String.format("{\"created_at\":\"%s\",\"id_str\":\"%s\",\"text\":\"%s\","
            +"\"entities\":{\"hashtags\":[],\"user_mentions\":[]},\"coordinates\": {\"coordinates\":[%f,%f], \"type\": \"Point\"},\"retweet_count\":%d,"
            + "\"favorite_count\":%d,\"favorited\": %s,\"retweeted\":%s}", createdAt, tweetId, text, coordinates[0],
            coordinates[1], retweetCount, favoriteCount, favorited.toString(), retweeted.toString());

    @Before
    public void mockSetup() {
        HttpResponse httpResponse = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);
        HttpEntity entity = EntityBuilder.create().setText(tweetJson).build();

        when(helper.httpPost(any(URI.class))).thenReturn(httpResponse);
        when(helper.httpGet(any(URI.class))).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(entity);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
    }

    @Test
    public void create() throws IOException, URISyntaxException {
        Tweet tweet = new Tweet(text, new Coordinates(coordinates));
        Tweet postedTweet = dao.create(tweet);
        asserts(postedTweet);
    }

    @Test
    public void findById() throws IOException, URISyntaxException {
        Tweet foundTweet = dao.findById(tweetId);
        asserts(foundTweet);
    }

    @Test
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