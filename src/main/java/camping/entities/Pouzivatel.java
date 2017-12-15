package camping.entities;

public class Pouzivatel {

    private Long id;
    private String pozicia;
    private String meno;
    private int pocet_odrobenych_hodin;
    private int vyplata;

    public int getPocet_odrobenych_hodin() {
        return pocet_odrobenych_hodin;
    }

    public void setPocet_odrobenych_hodin(int pocet_odrobenych_hodin) {
        this.pocet_odrobenych_hodin = pocet_odrobenych_hodin;
    }

    public int getVyplata() {
        return vyplata;
    }

    public void setVyplata(int vyplata) {
        this.vyplata = vyplata;
    }

    public String getPozicia() {
        return pozicia;
    }

    public void setPozicia(String pozicia) {
        this.pozicia = pozicia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    @Override
    public String toString() {
        return "Pouzivatel - id: " + id + ". Pozicia: " + pozicia + ". Meno: " + meno + ". Pocet odrobenych hodin: " + pocet_odrobenych_hodin + ". Vyplata: " + vyplata;
    }

}
