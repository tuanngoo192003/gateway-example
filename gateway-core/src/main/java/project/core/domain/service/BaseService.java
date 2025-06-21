package project.core.domain.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.log4j.Log4j2;
import project.core.domain.entity.BaseEntity;
import project.core.domain.repository.BaseRepository;

@Log4j2
public class BaseService<T extends BaseEntity, ID> {

    private final Class<T> c;

    private final BaseRepository<T, ID> repository;

    public BaseService(Class<T> c, BaseRepository<T, ID> repository) {
        this.c = c;
        this.repository = repository;
    }

    public T insertBean(T bean) {
        log.info("Insert bean of type ", c.getName());
        bean.setIsDeleted(false);
        bean.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        bean.setLastModifiedAt(new Timestamp(System.currentTimeMillis()));
        return repository.save(bean);
    }

    public T updateBean(T bean) {
        log.info("Insert bean of type ", c.getName());
        bean.setLastModifiedAt(new Timestamp(System.currentTimeMillis()));
        return repository.save(bean);
    }

    public T findByFields(Map<String, Object> fields) {
        log.info("Find bean of type ", c.getName());
        return repository.findOne(
                (root, query, criteriaBuilder) -> criteriaBuilder
                        .and(buildPredicates(fields, criteriaBuilder, root).toArray(new Predicate[0]))).orElse(null);
    }

    public List<Predicate> buildPredicates(Map<String, Object> fields, CriteriaBuilder builder, Root<T> root) {
        List<Predicate> predicates = new ArrayList<>();
        fields.forEach((field, value) -> predicates.add(builder.equal(root.get(field), value)));
        return predicates;
    }
}
