package vn.iostar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.iostar.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value = "SELECT count(*) AS number FROM users "
			+ "WHERE CONVERT(DATE, create_date) = CONVERT(DATE, GETDATE()) "
			+ "AND role_id = "
			+ "(SELECT role_id from roles where name='user')", nativeQuery = true)
    long countNewCustomerDay();
	
	@Query(value = "SELECT count(*) AS number "
			+ "FROM users "
			+ "WHERE MONTH(create_date) = MONTH(GETDATE()) "
			+ "AND YEAR(create_date) = YEAR(GETDATE()) "
			+ "AND role_id = (SELECT TOP 1 role_id from roles where name='user')", nativeQuery = true)
	long countNewCustomerMonth();
	
	@Query(value = "SELECT count(*) AS number "
			+ "FROM users "
			+ "WHERE YEAR(create_date) = YEAR(GETDATE()) "
			+ "AND role_id = (SELECT TOP 1 role_id from roles where name='user')", nativeQuery = true)
	long countNewCustomerYear();
	
	@Query(value = "SELECT count(*) AS number "
			+ "FROM users "
			+ "WHERE role_id = (SELECT TOP 1 role_id from roles where name='user')", nativeQuery = true)
	long countTotalCustomer();
	
	@Query(value = "WITH AllMonths AS (\r\n"
			+ "SELECT 1 AS month_number UNION SELECT 2 UNION SELECT 3 UNION SELECT 4\r\n"
			+ "UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8\r\n"
			+ "UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12)\r\n"
			+ "SELECT COUNT(p.user_id) AS totalMonth\r\n"
			+ "FROM AllMonths m LEFT JOIN users p \r\n"
			+ "ON MONTH(p.create_date) = m.month_number\r\n"
			+ "AND YEAR(p.create_date) = YEAR(GETDATE())\r\n"
			+ "GROUP BY m.month_number\r\n"
			+ "ORDER BY m.month_number;", nativeQuery = true)
	List<Long> getMonthlyTotal();
	
	@Query(value = "WITH Quarters AS\r\n"
			+ "(SELECT 1 AS quarter_number UNION\r\n"
			+ "SELECT 2 UNION\r\n"
			+ "SELECT 3 UNION\r\n"
			+ "SELECT 4)\r\n"
			+ "SELECT COUNT(p.user_id) AS totalQuarter\r\n"
			+ "FROM Quarters q\r\n"
			+ "LEFT JOIN users p ON q.quarter_number = CASE \r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 1 AND 3 THEN 1\r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 4 AND 6 THEN 2\r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 7 AND 9 THEN 3\r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 10 AND 12 THEN 4 END\r\n"
			+ "AND YEAR(p.create_date) = YEAR(GETDATE())\r\n"
			+ "GROUP BY q.quarter_number\r\n"
			+ "ORDER BY q.quarter_number;", nativeQuery = true)
	List<Long> getQuarterlyTotal();
	
	boolean existsByEmail(String email);
	
    User findByEmail(String email);

}
