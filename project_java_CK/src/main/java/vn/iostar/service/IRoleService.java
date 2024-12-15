
package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.Role;

public interface IRoleService {
//	List<Role> findAll();
	
	List<Role> findExceptUserShipper();
	
	List<Role> findExceptUser();
	
	Role getById(Integer id);
	
	Role findByName(String name);
	
	void deleteAll();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Role entity);

//	Optional<Role> findById(Integer id);

	List<Role> findAll();

	<S extends Role> S save(S entity);

	long count();

}
