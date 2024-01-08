package com.learn.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learn.storage.model.ListStoreMessage;
import com.learn.storage.model.StoreMessage;

@Service
public class ParseApiService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${parseUrl}")
	private String url;

	public void save(StoreMessage message) {
		restTemplate.postForEntity(url, message, StoreMessage.class);
	}

	public ListStoreMessage getAllData(String skip) {
		String message = "order=-createdAt";
		if(skip!=null && !skip.isEmpty()) {
			message+="&skip="+skip;
		}
 		return restTemplate.getForObject(url+"?"+message, ListStoreMessage.class);
 
	}

	public ListStoreMessage getByName(String key) {
		String message = "where= {\"name\":\"" + key + ":}";
		return restTemplate.getForObject(url, ListStoreMessage.class, message);

	}

}
