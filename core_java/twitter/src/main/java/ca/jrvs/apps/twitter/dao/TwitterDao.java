package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.Models.Coordinates;
import ca.jrvs.apps.twitter.Models.Tweet;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.DateFormat;

@Component
public class TwitterDao implements CrdDao<Tweet, String> {

    // URI constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";

    // URI Symbols
    private static final String QUERY_SYM = "?";
    private static final String AMBERSAND = "&";
    private static final String EQUAL = "=";
    private static final String SLASH = "/";

    //Response Code
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet tweet) throws URISyntaxException, IOException {
        StringBuilder builder = new StringBuilder(API_BASE_URI);
        builder.append(POST_PATH)
                .append(QUERY_SYM)
                .append("status")
                .append(EQUAL)
                .append(URLEncoder.encode(tweet.getText(), "utf-8"));

        if (tweet.getCoordinates() != null && tweet.getCoordinates().getCoordinates().length == 2) {
            builder.append(AMBERSAND)
                    .append("long")
                    .append(EQUAL)
                    .append(tweet.getCoordinates().getCoordinates()[0])
                    .append(AMBERSAND)
                    .append("lat")
                    .append(EQUAL)
                    .append(tweet.getCoordinates().getCoordinates()[1]);
        }
        URI postUri = new URI(builder.toString());
        HttpResponse response = httpHelper.httpPost(postUri);
        if (response.getStatusLine().getStatusCode() == HTTP_OK) {
            HttpEntity entity = response.getEntity();
            // returns JSON
            String responseString = EntityUtils.toString(entity, "UTF-8");
            Gson gson =  new GsonBuilder()
                    .setDateFormat("EEE MMM dd HH:mm:ss Z yyyy").create();
            // GSON library maps JSON to Tweet object
            Tweet responseTweet = gson.fromJson(responseString, Tweet.class);
            return responseTweet;
        } else {
            throw new RuntimeException("Failed to get response");
        }
    }

    @Override
    public Tweet findById(String id) throws IOException, URISyntaxException {
        StringBuilder builder = new StringBuilder(API_BASE_URI);
        builder.append(SHOW_PATH)
                .append(QUERY_SYM)
                .append("id")
                .append(EQUAL)
                .append(URLEncoder.encode(id, "utf-8"));
        URI getUri = new URI(builder.toString());
        HttpResponse response = httpHelper.httpGet(getUri);
        if (response.getStatusLine().getStatusCode() == HTTP_OK) {
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            Gson gson =  new GsonBuilder()
                    .setDateFormat("EEE MMM dd HH:mm:ss Z yyyy").create();
            Tweet responseTweet = gson.fromJson(responseString, Tweet.class);
            return responseTweet;
        } else {
            throw new RuntimeException("Failed to get response: " + response.getStatusLine().toString());
        }

    }

    @Override
    public Tweet deleteById(String id) throws IOException, URISyntaxException {
        StringBuilder builder = new StringBuilder(API_BASE_URI);
        builder.append(DELETE_PATH)
                .append(SLASH)
                .append(URLEncoder.encode(id, "utf-8"))
                .append(".json");
        URI getUri = new URI(builder.toString());
        HttpResponse response = httpHelper.httpPost(getUri);
        if(response.getStatusLine().getStatusCode() == HTTP_OK){
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            Gson gson =  new GsonBuilder()
                    .setDateFormat("EEE MMM dd HH:mm:ss Z yyyy").create();
            Tweet responseTweet = gson.fromJson(responseString, Tweet.class);
            return responseTweet;
        }else {
            throw new RuntimeException("Failed to delete response: " + response.getStatusLine().toString());
        }

    }
}
