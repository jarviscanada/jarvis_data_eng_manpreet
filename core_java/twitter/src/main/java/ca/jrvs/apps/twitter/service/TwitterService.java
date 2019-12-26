package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.Models.Coordinates;
import ca.jrvs.apps.twitter.Models.Tweet;
import ca.jrvs.apps.twitter.dao.CrdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@Component
public class TwitterService implements Service {

    private CrdDao<Tweet, String> dao;

    public TwitterService() {
    }

    @Autowired

    public TwitterService(CrdDao<Tweet, String> dao) {
        this.dao = dao;

    }

    @Override
    public Tweet postTweet(Tweet tweet) throws IOException, URISyntaxException {
        if (!isCoordinatesValid(tweet.getCoordinates())) {
             throw new RuntimeException("Incorrect lat long");
        }else if(tweet.getText().length()>140){
            throw new RuntimeException("Incorrect text length");
        }else {
            return dao.create(tweet);
        }
    }


    @Override
    public Tweet showTweet(String id, String[] fields) throws IOException, URISyntaxException {

        if(!isIdValid(id)){
            throw new RuntimeException("Incorrect id format");
        }

        return dao.findById(id);

    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) throws IOException, URISyntaxException {
        List<Tweet> tweetIDs = new ArrayList<>();
        for (String id : ids) {

            if (!isIdValid(id)) {
                throw new RuntimeException("Incorrect id format");
            }

            tweetIDs.add(dao.deleteById(id));

        }
        return tweetIDs;
    }
    // check id format
    public boolean isIdValid (String id){
        return id.length() == 19;
    }

    // validate coordinates
    public boolean isCoordinatesValid(Coordinates coordiates) {
        return  coordiates != null &&
                coordiates.getCoordinates().length == 2 &&
                coordiates.getCoordinates()[0] >= -180 &&
                coordiates.getCoordinates()[0] <= 180 &&
                coordiates.getCoordinates()[1] >= -90 &&
                coordiates.getCoordinates()[1] <= 90;
    }
}
