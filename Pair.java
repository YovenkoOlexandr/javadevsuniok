import java.util.Objects;

public class Pair {
    private String domain;
    private int counter;

    public Pair(String domain, int counter) {
        this.domain = domain;
        this.counter = counter;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return counter == pair.counter && Objects.equals(domain, pair.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, counter);
    }

    @Override
    public String toString() {
        return domain + " " + counter;
    }
}