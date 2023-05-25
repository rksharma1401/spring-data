package com.learn.storage.dao;

import org.springframework.data.repository.CrudRepository;

import com.learn.storage.model.StoreData;


public interface StoreDataDao extends CrudRepository<StoreData,Long>{

}
