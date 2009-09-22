package ufofileorganizer.core.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

public class DBServiceHibernate extends ADBServiceHibernate {

	public DBServiceHibernate(String aName, String aConnectionString) {
		super(aName, aConnectionString);
	}

	public boolean commit() {
		try {
			session().flush();
			session().beginTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			session().clear();
			e.printStackTrace(System.err);
			return false;
		}
	}

	public int executeQuery(String aHqlQuery) {
		Query query = session().createQuery(aHqlQuery);
		return query.executeUpdate();
	}

	public boolean delete(Object aObject) {
		try {
			session().beginTransaction();
			session().delete(aObject);
			return true;
		} catch (RuntimeException e) {
			session().clear();
			e.printStackTrace(System.err);
			return false;
		}
	}

	public boolean insert(Object aObject) {
		try {
			session().beginTransaction();
			session().save(aObject);
			return true;
		} catch (RuntimeException e) {
			session().clear();
			e.printStackTrace(System.err);
			return false;
		}
	}

	public boolean rollback() {
		try {
			session().beginTransaction().rollback();
			return true;
		} catch (RuntimeException e) {
			session().clear();
			e.printStackTrace(System.err);
			return false;
		}
	}

	public <T> List<T> select(Class<T> selectClass,
			WhereClausesList whereClauses) {

		List<T> resultArray = new ArrayList<T>();
		try {
			session().beginTransaction();

			Criteria crit = session().createCriteria(selectClass);

			// TODO da sistemare con polimorfismo
			for (int i = 0; i < whereClauses.size(); i++) {
				if (whereClauses.get(i) instanceof EqualsClause) {
					crit.add(Restrictions.eq(whereClauses.get(i).property(),
							whereClauses.get(i).value()));
				}
				if (whereClauses.get(i) instanceof LikeClause) {
					crit.add(Restrictions.like(whereClauses.get(i).property(),
							whereClauses.get(i).value()));
				}
				if (whereClauses.get(i) instanceof LikeIgnoreCaseClause) {
					crit.add(Restrictions.ilike(whereClauses.get(i).property(),
							whereClauses.get(i).value()));
				}
			}



			resultArray = getResult(crit);
			// resultArray = crit.list();


		} catch (RuntimeException e) {
			session().clear();
			e.printStackTrace(System.err);
		}
		return resultArray;
	}

	public boolean update(Object aObject) {
		try {
			session().beginTransaction();
			session().update(aObject);
			return true;
		} catch (RuntimeException e) {
			session().clear();
			e.printStackTrace(System.err);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> getResult(Criteria crit) {
		return crit.list();
	}

	@Override
	public boolean close() {
		session().close();
		return true;
	}

}
