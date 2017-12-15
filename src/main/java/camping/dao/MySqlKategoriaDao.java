package camping.dao;

import camping.design.KategoriaFxModel;
import camping.entities.Kategoria;
import camping.entities.Pozemok;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MySqlKategoriaDao implements KategoriaDao {

    private JdbcTemplate jdbcTemplate;

    public MySqlKategoriaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createKategoria(KategoriaFxModel kategoria) {
        if (kategoria.getId() == null) {
            String kategoria_create = "INSERT INTO kategoria(nazov) VALUE(?)";
            jdbcTemplate.update(kategoria_create, kategoria.getNazov());
        }
    }

    @Override
    public List<KategoriaFxModel> getAll() {
        String kategoria_getAll = "SELECT * FROM kategoria";
        return jdbcTemplate.query(kategoria_getAll, new KategoriaRowMapper());

    }

    @Override
    public void updateKategoriu(KategoriaFxModel kategoria) {
        String kategoria_update = "UPDATE kategoria SET nazov = ? WHERE id = ?";
        if (kategoria.getId() == null) {
            createKategoria(kategoria);
        } else {
            jdbcTemplate.update(kategoria_update, kategoria.getNazov(), kategoria.getId());
        }
    }

    @Override
    public boolean deleteKategoriaById(long id) {
        String kategoria_delete = "DELETE FROM kategoria WHERE id = " + id;
        int zmazanych = jdbcTemplate.update(kategoria_delete);
        return zmazanych == 1;
    }

    @Override
    public KategoriaFxModel findById(long id) {
        String kategoria_findById = "SELECT * FROM kategoria "
                + "WHERE id = " + id;
        return jdbcTemplate.query(kategoria_findById, (rs) -> {
            KategoriaFxModel k = new KategoriaFxModel();
            k.setId(rs.getLong(1));
            k.setNazov(rs.getString(2));

            return k;
        });
    }

    @Override
    public KategoriaFxModel findByNazov(String kategoria) {
        String kategoria_findByNazov = "SELECT * FROM kategoria "
                + "WHERE nazov = " + "'" + kategoria + "'";
        System.out.println(kategoria_findByNazov);
        return jdbcTemplate.query(kategoria_findByNazov, (rs) -> {
            KategoriaFxModel k = new KategoriaFxModel();
            k.setId(rs.getLong(1));
            k.setNazov(rs.getString(2));

            return k;
        });
    }

    private class KategoriaRowMapper implements RowMapper<KategoriaFxModel> {

        @Override
        public KategoriaFxModel mapRow(ResultSet rs, int i) throws SQLException {
            KategoriaFxModel k = new KategoriaFxModel();
            k.setId(rs.getLong(1));
            k.setNazov(rs.getString(2));
            return k;
        }

    }
}
