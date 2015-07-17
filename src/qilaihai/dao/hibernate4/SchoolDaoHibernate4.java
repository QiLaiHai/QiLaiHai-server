package qilaihai.dao.hibernate4;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import qilaihai.dao.SchoolDao;
import qilaihai.domain.School;

public class SchoolDaoHibernate4 extends BaseDaoHibernate4<School> implements
		SchoolDao {

	@Transactional
	@Override
	public School findByName(String name) {
		School result = null;

		List<School> schools = find("select s from School as s where s.name = ?0",
				name);
		
		if (schools != null && schools.size() > 0) {
			result = schools.get(0);
		}

		return result;
	}
}
