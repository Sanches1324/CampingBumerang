package camping.dao;

import camping.design.HesloFxModel;
import camping.entities.Heslo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class MySqlHesloDao implements HesloDao {

    private JdbcTemplate jdbcTemplate;

    MySqlHesloDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createHeslo(HesloFxModel heslo) {
        String heslo_update = "INSERT INTO hesla (uzivatel, heslo, sol) VALUES (?, ?, ?)";
        String sol = BCrypt.gensalt();
        String hash = BCrypt.hashpw(heslo.getHeslo(), sol);
        jdbcTemplate.update(heslo_update, heslo.getUzivatel(), hash, sol);

    }

    @Override
    public void updateHeslo(HesloFxModel heslo) {
        String heslo_update = "UPDATE hesla SET heslo = ?, sol = ? WHERE uzivatel = " + "'" + heslo.getUzivatel() + "';";
        String sol = BCrypt.gensalt();
        String hash = BCrypt.hashpw(heslo.getHeslo(), sol);
        jdbcTemplate.update(heslo_update, hash, sol);

    }

    @Override
    public List<HesloFxModel> getAll() {
        String heslo_get = "SELECT * FROM hesla";
        return jdbcTemplate.query(heslo_get, new HesloRowMapper());
    }

    @Override
    public HesloFxModel findByUzivatel(String uzivatel) {
        String heslo_findByUzivatel = "SELECT * FROM hesla "
                + "WHERE uzivatel = " + "'" + uzivatel + "'";
        List<HesloFxModel> hesla = jdbcTemplate.query(heslo_findByUzivatel, new HesloRowMapper());
        return jdbcTemplate.query(heslo_findByUzivatel, (rs) -> {
            HesloFxModel k = new HesloFxModel();
            while (rs.next()) {
                k.setUzivatel(rs.getString(2));
                k.setHeslo(rs.getString(3));
                k.setSol(rs.getString(4));
            }

            return k;
        });
    }

    @Override
    public boolean deleteHesloById(long id) {
        String heslo_delete = "DELETE FROM hesla WHERE id = " + id;
        int zmazanych = jdbcTemplate.update(heslo_delete);
        return zmazanych == 1;
    }

    private class HesloRowMapper implements RowMapper<HesloFxModel> {

        @Override
        public HesloFxModel mapRow(ResultSet rs, int i) throws SQLException {
            HesloFxModel h = new HesloFxModel();
            h.setUzivatel(rs.getString(2));
            h.setHeslo(rs.getString(3));
            h.setSol(rs.getString(4));
            return h;
        }

    }

}
