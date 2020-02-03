package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "stock/market/batch?symbols=%s&types=quote&token=";
    private static final int HTTP_OK = 200;
    private final String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
                         MarketDataConfig marketDataConfig){
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }
    @Override
    public <S extends IexQuote> S save(S s) {
        return null;
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    /**
     * Get a single IexQuote
     * @param ticker
     * @return IexQuote
     * @throws IllegalArgumentException if ticker is invalid
     * @throws DataRetrievalFailureException if Http reuquest fails
     */
    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional<IexQuote> iexQuote;
        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

        if (quotes.size() == 0){
            return Optional.empty();
        } else if (quotes.size() ==1) {
            iexQuote = Optional.of(quotes.get(0));
        } else {
            throw new DataRetrievalFailureException("Unexpected number of quotes.");
        }
        return iexQuote;
    }

    @Override
    public List<IexQuote> findAllById(Iterable<String> tickers){
        Optional<String> response= null;
        IexQuote quote = null;
        String quoteString = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //check ticker format
        for (String ticker : tickers){
            if (!ticker.matches("[a-zA-Z]{2,4}")){
                throw new IllegalArgumentException("Illegal ticker format.");
            }
        }
        String URI =  String.format(IEX_BATCH_URL, String.join(",", tickers));

        try {
            response = executeHttpGet(URI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject quotesJson = new JSONObject(response.get());

        List<IexQuote> quotes = new ArrayList<>();
        for(String ticker : tickers){
            quoteString = quotesJson.getJSONObject(ticker).getJSONObject("quote").toString();
            quote = gson.fromJson(quoteString, IexQuote.class);
            quotes.add(quote);
        }

        return quotes;

    }

    private Optional<String> executeHttpGet(String url) throws URISyntaxException, IOException {
        String responseString = null;
        URI uri = new URI(url.toString());
        HttpClient httpClient = getHttpClient();

        //Creating a HttpGet object
        HttpGet httpget = new HttpGet(uri);

         // Execute the HTTP Request
        HttpResponse httpresponse = httpClient.execute(httpget);

        if (httpresponse.getStatusLine().getStatusCode() != HTTP_OK) {
            throw new RuntimeException("Invalid URL");
        }else{
            HttpEntity entity = httpresponse.getEntity();
            responseString = EntityUtils.toString(entity, "UTF-8");
            Optional<String> response = Optional.of(responseString);
            return response;
        }

    }

    /**
     * Borrow a Http client form httpClientConnectionManager
     * @return httpClient
     */
    private CloseableHttpClient getHttpClient(){
        return HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                .setConnectionManagerShared(true)
                .build();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<IexQuote> findAll() {
        return null;
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(IexQuote iexQuote) {

    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
