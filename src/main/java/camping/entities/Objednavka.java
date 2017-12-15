package camping.entities;

import java.time.LocalDate;

public class Objednavka {

    private Long id;
    private Long pozemokId;
    private Long pouzivatelId;
    private LocalDate datumObjednavky;
    private LocalDate datumPrichodu;
    private LocalDate datumOdchodu;
    private Long pocetDni;
    private boolean platba;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPozemokId() {
        return pozemokId;
    }

    public void setPozemokId(Long pozemokId) {
        this.pozemokId = pozemokId;
    }

    public Long getPouzivatelId() {
        return pouzivatelId;
    }

    public void setPouzivatelId(Long pouzivatelId) {
        this.pouzivatelId = pouzivatelId;
    }

    public LocalDate getDatumObjednavky() {
        return datumObjednavky;
    }

    public void setDatumObjednavky(LocalDate datumObjednavky) {
        this.datumObjednavky = datumObjednavky;
    }

    public LocalDate getDatumPrichodu() {
        return datumPrichodu;
    }

    public void setDatumPrichodu(LocalDate datumPrichodu) {
        this.datumPrichodu = datumPrichodu;
    }

    public LocalDate getDatumOdchodu() {
        return datumOdchodu;
    }

    public void setDatumOdchodu(LocalDate datumOdchodu) {
        this.datumOdchodu = datumOdchodu;
    }

    public Long getPocetDni() {
        return pocetDni;
    }

    public void setPocetDni(Long pocetDni) {
        this.pocetDni = pocetDni;
    }

    public boolean isPlatba() {
        return platba;
    }

    public void setPlatba(boolean platba) {
        this.platba = platba;
    }

    @Override
    public String toString() {
        return "Objednavka - id: " + id + ". Datum objednavky: " + datumObjednavky + ". Datum prichodu: " + datumPrichodu + ". Datum odchodu: " + datumOdchodu + ". Pocet dni: " + pocetDni + ". Platba: " + platba;
    }

}
