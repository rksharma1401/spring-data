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
	
	@PostMapping("createData")
	public ResponseEntity<StoreData> createData(@RequestParam String title, @RequestParam String text) {

		StoreData d = new StoreData(title, text);
		StoreData save = dao.save(d);

		return ResponseEntity.ok(save);

	}

	@GetMapping("getAll")
	public @ResponseBody List<StoreData> getAllData() {

		List<StoreData> result = new ArrayList<>();
		dao.findAll().forEach(result::add);

		return result;

	}
}
