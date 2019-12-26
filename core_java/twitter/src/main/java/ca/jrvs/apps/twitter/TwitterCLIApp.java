package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.Models.Tweet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class TwitterCLIApp {

    private static final String USAGE= "USAGE: TwitterCLIApp post|show|delete [options]";

    private Controller controller;

    public TwitterCLIApp(Controller controller) {
        this.controller = controller;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

    TwitterHttpHelper helper = new TwitterHttpHelper();
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String accessTokenSecret = System.getenv("accessTokenSecret");

    {
        helper.setupContext(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }

    CrdDao twitterDao = new TwitterDao(helper);
    Service twitterService = new TwitterService(twitterDao);
    Controller twitterController = new TwitterController(twitterService);
    TwitterCLIApp app = new TwitterCLIApp(twitterController);

    app.run(args);
    }

    public void run(String[] args) throws IOException, URISyntaxException {
        if (args.length == 0) {
            throw new IllegalArgumentException(USAGE);
        }

        switch (args[0]) {
            case "post":
                printTweet(controller.postTweet(args));
                break;
            case "show":
                printTweet(controller.showTweet(args));
                break;
            case "delete":
                controller.deleteTweet(args).forEach(this::printTweet);
                break;
            default:
                throw new RuntimeException(USAGE);
        }
    }

    private void printTweet(Tweet tweet){
            Gson gson =  new GsonBuilder()
                    .setDateFormat("EEE MMM dd HH:mm:ss Z yyyy").setPrettyPrinting().create();
            System.out.println(gson.toJson(tweet));
    }

}
