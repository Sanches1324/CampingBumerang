package camping.dao;

import camping.design.PouzivatelFxModel;
import camping.entities.Pouzivatel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MySqlPouzivatelDao implements PouzivatelDao {

    private JdbcTemplate jdbcTemplate;

    public MySqlPouzivatelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createPouzivatela(PouzivatelFxModel pouzivatel) {
        String pouzivatel_create = "INSERT INTO pouzivatel(meno, pozicia, pocet_odrobenych_hodin, vyplata) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(pouzivatel_create, pouzivatel.getMeno(), pouzivatel.getPozicia(), pouzivatel.getOdrobeneHodiny(), pouzivatel.getVyplata());

    }

    @Override
    public List<PouzivatelFxModel> getAll() {
        String pouzivatel_getAll = "SELECT * FROM pouzivatel";
        return jdbcTemplate.query(pouzivatel_getAll, new PouzivatelRowMapper());
    }

    @Override
    public void updatePouzivatela(PouzivatelFxModel pouzivatel) {
        String pouzivatel_update = "UPDATE pouzivatel SET pozicia = ?, pocet_odrobenych_hodin = ?, vyplata = ? WHERE meno = '?'";
        if (pouzivatel.getMeno() == null) {
            createPouzivatela(pouzivatel);
        } else {
            jdbcTemplate.update(pouzivatel_update, pouzivatel.getPozicia(), pouzivatel.getOdrobeneHodiny(), pouzivatel.getVyplata(), pouzivatel.getMeno());
        }

    }

    @Override
    public boolean deletePouzivatela(Long id) {
        String pouzivatel_delete = "DELETE FROM pouzivatel WHERE id = " + id;
        int zmazanych = jdbcTemplate.update(pouzivatel_delete);
        return zmazanych == 1;
    }

    @Override
    public List<PouzivatelFxModel> findById(Long id) {
        String pouzivatel_findById = "SELECT * FROM pouzivatel "
                + "WHERE id = " + id;
        return jdbcTemplate.query(pouzivatel_findById, new PouzivatelRowMapper());
    }

    @Override
    public List<PouzivatelFxModel> findByMeno(String meno) {
        String pouzivatel_findByMeno = "SELECT * FROM pouzivatel "
                + "WHERE meno = " + meno;
        return jdbcTemplate.query(pouzivatel_findByMeno, new PouzivatelRowMapper());
    }

    @Override
    public List<PouzivatelFxModel> findByPozicia(String pozicia) {
        String pouzivatel_findByPozicia = "SELECT * FROM pouzivatel "
                + "WHERE pozicia = " + pozicia;
        return jdbcTemplate.query(pouzivatel_findByPozicia, new PouzivatelRowMapper());
    }

    private class PouzivatelRowMapper implements RowMapper<PouzivatelFxModel> {

        @Override
        public PouzivatelFxModel mapRow(ResultSet rs, int i) throws SQLException {
            PouzivatelFxModel p = new PouzivatelFxModel();
            p.setMeno(rs.getString(2));
            p.setPozicia(rs.getString(3));
            p.setOdrobeneHodiny(rs.getInt(4));
            p.setVyplata(rs.getInt(5));
            return p;
        }

    }

}
