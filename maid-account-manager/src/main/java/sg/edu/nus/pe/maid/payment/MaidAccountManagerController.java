package sg.edu.nus.pe.maid.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sg.edu.nus.pe.maid.payment.dto.MaidUsageBill;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(MaidAccountManagerController.BASE_URL)
public class MaidAccountManagerController {

    public static final String BASE_URL = "/api/bill";
    private Logger log = LoggerFactory.getLogger(MaidAccountManagerController.class);

    @GetMapping(path = "/get")
    @ResponseStatus(HttpStatus.OK)
    public MaidUsageBill getBill() {
        double random = new Random().nextDouble();
        if (random<0.4){
            log.error("Random Exception for value "+ random, new RuntimeException ("Random Exception is thrown @ " + new Date()));
        }
        String randomMonth = Month.of(ThreadLocalRandom.current().nextInt(1, 12)).getDisplayName(TextStyle.FULL, Locale.US);
        String randomYear = String.valueOf(ThreadLocalRandom.current().nextInt(2000, 2020));
        int id = Math.abs(ThreadLocalRandom.current().nextInt());
        double amount = Math.ceil(ThreadLocalRandom.current().nextDouble(500, 1200));
        log.info("Returning Maid Usage for the period {}, {} with Id {} and Amount {}", randomMonth, randomYear, id, amount);
        return new MaidUsageBill(
                id,
                amount,
                String.format("Version 2 of the API - Payment for %s %s", randomMonth, randomYear)
        );
    }

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }
}