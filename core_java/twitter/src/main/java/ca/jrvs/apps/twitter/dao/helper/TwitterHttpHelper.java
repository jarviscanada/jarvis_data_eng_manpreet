package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;
import oauth.signpost.signature.HmacSha1MessageSigner;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.PresentationDirection;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class TwitterHttpHelper implements HttpHelper {

    private OAuthConsumer oAuthConsumer;

    public TwitterHttpHelper() {
    }

    @Autowired
    public TwitterHttpHelper(@Value("${consumerKey}") String consumerKey,
                             @Value("${consumerSecret}") String consumerSecret,
                             @Value("${accessToken}") String accessToken,
                             @Value("${accessTokenSecret}") String accessTokenSecret) {
        setupContext(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }

    public void setupContext(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        oAuthConsumer.setTokenWithSecret(accessToken, accessTokenSecret);
    }

    public void authorize(HttpRequestBase httpRequest) throws RuntimeException {
        try {
            oAuthConsumer.sign(httpRequest);
        } catch (OAuthMessageSignerException e) {
            throw new RuntimeException(e);
        } catch (OAuthExpectationFailedException e) {
            throw new RuntimeException(e);
        } catch (OAuthCommunicationException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpClient createHttpClient() {
        return HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
    }

    @Override
    public HttpResponse httpPost(URI uri) {

        // Check oAuthConcumser is initialized
        if (oAuthConsumer == null) {
            throw new RuntimeException("OAuth context is not set");
        }

        // Create http client with standard cookies
        HttpClient httpClient = createHttpClient();

        // Create Http post request
        HttpPost httpPost = new HttpPost(uri);

        // Add Authorization headers
        authorize(httpPost);
        /*
         * Execute the HTTP Request
         */
        try {
            HttpResponse response = httpClient.execute(httpPost);
            return response;
        } catch (IOException e) {
            throw new RuntimeException("Request execution failed", e);
        }
    }



    @Override
    public HttpResponse httpGet(URI uri) {
        // Check oAuthConcumser is initialized
        if (oAuthConsumer == null) {
            throw new RuntimeException("OAuth context is not set");
        }

        // Create http client with standard cookies
        HttpClient httpClient = createHttpClient();

        //Creating a HttpGet object
        HttpGet httpget = new HttpGet(uri);

        // Add Authorization headers
        authorize(httpget);

        /*
         * Execute the HTTP Request
         */
        try {
            HttpResponse httpresponse = httpClient.execute(httpget);
            return httpresponse;
        } catch (IOException e) {
            throw new RuntimeException("Request execution failed", e);
        }
    }
}
