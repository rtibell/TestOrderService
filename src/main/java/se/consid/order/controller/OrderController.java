package se.consid.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/order/receipt")
    public Mono<String> createReceipt() {
        return webClientBuilder.build()
                .get()
                .uri("http://receipt-service/receipts")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/order/payment")
    public Mono<String> createPayment() {
        return webClientBuilder.build()
                .get()
                .uri("http://payment-service/receipts")
                .retrieve()
                .bodyToMono(String.class);
    }
}
