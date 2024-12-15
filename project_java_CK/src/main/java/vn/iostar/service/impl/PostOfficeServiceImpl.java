package vn.iostar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.PostOffice;
import vn.iostar.repository.PostOfficeRepository;
import vn.iostar.service.IPostOfficeService;

@Service
public class PostOfficeServiceImpl implements IPostOfficeService{

	@Autowired
    private PostOfficeRepository postOfficeRepository;

    @Override
	public List<PostOffice> getAllPostOffices() {
        return postOfficeRepository.findAll();
    }
    
    @Override
	public List<String> getAllAddresses() {
        return postOfficeRepository.findAllAddresses(); // Lấy tất cả địa chỉ
    }
    
    @Override
    public List<String> getAllOptionAddress() {
    	return postOfficeRepository.findAllAddresses();
    }
}
