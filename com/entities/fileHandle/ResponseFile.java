package com.entities.fileHandle;

public class ResponseFile {
	private Integer id;
	private String fileName;
	private String name;
	private String url;
	private String type;
	private long size;

	public ResponseFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseFile(Integer id, String fileName, String name, String url, String type, long size) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}