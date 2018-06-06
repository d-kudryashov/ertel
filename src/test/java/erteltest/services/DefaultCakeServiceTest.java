package erteltest.services;

import erteltest.models.*;
import erteltest.repositories.CakeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCakeServiceTest {

    @Mock
    private CakeRepository cakeRepository;

    @InjectMocks
    private DefaultCakeService cakeService;

    @Test
    public void getItem() {
        long id = 1L;
        Cake cake = new Cake("cake1", StatusType.FRESH);
        given(cakeRepository.findCakeById(id)).willReturn(CompletableFuture.completedFuture(cake));

        CakeDto result = cakeService.getItem(id).join();

        Assert.assertThat(result.getName(), is(cake.getName()));
        Assert.assertThat(result.getStatus(), is(cake.getStatusType().getName()));
    }

    @Test
    public void getView() {
        int page = 0;
        int limit = 10;
        String name = "cake";
        StatusType[] statuses = {StatusType.FRESH};
        CakeFilter cakeFilter = new CakeFilter(page, limit, name, statuses);

        Cake cake = new Cake("cake", StatusType.FRESH);
        List<Cake> cakes = new ArrayList<>();
        cakes.add(cake);
        PageRequest pageRequest = PageRequest.of(cakeFilter.getPage(), cakeFilter.getLimit());
        given(cakeRepository.findByNameAndStatusTypeIn(cakeFilter.getText(), cakeFilter.getStatuses(), pageRequest))
                .willReturn(CompletableFuture.completedFuture(cakes));
        given(cakeRepository.getTotal(cakeFilter.getText(), cakeFilter.getStatuses()))
                .willReturn(CompletableFuture.completedFuture((long) cakes.size()));

        CakeView result = cakeService.getView(cakeFilter).join();

        Assert.assertThat(result.getItems().size(), is(cakes.size()));
        Assert.assertThat(result.getTotal(), is((long) cakes.size()));
        Assert.assertThat(result.getItems().get(0).getName(), is(cakes.get(0).getName()));
    }

    @Test
    public void saveItem() {
        Cake cake = new Cake("cake", StatusType.FRESH);
        ArgumentCaptor<Cake> argumentCaptor = ArgumentCaptor.forClass(Cake.class);

        cakeService.saveItem(cake);

        verify(cakeRepository).save(argumentCaptor.capture());

        Assert.assertThat(cake.getName(), is(argumentCaptor.getValue().getName()));
    }

    @Test
    public void removeItem() {
        long id = 1L;
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);

        cakeService.removeItem(id);

        verify(cakeRepository).deleteById(argumentCaptor.capture());

        Assert.assertThat(id, is(argumentCaptor.getValue()));
    }

    @Test
    public void getTotal() {
        int page = 0;
        int limit = 10;
        String name = "cake";
        StatusType[] statuses = {StatusType.FRESH};
        CakeFilter cakeFilter = new CakeFilter(page, limit, name, statuses);
        long total = 1L;

        given(cakeRepository.getTotal(cakeFilter.getText(), cakeFilter.getStatuses()))
                .willReturn(CompletableFuture.completedFuture(total));

        Long result = cakeService.getTotal(cakeFilter).join();

        Assert.assertThat(result, is(total));
    }

    @Test
    public void ifExists() {
        long id = 1L;
        Optional<Cake> cake = Optional.of(new Cake("cake", StatusType.FRESH));

        given(cakeRepository.findById(id)).willReturn(cake);

        boolean result = cakeService.ifExists(id).join();

        Assert.assertThat(result, is(true));
    }
}