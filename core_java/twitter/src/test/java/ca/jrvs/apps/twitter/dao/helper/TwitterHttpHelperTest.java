package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.Models.Tweet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@RunWith(MockitoJUnitRunner.class)
public class TwitterHttpHelperTest {

    private String text = "This is my text 2";
    private String id = "1207743349681262593";

    TwitterHttpHelper helper;

    Gson gson;


    @Before
    public void setUp() throws IOException {
        helper = new TwitterHttpHelper();
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String accessTokenSecret = System.getenv("accessTokenSecret");
        helper.setupContext(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        gson =  new GsonBuilder()
                .setDateFormat("EEE MMM dd HH:mm:ss Z yyyy").setPrettyPrinting().create();
    }

    @Test
    public void httpPost() throws URISyntaxException, IOException {
        URI postUri = new URI(String.format("https://api.twitter.com/1.1/statuses/update.json?status=%s", URLEncoder.encode(text, "UTF-8")));
        HttpResponse response = helper.httpPost(postUri);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            Tweet tweet = gson.fromJson(responseString, Tweet.class);
            System.out.println(gson.toJson(tweet));
        }
    }

    @Test
    public void httpGet() throws URISyntaxException, IOException {
        URI getUri = new URI(String.format("https://api.twitter.com/1.1/statuses/show.json?id=%s", URLEncoder.encode(id, "UTF-8")));
        HttpResponse response = helper.httpGet(getUri);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            Tweet tweet = gson.fromJson(responseString, Tweet.class);
            System.out.println(gson.toJson(tweet));
        }
    }
}