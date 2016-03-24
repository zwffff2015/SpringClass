package com.darren.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * ���Ƽ�ʹ��JdbcDaoSupport�����Ƽ�ֱ��ʹ��JdbcTemplate��ΪDao��ĳ�Ա����
 * @author DarrenZeng
 *
 */
@Repository
public class DepartmentDao extends JdbcDaoSupport {

	@Autowired
	public void setDataSource2(DataSource dataSource) {
		setDataSource(dataSource);
	}

	public Department get(Integer id) {
		String sql = "SELECT id,dept_name as name FROM departments where id=?";
		RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
		Department department = getJdbcTemplate().queryForObject(sql, rowMapper, 1);
		return department;
	}
}
