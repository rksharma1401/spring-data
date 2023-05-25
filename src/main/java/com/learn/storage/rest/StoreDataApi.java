package com.learn.storage.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

import com.learn.storage.dao.StoreDataDao;
import com.learn.storage.model.StoreData;

@RestController
@RequestMapping("storeDataApi")
@CrossOrigin
public class StoreDataApi {

	@Autowired
	private StoreDataDao dao;
	
	@GetMapping("isWorking")
	public ResponseEntity<String> isWorking() {
		return  ResponseEntity.ok("Running");

	}
	@GetMapping("justTest")
	public ResponseEntity<String> justTest() {
		return  ResponseEntity.ok("Running");

	}
	
	@GetMapping("deleteAll")
	public ResponseEntity<String> deleteAll() {
		dao.deleteAll();
		return  ResponseEntity.ok("Delete");

	}
	@PostMapping("createData")
	public ResponseEntity<StoreData> createData(@RequestParam String title, @RequestParam String text) {

		StoreData d = new StoreData(title, text);
		StoreData save = dao.save(d);
		
		try (Jedis jedis = new Jedis("redis://red-chno5tfdvk4n43b4pc3g", 6379,300000)) {
 
				
				String cachedResponse = jedis.get(title);
				if(cachedResponse==null){
					cachedResponse="";
				}
 				jedis.set(title, cachedResponse+"\n"+ text);

 			}catch (Exception e) {
 			}

		return ResponseEntity.ok(save);
	}

   @GetMapping("getRedis")
   public ResponseEntity<String> getRedis(@RequestParam String key) {
			try (Jedis jedis = new Jedis("redis://red-chno5tfdvk4n43b4pc3g", 6379,300000)) {
				String cachedResponse = jedis.get(key);
				if(cachedResponse==null){
					cachedResponse="";
				}
				return ResponseEntity.ok(cachedResponse);
 			}catch (Exception e) {
				 return ResponseEntity.ok(e.getMessage());
 			}
       

    }
	
	@GetMapping("getAll")
	public @ResponseBody List<StoreData> getAllData() {

		List<StoreData> result = new ArrayList<>();
		dao.findAll().forEach(result::add);

		return result;

	}
	
	
	
	 
}
