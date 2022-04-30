package bitchecking.bitcheckingspring.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;

public class UpbitApi implements IApi{
    @Override
    public Double getPrice(Coin coin) {
        try {
            URL url = new URL("https://api.upbit.com/v1/ticker?markets=KRW-" + coin.toString());
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(result);

            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            return (Double) jsonObject.get("trade_price");
        }catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
