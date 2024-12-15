package vn.iostar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.iostar.entity.User;
import vn.iostar.model.UserRoleDTO;
import vn.iostar.repository.UserRepositoryAdmin;
import vn.iostar.service.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepositoryAdmin userRepository;

    @Override
    public List<User> findShippers() {
        // Giả sử Role của Shipper có ID là 2, bạn cần query danh sách này.
        return userRepository.findByRoleRoleId(3);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

	@Override
	public long countNewCustomerDay() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long countNewCustomerMonth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long countNewCustomerYear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long countTotalCustomer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getMonthlyTotal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getQuarterlyTotal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> getCustomer(Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRoleDTO> findAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserRoleDTO> getAllEmployee(Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRoleDTO> findByRoleOffice(String role, Integer office) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserRoleDTO> getAllByRoleOffice(String role, Integer officeId, Integer pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
