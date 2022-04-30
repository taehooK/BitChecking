package bitchecking.bitcheckingspring.controller;

import bitchecking.bitcheckingspring.AppConfig;
import bitchecking.bitcheckingspring.api.Coin;
import bitchecking.bitcheckingspring.api.IApi;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        IApi api1 = ac.getBean("upbitApi", IApi.class);
        Double price_one = api1.getPrice(Coin.BTC);
        model.addAttribute("price_one", String.format("%.2f", price_one));

        IApi api2 = ac.getBean("binanceApi", IApi.class);
        Double price_other = api2.getPrice(Coin.BTC);
        model.addAttribute("price_other", String.format("%.2f", price_other));

        Double diff = price_other / price_one;
        model.addAttribute("price_diff", String.format("%.2f", diff));

        return "index";
    }
}
