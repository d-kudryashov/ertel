package erteltest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DefaultCakeService implements CakeService {

    private final CakeRepository cakeRepository;

    @Autowired
    public DefaultCakeService(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @Override
    public CompletableFuture<CakeDto> getItem(Long id) {
        return cakeRepository.findById(id).thenApply(CakeConverter::cakeToCakeDTO);
    }

    @Override
    public CompletableFuture<CakeView> getView(CakeFilter filter) {
        PageRequest pageRequest = new PageRequest(filter.getPage(), filter.getLimit());
        return cakeRepository.findByNameAndStatusTypeIn(filter.getText(), filter.getStatuses(), pageRequest)
                .thenApplyAsync(CakeConverter::cakeToCakeDTO)
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> cakeRepository.getTotal(filter.getText(), filter.getStatuses(), pageRequest)).join(),
                        CakeView::new);
    }

    @Override
    public CompletableFuture<Void> saveItem(Cake item) {
        return null;
    }

    @Override
    public CompletableFuture<Void> removeItem(Long id) {
        return null;
    }

    @Override
    public CompletableFuture<Long> getTotal(CakeFilter filter) {
        return cakeRepository.getTotal(filter.getText(), filter.getStatuses(), new PageRequest(filter.getPage(), filter.getLimit()));
    }

}