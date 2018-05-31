package erteltest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {

    @Async
    CompletableFuture<Cake> findById(Long id);

    @Async
    CompletableFuture<List<Cake>> findByNameAndStatusTypeIn(String name, StatusType[] statusTypes, Pageable pageable);

    @Async
    @Query("select count(c) from Cake c")
    CompletableFuture<Long> getTotal(String name, StatusType[] statusTypes);

}
