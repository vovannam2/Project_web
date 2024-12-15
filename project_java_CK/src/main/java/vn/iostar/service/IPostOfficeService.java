package vn.iostar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.iostar.entity.PostOffice;


public interface IPostOfficeService {

	List<PostOffice> getAllPostOffices();

	List<String> getAllAddresses();

	List<String> getAllOptionAddress();

	

}
