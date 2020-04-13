package com.learn.moviecatalog.models;

import java.util.ArrayList;
import java.util.List;

public class CatalogItem {

	List<Catalog> catalogList = new ArrayList<Catalog>();

	public List<Catalog> getCatalogList() {
		return catalogList;
	}

	public void setCatalogList(List<Catalog> catalogList) {
		this.catalogList = catalogList;
	}
	
}
