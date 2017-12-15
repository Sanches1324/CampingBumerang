package camping.dao;

import camping.entities.Pouzivatel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MySqlPouzivatelDao implements PouzivatelDao {

    private JdbcTemplate jdbcTemplate;

    public MySqlPouzivatelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createPouzivatela(Pouzivatel pouzivatel) {
        if (pouzivatel.getId() == null) {
            String pouzivatel_create = "INSERT INTO pouzivatel(meno, pozicia, pocet_odrobenych_hodin, vyplata) VALUES(?, ?, ?, ?)";
            jdbcTemplate.update(pouzivatel_create, pouzivatel.getMeno(), pouzivatel.getPozicia(), pouzivatel.getPocet_odrobenych_hodin(), pouzivatel.getVyplata());
        }
    }

    @Override
    public List<Pouzivatel> getAll() {
        String pouzivatel_getAll = "SELECT * FROM pouzivatel";
        return jdbcTemplate.query(pouzivatel_getAll, new PouzivatelRowMapper());
    }

    @Override
    public void updatePouzivatela(Pouzivatel pouzivatel) {
        String pouzivatel_update = "UPDATE pouzivatel SET meno = ?, pozicia = ?, pocet_odrobenych_hodin = ?, vyplata = ? WHERE id = ?";
        if (pouzivatel.getId() == null) {
            createPouzivatela(pouzivatel);
        } else {
            jdbcTemplate.update(pouzivatel_update, pouzivatel.getMeno(), pouzivatel.getPozicia(), pouzivatel.getPocet_odrobenych_hodin(), pouzivatel.getVyplata());
        }

    }

    @Override
    public boolean deletePouzivatela(Long id) {
        String pouzivatel_delete = "DELETE FROM pouzivatel WHERE id = " + id;
        int zmazanych = jdbcTemplate.update(pouzivatel_delete);
        return zmazanych == 1;
    }

    @Override
    public List<Pouzivatel> findById(Long id) {
        String pouzivatel_findById = "SELECT * FROM pouzivatel "
                + "WHERE id = " + id;
        return jdbcTemplate.query(pouzivatel_findById, new PouzivatelRowMapper());
    }

    @Override
    public List<Pouzivatel> findByMeno(String meno) {
        String pouzivatel_findByMeno = "SELECT * FROM pouzivatel "
                + "WHERE meno = " + meno;
        return jdbcTemplate.query(pouzivatel_findByMeno, new PouzivatelRowMapper());
    }

    @Override
    public List<Pouzivatel> findByPozicia(String pozicia) {
        String pouzivatel_findByPozicia = "SELECT * FROM pouzivatel "
                + "WHERE pozicia = " + pozicia;
        return jdbcTemplate.query(pouzivatel_findByPozicia, new PouzivatelRowMapper());
    }

    private class PouzivatelRowMapper implements RowMapper<Pouzivatel> {

        @Override
        public Pouzivatel mapRow(ResultSet rs, int i) throws SQLException {
            Pouzivatel p = new Pouzivatel();
            p.setId(rs.getLong(1));
            p.setMeno(rs.getString(2));
            p.setPozicia(rs.getString(3));
            p.setPocet_odrobenych_hodin(rs.getInt(4));
            p.setVyplata(rs.getInt(5));
            return p;
        }

    }

}
