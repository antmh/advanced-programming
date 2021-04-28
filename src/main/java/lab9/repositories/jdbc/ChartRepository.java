package lab9.repositories.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lab9.entities.Chart;

class ChartRepository extends JDBCRepository<Chart> {
	@Override
	protected String getCreateStatement() {
		return "INSERT INTO charts (name, creation_date) VALUES (?, ?)";
	}

	@Override
	protected String getFindByNameStatement() {
		return "SELECT id, name, creation_date FROM charts WHERE name = ?";
	}

	@Override
	protected String getFindByIdStatement() {
		return "SELECT id, name, creation_date FROM charts WHERE id = ?";
	}

	@Override
	protected void bindCreateStatement(PreparedStatement statement, Chart item) throws SQLException {
		statement.setString(1, item.getName());
		statement.setDate(2, new Date(item.getCreationDate().getTime()));
	}

	@Override
	protected Chart extractFromFindResults(ResultSet results) throws SQLException {
		Chart chart = new Chart();
		chart.setId(results.getInt("id"));
		chart.setName(results.getString("name"));
		chart.setCreationDate(results.getDate("creation_date"));
		return chart;
	}
}
