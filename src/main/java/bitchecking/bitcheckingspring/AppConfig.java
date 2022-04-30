package bitchecking.bitcheckingspring;

import bitchecking.bitcheckingspring.api.BinanceApi;
import bitchecking.bitcheckingspring.api.IApi;
import bitchecking.bitcheckingspring.api.UpbitApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public IApi upbitApi() {
        return new UpbitApi();
    }

    @Bean
    public IApi binanceApi() {
        return new BinanceApi();
    }

}
