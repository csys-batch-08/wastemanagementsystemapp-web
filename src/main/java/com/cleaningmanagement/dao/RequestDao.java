package com.cleaningmanagement.dao;

import java.sql.Date;

import java.util.List;

import com.cleaningmanagement.model.Request;

public interface RequestDao {
	public boolean insertRequestDetails(Request req);
	public List<Request> showRequest();
	public List<Request> showRequest(String search);
	public int findRequestID(int userId, String category, String location);
	public boolean deleteRequest(int requestId);
	public int calculateWeight(String location, Date fromDate, Date toDate);
	
}
