package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLStatement {

	private Connection con;
	private String query;
	
	public SQLStatement(Connection con, String query) {
		this.con = con;
		this.query = query;
	}
	
	public void exec() throws SQLException {
		try(Statement stmt = con.createStatement();) {
			stmt.execute(query);
			stmt.close();
		}
	}
	
	@Override
	public String toString() {
		return query;
	}
}
