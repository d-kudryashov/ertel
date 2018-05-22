package erteltest;

import java.util.List;

public class CakeView {

    private List<CakeDto> items;
    private Integer total;

    public CakeView() {
    }

    public CakeView(List<CakeDto> items) {
        setItems(items);

    }

    public List<CakeDto> getItems() {
        return items;
    }

    public void setItems(List<CakeDto> items) {
        this.items = items;
        total = items.size();
    }

    public Integer getTotal() {
        return total;
    }
}
