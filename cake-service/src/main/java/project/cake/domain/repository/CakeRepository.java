package project.cake.domain.repository;

import org.springframework.stereotype.Repository;

import project.cake.domain.entity.Cake;
import project.core.domain.repository.BaseRepository;

@Repository
public interface CakeRepository extends BaseRepository<Cake, String> {
    
}
