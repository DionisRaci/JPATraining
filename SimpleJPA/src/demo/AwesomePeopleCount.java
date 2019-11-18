package demo;

public class AwesomePeopleCount {
    private Long notCount = Long.parseLong("0");
    private Long count = Long.parseLong("0");

    public Long getNotCount() {
        return notCount;
    }

    public void setNotCount(Long notCount) {
        this.notCount += notCount;
    }

    public void setCount(Long count) {
        this.count += count;
    }

    public Long getCount() {
        return count;
    }

    public AwesomePeopleCount() {
    }

    public AwesomePeopleCount(boolean isAwesome, Long count) {
        if (count != null) {
            if (isAwesome) this.count += count;
            else this.notCount += count;
        }
    }
}
