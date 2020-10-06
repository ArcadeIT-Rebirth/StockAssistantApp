package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;

/*
    Class to create 'users' table with schema history using flyway.
 */

public class V1_0_0__CreateUserTable extends BaseJavaMigration {

    @Override
    public void migrate(final Context context) throws Exception {
        final JdbcTemplate template = new JdbcTemplate(context.getConnection());
        template.execute("CREATE TABLE users (\n"
                + "email VARCHAR(50) primary key,\n"
                + "first_name VARCHAR(25),\n"
                + "last_name VARCHAR(50),\n"
                + "password VARCHAR(64),\n"
                + "user_role VARCHAR(15),\n"
                + "wallet_number VARCHAR(16)\n"
                + ");"
        );
    }
}
