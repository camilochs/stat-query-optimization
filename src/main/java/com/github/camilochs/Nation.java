package com.github.camilochs;

public class Nation {
	
	private Long key;
	public Long getKey(){
		return key;
	}
	public void setKey(Long key){
		this.key = key;
	}
	
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	private long regionKey;
	public long getRegionKey(){
		return regionKey;
	}
	public void setRegionKey(long regionKey){
		this.regionKey = regionKey;
	}
	
	private String comment;
	public String getComment(){
		return comment;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	
}
