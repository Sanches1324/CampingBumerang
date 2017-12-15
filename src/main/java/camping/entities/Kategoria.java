package camping.entities;

public class Kategoria {

    private Long id;
    private String nazov;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String toString() {
        return "Kategoria - id:" + id + ". Nazov:" + nazov;
    }

}
