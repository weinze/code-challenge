package weinze.code.challenge.domain.repository;

import static java.util.Objects.nonNull;
import static weinze.code.challenge.domain.utils.CollectionUtils.stream;

import java.util.List;

import com.google.common.collect.Lists;
import weinze.code.challenge.domain.model.PersistentEntity;

public abstract class AbstractRepository<T extends PersistentEntity> {

    public T find(Long id) {
        return stream(findAll()).filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    public List<T> findAll() {
        return cachedEntities();
    }

    public void save(T entity) {
        if(nonNull(cachedEntities())) {
            cachedEntities(Lists.newArrayList());
        }

        cachedEntities().add(entity);
    }

    public void saveAll(List<T> entities) {
        cachedEntities(entities);
    }

    protected abstract List<T> cachedEntities();
    protected abstract void cachedEntities(List<T> entities);

}
