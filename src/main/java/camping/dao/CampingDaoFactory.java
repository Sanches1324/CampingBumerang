package camping.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum CampingDaoFactory {
    INSTANCE;

    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJDBCTemplate() {
        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("CampingUser");
            dataSource.setPassword("campingbumerang");
            dataSource.setUrl("jdbc:mysql://localhost/campingsql?serverTimezone=Europe/Bratislava");

            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    public KategoriaDao getMySqlKategoriaDao() {
        return new MySqlKategoriaDao(getJDBCTemplate());
    }

    public PozemokDao getMySqlPozemokDao() {
        return new MySqlPozemokDao(getJDBCTemplate());

    }

    public ObjednavkaDao getMySqlObjednavkaDao() {
        return new MySqlObjednavkaDao(getJDBCTemplate());
    }

    public PouzivatelDao getMySqlPouzivatelDao() {
        return new MySqlPouzivatelDao(getJDBCTemplate());
    }

    public HesloDao getMySqlHesloDao() {
        return new MySqlHesloDao(getJDBCTemplate());
    }

}
