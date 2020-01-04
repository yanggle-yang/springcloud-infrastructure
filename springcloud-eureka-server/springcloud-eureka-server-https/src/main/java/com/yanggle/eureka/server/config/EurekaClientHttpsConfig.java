package com.yanggle.eureka.server.config;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Profile({"eureka-server-A","eureka-server-B","eureka-server-C"})
@Configuration
public class EurekaClientHttpsConfig {

    @Value("${config.ssl.client.key-store}")
    String keyStoreFileName;

    @Value("${config.ssl.client.key-store-password}")
    String keyStorePassword;

    @Bean
    public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        EurekaJerseyClientImpl.EurekaJerseyClientBuilder builder = new EurekaJerseyClientImpl.EurekaJerseyClientBuilder();
        builder.withClientName("eureka-client-https");
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(
                        this.getClass().getClassLoader().getResource(keyStoreFileName),keyStorePassword.toCharArray()
                )
                .build();
        builder.withCustomSSL(sslContext);

        builder.withMaxTotalConnections(10);
        builder.withMaxConnectionsPerHost(10);

        DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
        args.setEurekaJerseyClient(builder.build());
        return args;
    }
}