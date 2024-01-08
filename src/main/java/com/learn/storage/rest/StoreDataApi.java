package com.learn.storage.rest;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

 import com.learn.storage.model.ListStoreMessage;
import com.learn.storage.model.StoreMessage;
import com.learn.storage.service.ParseApiService;

@RestController
@RequestMapping("storeDataApi")
@CrossOrigin
public class StoreDataApi {

	@Value("${PASSKEY}")
	private String pass;
  

	@Autowired
	private ParseApiService parse;

	@GetMapping("isWorking")
	public ResponseEntity<String> isWorking() {
		return ResponseEntity.ok("Running");

	}

	@GetMapping("justTest")
	public ResponseEntity<String> justTest() {
		return ResponseEntity.ok("justTest");

	}

 

	private String oldMessage="";
	
	@SuppressWarnings("rawtypes")
	@PostMapping("createData")
	public ResponseEntity createData(@RequestParam String title, @RequestParam String text) {

		if(oldMessage.equals(text)) {
			return ResponseEntity.noContent().build();
		}
		oldMessage=text;
		parse.save(new StoreMessage(title, text, null));

		return ResponseEntity.noContent().build();
	}

	@GetMapping(value="getAll",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ListStoreMessage getAllData(@RequestParam String pass,@RequestParam(required = false) String skip) {

		if (!pass.equals(this.pass)) {
			return null;
		}
		
		 ListStoreMessage allData = parse.getAllData(skip);
		 System.out.println("size of message received "+ allData.results().size());
		 allData.results().sort((o1, o2) -> {
			OffsetDateTime odt1 = OffsetDateTime.parse(o1.createdAt());
			OffsetDateTime odt2 = OffsetDateTime.parse(o2.createdAt());
			return odt2.compareTo(odt1);
		});
		 
		 return allData;
	

	}
	
	@GetMapping("getData")
	public @ResponseBody ListStoreMessage getData(@RequestParam String key,@RequestParam String pass) {

		if (!pass.equals(this.pass)) {
 			return null;
		}

		return parse.getByName(key);

	}

}
