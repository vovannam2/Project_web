package vn.iostar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.iostar.entity.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Integer>{

	@Query(value = "SELECT ISNULL(SUM(shipping_fee), 0) AS totalDay FROM parcels "
			+ "WHERE CONVERT(DATE, create_date) = CONVERT(DATE, GETDATE());", nativeQuery = true)
    long revenueOnCurrentDate();
	
	@Query(value = "SELECT ISNULL(SUM(shipping_fee), 0) AS totalWeek FROM parcels " +
            "WHERE create_date >= DATEADD(week, DATEDIFF(week, 0, GETDATE()), 0) " +
            "AND create_date <= GETDATE();", nativeQuery = true)
    long revenueOnCurrentWeek();
	
	@Query(value = "SELECT ISNULL(SUM(shipping_fee), 0) AS totalMonth FROM parcels " +
            "WHERE MONTH(create_date) = MONTH(GETDATE()) " +
            "AND YEAR(create_date) = YEAR(GETDATE());", nativeQuery = true)
	long revenueOnCurrentMonth();
	
	@Query(value = "SELECT ISNULL(SUM(shipping_fee), 0) AS totalYear FROM parcels " +
            "WHERE YEAR(create_date) = YEAR(GETDATE());", nativeQuery = true)
	long revenueOnCurrentYear();
	
	@Query(value = "WITH AllMonths AS ( \r\n"
			+ "SELECT 1 AS month_number UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 \r\n"
			+ "UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8\r\n"
			+ "UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12\r\n"
			+ ")\r\n"
			+ "SELECT ISNULL(SUM(p.shipping_fee), 0) AS totalMonth \r\n"
			+ "FROM AllMonths m \r\n"
			+ "LEFT JOIN parcels p ON MONTH(p.create_date) = m.month_number \r\n"
			+ "AND YEAR(p.create_date) = YEAR(GETDATE()) \r\n"
			+ "GROUP BY m.month_number \r\n"
			+ "ORDER BY m.month_number;", nativeQuery = true)
	List<Long> getMonthlyTotal();
	
	@Query(value = "WITH Quarters AS (\r\n"
			+ "SELECT 1 AS quarter_number UNION\r\n"
			+ "SELECT 2 UNION\r\n"
			+ "SELECT 3 UNION\r\n"
			+ "SELECT 4\r\n"
			+ ")\r\n"
			+ "SELECT \r\n"
			+ "ISNULL(SUM(p.shipping_fee), 0) AS totalQuarter\r\n"
			+ "FROM Quarters q\r\n"
			+ "LEFT JOIN parcels p \r\n"
			+ "ON q.quarter_number = \r\n"
			+ "CASE \r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 1 AND 3 THEN 1\r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 4 AND 6 THEN 2\r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 7 AND 9 THEN 3\r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 10 AND 12 THEN 4\r\n"
			+ "END\r\n"
			+ "AND YEAR(p.create_date) = YEAR(GETDATE())\r\n"
			+ "GROUP BY q.quarter_number\r\n"
			+ "ORDER BY q.quarter_number;", nativeQuery = true)
	List<Long> getQuarterlyTotal();
}
