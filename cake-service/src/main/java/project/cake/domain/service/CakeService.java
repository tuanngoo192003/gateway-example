package project.cake.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import project.cake.application.presenter.request.CakeCreateRequest;
import project.cake.application.presenter.request.CakeUpdateRequest;
import project.cake.application.presenter.response.CakeCreateResponse;
import project.cake.application.presenter.response.CakeUpdateResponse;
import project.cake.application.presenter.response.GetCakeResponse;
import project.cake.domain.entity.Cake;
import project.cake.domain.repository.CakeRepository;
import project.core.domain.repository.BaseRepository;
import project.core.domain.service.BaseService;

@Service
public class CakeService extends BaseService<Cake, String> {

    private CakeRepository repository;

    public CakeService(BaseRepository<Cake, String> repository) {
        super(Cake.class, repository);
        this.repository = (CakeRepository) repository;
    }

    public CakeCreateResponse createCake(CakeCreateRequest request) {
        Cake cake = Cake.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .isAvailable(request.getIsAvailable())
                .build();

        cake = insertBean(cake);

        return CakeCreateResponse.builder()
                .id(cake.getId())
                .name(cake.getName())
                .description(cake.getDescription())
                .price(cake.getPrice())
                .isAvailable(cake.getIsAvailable())
                .build();
    }

    public CakeUpdateResponse updateCake(CakeUpdateRequest request) {

        Cake cake = findByFields(Map.of("id", request.getId()));

        if (cake.getIsDeleted().booleanValue()) {
            cake.setIsDeleted(true);
            updateBean(cake);
            return CakeUpdateResponse.builder().build();
        }

        cake.setName(request.getName());
        cake.setDescription(request.getDescription());
        cake.setPrice(request.getPrice());
        cake.setIsAvailable(request.getIsAvailable());

        cake = updateBean(cake);

        return CakeUpdateResponse.builder()
                .id(cake.getId())
                .name(cake.getName())
                .description(cake.getDescription())
                .price(cake.getPrice())
                .isAvailable(cake.getIsAvailable())
                .build();
    }

    public List<GetCakeResponse> getCakes() {
        List<Cake> cakes = repository.findAll();

        return cakes.stream().map(cake -> GetCakeResponse.builder()
                .id(cake.getId())
                .name(cake.getName())
                .description(cake.getDescription())
                .price(cake.getPrice())
                .isAvailable(cake.getIsAvailable())
                .build()).toList();
    }

}
