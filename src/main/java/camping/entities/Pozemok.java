package camping.entities;

import java.util.Objects;

public class Pozemok {

    private Long id;
    private Long cisloPozemku;
    private Long kategoria_id;
    private int cena;
    private boolean obsadenost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCisloPozemku() {
        return cisloPozemku;
    }

    public void setCisloPozemku(Long cisloPozemku) {
        this.cisloPozemku = cisloPozemku;
    }

    public Long getKategoria_id() {
        return kategoria_id;
    }

    public void setKategoria_id(Long kategoria_id) {
        this.kategoria_id = kategoria_id;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public boolean isObsadenost() {
        return obsadenost;
    }

    public void setObsadenost(boolean obsadenost) {
        this.obsadenost = obsadenost;
    }

    @Override
    public String toString() {
        return "Pozemok - id:" + id + ". Cislo Pozemku:" + cisloPozemku + ". Kategoria:" + kategoria_id + ". Cena:" + cena + ". Obsadenost:" + obsadenost;
    }

}
