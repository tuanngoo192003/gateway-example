package project.core.domain.service;

import java.sql.Timestamp;

import project.core.domain.entity.BaseEntity;
import project.core.domain.repository.BaseRepository;

public class BaseService<T extends BaseEntity, ID> {

    private final Class<T> c;

    private final BaseRepository<T, ID> repository;

    public BaseService (Class<T> c, BaseRepository<T, ID> repository) {
        this.c = c;
        this.repository = repository;
    }

    public T insertBean(T bean) {
        bean.setIsDeleted(false);
        bean.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        bean.setLastModifiedAt(new Timestamp(System.currentTimeMillis()));
        return repository.save(bean);
    }
}
