package burgerfactory.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Repository
public abstract class BaseDao<ENTITY, ID> {

    protected Class<ENTITY> entityClass;

    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        entityClass = (Class) pt.getActualTypeArguments()[0];
    }

    protected EntityManager em() {
        return em;
    }

    @Transactional
    public ENTITY updateEntity(ENTITY entity) {
        return em.merge(entity);
    }

    @Transactional
    public void save(ENTITY entity) {
        em.persist(entity);
        em.flush();
    }
}
