package bitchecking.bitcheckingspring.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;

public class BinanceApi implements IApi{
    @Override
    public Double getPrice(Coin coin) {
        try {
            URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol=" + coin.toString() + "USDT");
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

            Double priceUSD = Double.parseDouble((String) jsonObject.get("price"));
            Double exchangeRate = 1260.0;
            Double priceKRW = priceUSD * exchangeRate;
            BigDecimal ret = new BigDecimal(priceKRW);

            return priceKRW;
        }catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
}
