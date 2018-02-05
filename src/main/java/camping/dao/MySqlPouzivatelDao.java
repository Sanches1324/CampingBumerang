package camping.dao;

import camping.design.PouzivatelFxModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class MySqlPouzivatelDao implements PouzivatelDao {

    private JdbcTemplate jdbcTemplate;

    public MySqlPouzivatelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createPouzivatela(PouzivatelFxModel pouzivatel) {
        String pouzivatel_create = "INSERT INTO pouzivatel(meno, prihlasovacie_meno, pozicia, datum_narodenia, adresa, tel_cislo, e_mail, heslo, povolenie) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sol = BCrypt.gensalt();
        String hash = BCrypt.hashpw(pouzivatel.getHeslo(), sol);
        if (pouzivatel.getPovoleny().equals("povolený")) {
            jdbcTemplate.update(pouzivatel_create, pouzivatel.getMeno(), pouzivatel.getPrihl_Meno(), pouzivatel.getPozicia(), pouzivatel.getDatumNarodenia(), pouzivatel.getAdresa(), pouzivatel.getTel_cislo(), pouzivatel.getE_mail(), hash, 1);
        } else {
            jdbcTemplate.update(pouzivatel_create, pouzivatel.getMeno(), pouzivatel.getPrihl_Meno(), pouzivatel.getPozicia(), pouzivatel.getDatumNarodenia(), pouzivatel.getAdresa(), pouzivatel.getTel_cislo(), pouzivatel.getE_mail(), hash, 0);
        }

    }

    @Override
    public List<PouzivatelFxModel> getAll() {
        String pouzivatel_getAll = "SELECT * FROM pouzivatel";
        return jdbcTemplate.query(pouzivatel_getAll, new PouzivatelRowMapper());
    }

    @Override
    public void updatePouzivatela(PouzivatelFxModel pouzivatel) {
        String pouzivatel_update = "UPDATE pouzivatel SET pozicia = ?, datum_narodenia = ?, adresa = ?, tel_cislo = ?, e_mail = ?, povolenie = ? WHERE meno = " + "'" + pouzivatel.getMeno() + "'";
        if (pouzivatel.getPovoleny().equals("povolený")) {
            jdbcTemplate.update(pouzivatel_update, pouzivatel.getPozicia(), pouzivatel.getDatumNarodenia(), pouzivatel.getAdresa(), pouzivatel.getTel_cislo(), pouzivatel.getE_mail(), 1);
        } else {
            jdbcTemplate.update(pouzivatel_update, pouzivatel.getPozicia(), pouzivatel.getDatumNarodenia(), pouzivatel.getAdresa(), pouzivatel.getTel_cislo(), pouzivatel.getE_mail(), 0);
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
                + "WHERE prihlasovacie_meno = " + "'" + meno + "'";
        return jdbcTemplate.query(pouzivatel_findByMeno, new PouzivatelRowMapper());
    }

    @Override
    public List<PouzivatelFxModel> findByMeno2(String meno) {
        String pouzivatel_findByMeno = "SELECT * FROM pouzivatel "
                + "WHERE meno = " + "'" + meno + "'";
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
            p.setId(rs.getLong(1));
            p.setMeno(rs.getString(2));
            p.setPrihl_Meno(rs.getString(3));
            p.setPozicia(rs.getString(4));
            p.setDatumNarodenia((LocalDate) rs.getDate(5).toLocalDate());
            p.setAdresa(rs.getString(6));
            p.setTel_cislo(rs.getString(7));
            p.setE_mail(rs.getString(8));
            p.setHeslo(rs.getString(9));
            if (rs.getInt(10) == 0) {
                p.setPovoleny("nepovolený");
            } else {
                p.setPovoleny("povolený");
            }
            return p;
        }

    }

}
