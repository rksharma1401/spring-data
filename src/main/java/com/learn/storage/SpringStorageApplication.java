package com.learn.storage;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
public class SpringStorageApplication {

	private void ignoreCertificates() {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringStorageApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder, @Value("${PARSE_APP_ID}") String parseAppId,
			@Value("${PARSE_REST_KEY}") String parseRestApiKey) {
		ignoreCertificates();
		return builder.defaultHeader("X-Parse-Application-Id", parseAppId)
				.defaultHeader("X-Parse-REST-API-Key", parseRestApiKey).build();
	}

}
