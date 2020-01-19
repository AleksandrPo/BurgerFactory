package burgerfactory.endpoints.auth.dao.impl;

import burgerfactory.endpoints.auth.dao.UserDao;
import burgerfactory.endpoints.auth.model.User;
import burgerfactory.infrastructure.persistence.BaseDao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

public class UserDaoImpl extends BaseDao<User, Long> implements UserDao {

    @Transactional
    public User findUserByUsername(String username) {
        CriteriaBuilder cb = em().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(entityClass);
        Root<User> root = cq.from(entityClass);
        Predicate predicate = cb.equal(root.get("username"), username);

        cq.select(root).where(predicate);

        try {
            return em().createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
