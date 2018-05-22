package erteltest;

public class CakeFilter {

    private Integer page;
    private Integer limit;
    private String text;
    private StatusType[] statuses;

    public CakeFilter() {
    }

    public CakeFilter(Integer page, Integer limit, String text, StatusType[] statuses) {
        this.page = page;
        this.limit = limit;
        this.text = text;
        this.statuses = statuses;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public StatusType[] getStatuses() {
        return statuses;
    }

    public void setStatuses(StatusType[] statuses) {
        this.statuses = statuses;
    }

}
