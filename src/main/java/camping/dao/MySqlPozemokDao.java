package camping.dao;

import camping.design.KategoriaFxModel;
import camping.design.PozemokFxModel;
import camping.entities.Kategoria;
import camping.entities.Pozemok;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MySqlPozemokDao implements PozemokDao {

    private JdbcTemplate jdbcTemplate;

    public MySqlPozemokDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createPozemok(PozemokFxModel pozemok) {
        String pozemok_create = "INSERT INTO pozemky(cislo_pozemku, kategoria_id, cena, obsadenost) VALUES(?, ?, ?, ?)";
        if (!pozemok.getObsadenost()) {
            jdbcTemplate.update(pozemok_create, pozemok.getCisloPozemku(), pozemok.getKategoriaId(), pozemok.getCena(), 0);
        } else {
            jdbcTemplate.update(pozemok_create, pozemok.getCisloPozemku(), pozemok.getKategoriaId(), pozemok.getCena(), 1);
        }
    }

    @Override
    public List<PozemokFxModel> getAll() {
        String pozemok_getAll = "SELECT * FROM campingsql.pozemky p LEFT JOIN kategoria k on k.id = p.kategoria_id ";
        return jdbcTemplate.query(pozemok_getAll, new PozemokRowMapper());
    }

    // Update cely pozemok
    @Override
    public void updatePozemok(PozemokFxModel pozemok
    ) {
        String pozemok_update = "UPDATE pozemky SET  cena = ?, kategoria_id = ?, obsadenost = ? WHERE cislo_pozemku = ?";
        if (pozemok.getCisloPozemku() == null) {
            createPozemok(pozemok);
        } else {
            if (pozemok.getObsadenost()) {
                jdbcTemplate.update(pozemok_update, pozemok.getCena(), pozemok.getKategoriaId(), 1, pozemok.getCisloPozemku());
            } else {
                jdbcTemplate.update(pozemok_update, pozemok.getCena(), pozemok.getKategoriaId(), 0, pozemok.getCisloPozemku());
            }
        }

    }

    @Override
    public boolean deletePozemokById(long id
    ) {
        String pozemok_delete = "DELETE FROM pozemky WHERE id = " + id;
        int zmazanych = jdbcTemplate.update(pozemok_delete);
        return zmazanych == 1;
    }

    @Override
    public boolean deletePozemokByCisloPozemku(long cisloPozemku
    ) {
        String pozemok_delete = "DELETE FROM pozemky WHERE cislo_pozemku = " + cisloPozemku;
        int zmazanych = jdbcTemplate.update(pozemok_delete);
        return zmazanych == 1;
    }

    @Override
    public PozemokFxModel findById(long id
    ) {
        String pozemok_findById = "SELECT * FROM pozemky "
                + "WHERE id = " + id;
        return jdbcTemplate.query(pozemok_findById, (rs) -> {
            PozemokFxModel p = new PozemokFxModel();
            while (rs.next()) {
                p.setCisloPozemku(rs.getLong(2));
                p.setKategoriaId(rs.getLong(3));
                p.setCena(rs.getInt(4));
                if (rs.getInt(5) == 1) {
                    p.setObsadenostBoolean("obsadený");
                } else {
                    p.setObsadenostBoolean("voľný");
                }

            }

            return p;
        });
    }

    @Override
    public PozemokFxModel findByCisloPozemku(long cisloPozemku
    ) {
        String pozemok_findByCisloPozemku = "SELECT * FROM campingsql.pozemky p LEFT JOIN kategoria k on k.id = p.kategoria_id "
                + "WHERE cislo_pozemku = " + cisloPozemku;
        return jdbcTemplate.query(pozemok_findByCisloPozemku, (rs) -> {
            PozemokFxModel p = new PozemokFxModel();
            while (rs.next()) {
                p.setCisloPozemku(rs.getLong(2));
                p.setKategoriaId(rs.getLong(3));
                p.setKategoriaString(rs.getString(7));
                p.setCena(rs.getInt(4));
                if (rs.getInt(5) == 1) {
                    p.setObsadenostBoolean("obsadený");
                } else {
                    p.setObsadenostBoolean("voľný");
                }

            }

            return p;
        });
    }

    @Override
    public List<PozemokFxModel> findByCena(int cena
    ) {
        String pozemok_findByCena = "SELECT * FROM pozemky "
                + "WHERE cena = " + cena;
        return jdbcTemplate.query(pozemok_findByCena, new PozemokRowMapper());
    }

    @Override
    public List<PozemokFxModel> findByObsadenost(String obsadenost) {
        String pozemok_findByObsadenost = "";
        if (obsadenost.equals("obsadený")) {
            pozemok_findByObsadenost = "SELECT * FROM pozemky "
                    + "WHERE obsadenost = " + 1;
        } else {
            pozemok_findByObsadenost = "SELECT * FROM pozemky "
                    + "WHERE obsadenost = " + 0;
        }
        return jdbcTemplate.query(pozemok_findByObsadenost, new PozemokRowMapper());

    }

    private class PozemokRowMapper implements RowMapper<PozemokFxModel> {

        @Override
        public PozemokFxModel mapRow(ResultSet rs, int i) throws SQLException {
            PozemokFxModel p = new PozemokFxModel();
            p.setCisloPozemku(rs.getLong(2));
            p.setKategoriaId(rs.getLong(3));
            p.setKategoriaString(rs.getString(7));
            p.setCena(rs.getInt(4));
            if (rs.getInt(5) == 1) {
                p.setObsadenost(true);
                p.setObsadenostBoolean("obsadený");
            } else {
                p.setObsadenost(false);
                p.setObsadenostBoolean("voľný");
            }
//            Object object = rs.getObject(12);
//            if (object != null) {
//                p.setDatumPrichodu((LocalDate) rs.getDate(12).toLocalDate());
//            }
//            Object object2 = rs.getObject(13);
//            if (object2 != null) {
//                p.setDatumOdchodu((LocalDate) rs.getDate(13).toLocalDate());
//            }

            return p;
        }

    }

}
