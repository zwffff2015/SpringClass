package com.darren.spring.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JDBCTest {
	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	private EmployeeDao employeeDao;
	private DepartmentDao departmentDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		employeeDao = (EmployeeDao) ctx.getBean("employeeDao");
		departmentDao = (DepartmentDao) ctx.getBean("departmentDao");
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean("namedParameterJdbcTemplate");
	}

	/**
	 * ʹ�þ�������ʱ������ʹ��update(String sql, SqlParameterSource paramSource)�������и��²���
	 * 1. SQL����еĲ��������������һ�£�
	 * 2. ʹ��SqlParameterSource��BeanPropertySqlParameterSourceʵ������Ϊ������
	 */
	@Test
	public void testNamedParameterJdbcTemplate2() {
		String sql = "INSERT INTO employees(last_name,email,dept_id) VALUES(:lastName,:email,:deptId)";

		Employee employee = new Employee();
		employee.setLastName("XYZ");
		employee.setEmail("xyz@163.com");
		employee.setDeptId(3);

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		namedParameterJdbcTemplate.update(sql, paramSource);
	}

	/**
	 * ����Ϊ���������֡� 1. �ŵ㣺���ж��������������ȥ��Ӧλ�ã�ֱ�Ӷ�Ӧ������������ά���� 2. ȱ�㣺��Ϊ�鷳��
	 */
	@Test
	public void testNamedParameterJdbcTemplate() {
		String sql = "INSERT INTO employees(last_name,email,dept_id) VALUES(:ln,:email,:deptId)";

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ln", "FF");
		paramMap.put("email", "ff@163.com");
		paramMap.put("deptId", 2);

		namedParameterJdbcTemplate.update(sql, paramMap);
	}

	@Test
	public void testEmployeeDao() {
		System.out.println(employeeDao.get(1));
	}

	@Test
	public void testDepartmentDao() {
		System.out.println(departmentDao.get(1));
	}

	/**
	 * ִ���������£�INSERT, UPDATE, DELETE
	 * ���һ��������Object[]��List���ͣ���Ϊ�޸�һ����¼��Ҫһ��Object���飬��������Ҫ���Object����
	 */
	@Test
	public void testBatchUpdate() {
		String sql = "INSERT INTO employees(Last_Name,Email,Dept_id) values(?,?,?)";

		List<Object[]> batchArgs = new ArrayList<>();

		batchArgs.add(new Object[] { "AA", "AA@163.com", 1 });
		batchArgs.add(new Object[] { "BB", "BB@163.com", 2 });
		batchArgs.add(new Object[] { "CC", "CC@163.com", 3 });
		batchArgs.add(new Object[] { "DD", "DD@163.com", 1 });

		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	/**
	 * ��ȡ�����е�ֵ������ͳ�Ʋ�ѯ ʹ��queryForObject(String sql, Class<Long> requiredType)
	 */
	@Test
	public void testQueryForObject2() {
		String sql = "SELECT count(id) FROM employees";
		long count = jdbcTemplate.queryForObject(sql, Long.class);

		System.out.println(count);
	}

	/**
	 * ��ѯʵ����ļ��� ע����õĲ���queryForList����
	 */
	@Test
	public void testQueryForList() {
		String sql = "SELECT id,last_name,email FROM employees WHERE id>?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql, rowMapper, 5);

		System.out.println(employees);
	}

	/**
	 * �����ݿ��л�ȡһ����¼��ʵ�ʵõ���Ӧ��һ������ ע�⣺���ǵ���queryForObject(String sql,Class
	 * <Employee> requiredType,Object... args)������ ����Ҫ����queryForObject(String
	 * sql,RowMapper<Employee> rowMapper,Object... args) 1.
	 * ���е�RowMapperָ�����ȥӳ���������У����õ�ʵ����ΪBeanPropertyRowMapper 2.
	 * ʹ��SQL���еı�����������������������ӳ�䡣���磺Last_Name lastName 3.
	 * ��֧�ּ������ԡ�JdbcTemplate������һ��JDBC��С���ߣ�������ORM���
	 */
	@Test
	public void testQueryForObject() {
		String sql = "SELECT id,Last_Name lastName,email FROM employees WHERE id=?";

		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);

		System.out.println(employee);
	}

	/**
	 * ִ��INSERT, UPDATE, DELETE
	 */
	@Test
	public void testUpdate() {
		String sql = "UPDATE employees SET Last_Name=? where Id=?";
		jdbcTemplate.update(sql, "Jack", 1);
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource.getConnection());
	}
}
