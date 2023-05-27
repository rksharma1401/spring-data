package com.learn.storage.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ListStoreMessage (List<StoreMessage> results  ) { }
