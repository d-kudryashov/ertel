package erteltest;

import java.util.concurrent.CompletableFuture;

public interface CakeService {

    CompletableFuture<CakeDto> getItem(Long id);

    CompletableFuture<CakeView> getView(CakeFilter filter);

    CompletableFuture<Void> saveItem(Cake item);

    CompletableFuture<Void> removeItem(Long id);

    CompletableFuture<Long> getTotal(CakeFilter filter);

}