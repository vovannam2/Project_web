package vn.iostar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<PostOffice> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<PostOffice> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostOffice> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PostOffice> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PostOffice> getAll(Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}
}
