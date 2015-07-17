package qilaihai.dao;

import qilaihai.domain.School;

public interface SchoolDao extends BaseDao<School> {
	School findByName(String name);
}
