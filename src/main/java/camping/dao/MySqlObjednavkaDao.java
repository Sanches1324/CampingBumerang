package camping.dao;

import camping.design.ObjednavkaFxModel;
import camping.entities.Objednavka;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MySqlObjednavkaDao implements ObjednavkaDao {

    private JdbcTemplate jdbcTemplate;

    public MySqlObjednavkaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createObjednavku(ObjednavkaFxModel objednavka) {
        String objednavka_create = "INSERT INTO objednavky(pozemky_id, pouzivatel_id, datum_objednavky, datum_prichodu, datum_odchodu, pocet_dni, platba, zakaznik_meno, zakaznik_cislo) VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if (objednavka.getPlatba()) {
            jdbcTemplate.update(objednavka_create, objednavka.getPozemokId(), objednavka.getPouzivatelId(), objednavka.getDatumObjednavky(), objednavka.getDatumPrichodu(), objednavka.getDatumOdchodu(), objednavka.getPocetDni(), 1, objednavka.getMenoZakaznika(), objednavka.getTelCisloZakaznika());
        } else {
            jdbcTemplate.update(objednavka_create, objednavka.getPozemokId(), objednavka.getPouzivatelId(), objednavka.getDatumObjednavky(), objednavka.getDatumPrichodu(), objednavka.getDatumOdchodu(), objednavka.getPocetDni(), 0, objednavka.getMenoZakaznika(), objednavka.getTelCisloZakaznika());
        }

    }

    @Override
    public List<ObjednavkaFxModel> getAll() {
        String objednavka_getAll = "SELECT * FROM campingsql.objednavky o left join pouzivatel p on o.pouzivatel_id = p.id;";
        return jdbcTemplate.query(objednavka_getAll, new ObjednavkaRowMapper());
    }

    @Override
    public void updateObjednavku(ObjednavkaFxModel objednavka) {
        if (objednavka.getId() == null) {
            createObjednavku(objednavka);
        } else {
            String objednavka_update = "UPDATE pouzivatel SET pozemok_id = ?, pouzivatel_id = ?, datum_objednavky = ?, datum_prichodu = ?, datum_odchodu = ?, pocet_dni = ?, platba = ?, zakaznik_meno = ?, zakaznik_cislo = ? WHERE id = ?";
            if (objednavka.getPlatba()) {
                jdbcTemplate.update(objednavka_update, objednavka.getPozemokId(), objednavka.getPouzivatelId(), objednavka.getDatumObjednavky(), objednavka.getDatumPrichodu(), objednavka.getDatumOdchodu(), objednavka.getPocetDni(), 1, objednavka.getMenoZakaznika(), objednavka.getTelCisloZakaznika());
            } else {
                jdbcTemplate.update(objednavka_update, objednavka.getPozemokId(), objednavka.getPouzivatelId(), objednavka.getDatumObjednavky(), objednavka.getDatumPrichodu(), objednavka.getDatumOdchodu(), objednavka.getPocetDni(), 0, objednavka.getMenoZakaznika(), objednavka.getTelCisloZakaznika());
            }
        }
    }

    @Override
    public boolean deleteObjednavku(Long id) {
        String objednavka_delete = "DELETE FROM objednavky WHERE id = " + id;
        int zmazanych = jdbcTemplate.update(objednavka_delete);
        return zmazanych == 1;

    }

    @Override
    public List<ObjednavkaFxModel> findByPozemokId(Long pozemok_id) {
        String objednavka_findByPozemokId = "SELECT * FROM campingsql.objednavky o left join pouzivatel p on o.pouzivatel_id = p.id "
                + "WHERE pozemky_id = " + pozemok_id;
        return jdbcTemplate.query(objednavka_findByPozemokId, new ObjednavkaRowMapper());
    }

    @Override
    public List<ObjednavkaFxModel> findByPouzivatelId(Long pouzivatel_id) {
        String objednavka_findByPouzivatelId = "SELECT * FROM campingsql.objednavky o left join pouzivatel p on o.pouzivatel_id = p.id "
                + "WHERE pouzivatel_id = " + pouzivatel_id;
        return jdbcTemplate.query(objednavka_findByPouzivatelId, new ObjednavkaRowMapper());
    }

    @Override
    public List<ObjednavkaFxModel> findByDatumObjednavky(LocalDate datumObjednavky) {
        String objednavka_findByDatumObjednavky = "SELECT * FROM campingsql.objednavky o left join pouzivatel p on o.pouzivatel_id = p.id "
                + "WHERE datum_objednavky = " + "'" + datumObjednavky + "'";
        return jdbcTemplate.query(objednavka_findByDatumObjednavky, new ObjednavkaRowMapper());
    }

    @Override
    public List<ObjednavkaFxModel> findByDatumPrichodu(LocalDate datumPrichodu) {
        String objednavka_findByDatumPrichodu = "SELECT * FROM campingsql.objednavky o left join pouzivatel p on o.pouzivatel_id = p.id "
                + "WHERE datum_prichodu = " + "'" + datumPrichodu + "'";
        return jdbcTemplate.query(objednavka_findByDatumPrichodu, new ObjednavkaRowMapper());
    }

    @Override
    public List<ObjednavkaFxModel> findByDatumOdchodu(LocalDate datumOdchodu) {
        String objednavka_findByDatumOdchodu = "SELECT * FROM campingsql.objednavky o left join pouzivatel p on o.pouzivatel_id = p.id "
                + "WHERE datum_odchodu = " + "'" + datumOdchodu + "'";
        return jdbcTemplate.query(objednavka_findByDatumOdchodu, new ObjednavkaRowMapper());
    }

    @Override
    public List<ObjednavkaFxModel> findByPocetDni(int pocetDni) {
        String objednavka_findByDatumPocetDni = "SELECT * FROM campingsql.objednavky o left join pouzivatel p on o.pouzivatel_id = p.id "
                + "WHERE pocet_dni = " + pocetDni;
        return jdbcTemplate.query(objednavka_findByDatumPocetDni, new ObjednavkaRowMapper());
    }

    @Override
    public List<ObjednavkaFxModel> findByPlatba(boolean platba) {
        String objednavka_findByPlatba = "";
        if (platba == true) {
            objednavka_findByPlatba = "SELECT * FROM campingsql.objednavky o left join pouzivatel p on o.pouzivatel_id = p.id "
                    + "WHERE platba = " + 1;
        } else {
            objednavka_findByPlatba = "SELECT * FROM campingsql.objednavky o left join pouzivatel p on o.pouzivatel_id = p.id "
                    + "WHERE platba = " + 0;
        }
        return jdbcTemplate.query(objednavka_findByPlatba, new ObjednavkaRowMapper());
    }

    private class ObjednavkaRowMapper implements RowMapper<ObjednavkaFxModel> {

        @Override
        public ObjednavkaFxModel mapRow(ResultSet rs, int i) throws SQLException {
            ObjednavkaFxModel o = new ObjednavkaFxModel();
            o.setId(rs.getLong(1));
            o.setPozemokId(rs.getLong(2));
            o.setPouzivatelId(rs.getLong(3));
            o.setDatumObjednavky((LocalDate) rs.getDate(4).toLocalDate());
            o.setDatumPrichodu((LocalDate) rs.getDate(5).toLocalDate());
            o.setDatumOdchodu((LocalDate) rs.getDate(6).toLocalDate());
            o.setPocetDni(rs.getLong(7));
            if (rs.getInt(8) == 1) {
                o.setPlatba(true);
                o.setPlatbaString("zaplatené");
            } else {
                o.setPlatba(false);
                o.setPlatbaString("nezaplatené");
            }
            o.setMenoZakaznika(rs.getString(9));
            o.setTelCisloZakaznika(rs.getString(10));
            o.setMenoPouziatela(rs.getString(12));

            return o;
        }

    }

}
