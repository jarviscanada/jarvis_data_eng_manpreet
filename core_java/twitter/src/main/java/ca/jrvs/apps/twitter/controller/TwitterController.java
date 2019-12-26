package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.Models.Coordinates;
import ca.jrvs.apps.twitter.Models.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Controller
@Component
public class TwitterController implements Controller {

    private static final String COORD_SEP = ":" ;
    private static final String COMMA = ",";

    Service service = new TwitterService();

    public TwitterController() {
    }

    @Autowired

    public TwitterController(Service service) {
        this.service = service;
    }

    @Override
    public Tweet postTweet(String[] args) throws IOException, URISyntaxException {
        if(args.length != 3){
            throw new IllegalArgumentException("USAGE: post \"tweet text\" \"long:lat\"");
        }
        String text = args[1];
        String coord = args[2];
        String[] coordArray = coord.split(COORD_SEP);


        Double lat=null, lon=null;

        lat = Double.parseDouble(coordArray[1]);
        lon = Double.parseDouble(coordArray[0]);

        Tweet tweet = new Tweet();
        tweet.setText(text);

        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(new double[] {lon, lat});
        tweet.setCoordinates(coordinates);

        return service.postTweet(tweet);
    }

    @Override
    public Tweet showTweet(String[] args) throws IOException, URISyntaxException {
        if(args.length!=2){
            throw new IllegalArgumentException("USAGE: show \"tweet id\" ");
        }

        String tweetId = args[1];
        Tweet tweet = service.showTweet(tweetId, null);
        return tweet;
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) throws IOException, URISyntaxException {

       if(args.length!=2){
           throw new IllegalArgumentException("USAGE: delete \"tweetid1,tweetid2,tweetid3\"");
       }

       String tweetIds = args[1];
       String[] tweetIdArray = tweetIds.split(COMMA);

        List<Tweet> tweetsDeleted = service.deleteTweets(tweetIdArray);

        return tweetsDeleted;
    }
}
