package burgerfactory.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;

public abstract class BaseDao<ENTITY, ID> implements CrudRepository<ENTITY, ID> {

    public ENTITY getFirstElement(ID id) {
        return findById(id).orElse(null);
    }
}
