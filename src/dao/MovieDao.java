package dao;

import helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.MovieBean;

public class MovieDao {

	public List<MovieBean> getMovies(Connection con) {
		List<MovieBean> movies = new ArrayList<MovieBean>();

		DBConnectionHelper dbHelper = new DBConnectionHelper();
		/* Connection con = dbHelper.connectDatabase(); */
		Statement s = null;
		ResultSet rs = null;
		String sql = "select DISTINCT(`movie_id`), movie_name from "
				+ dbHelper.getTable() + "`";
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);

			while (rs.next()) {
				MovieBean movie = new MovieBean();
				movie.setMovie_id(rs.getString("movie_id"));
				movie.setMovie_name(rs.getString("movie_name"));

				movies.add(movie);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return movies;
	}

	public int getGroups(Connection con, String movie_id) {
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		/* Connection con = dbHelper.connectDatabase(); */
		Statement s = null;
		ResultSet rs = null;
		int groups = 0;
		String sql = "select DISTINCT(`group`) from "  +dbHelper.getTable() + "  order by `group` desc";
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);

			if (rs.next()) {
				groups = rs.getInt("group");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groups;
	}
}
