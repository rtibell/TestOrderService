package se.consid.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class OrderController {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/order/receipt")
    public Mono<String> createReceipt() {
        return webClientBuilder.build()
                .get()
                .uri("http://receipt-service/receipt")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/order/payment")
    public Mono<String> createPayment() {
        return webClientBuilder.build()
                .get()
                .uri("http://payment-service/paymen")
                .retrieve()
                .bodyToMono(String.class);
    }
}
