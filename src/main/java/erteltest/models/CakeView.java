package erteltest.models;

import java.util.List;

public class CakeView {

    private List<CakeDto> items;
    private Long total;

    public CakeView() {
    }

    public CakeView(List<CakeDto> items, Long total) {
        this.items = items;
        this.total = total;
    }

    public List<CakeDto> getItems() {
        return items;
    }

    public void setItems(List<CakeDto> items) {
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
