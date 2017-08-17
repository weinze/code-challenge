package weinze.code.challenge.domain.model;

public abstract class PersistentEntity {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
