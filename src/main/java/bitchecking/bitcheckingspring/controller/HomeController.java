package bitchecking.bitcheckingspring.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        try {
            URL url = new URL("https://api.upbit.com/v1/ticker?markets=KRW-BTC");
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            String price = (String) jsonObject.get("trade_price");
            model.addAttribute("other_price", price);

        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "index";
    }
}
