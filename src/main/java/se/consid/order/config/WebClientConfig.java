package se.consid.order.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConfig {
    @LoadBalanced
    @Bean
    public WebClient.Builder webClientBuilder() {
        HttpClient httpClient = HttpClient.create(ConnectionProvider.create("myConnectionProvider"))
                .tcpConfiguration(client ->
                        client.bootstrap(bootstrap ->
                                //Way to modify tcp client connect settings.
                                bootstrap.resolver(DefaultAddressResolverGroup.INSTANCE)));
        return WebClient.builder();
    }
}
