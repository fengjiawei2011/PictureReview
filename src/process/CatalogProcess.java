package process;

import helper.DBConnectionHelper;

import java.sql.Connection;
import java.util.List;

import beans.MovieBean;
import dao.MovieDao;

public class CatalogProcess {
	
	MovieDao md = new MovieDao();
	DBConnectionHelper helper = new DBConnectionHelper();
	public List<MovieBean> getMovies(){
		Connection con = helper.connectDatabase();
		List<MovieBean> movies = md.getMovies(con);
		for(int i = 0; i < movies.size() ; i++){
			movies.get(i).setGroups(md.getGroups(con, movies.get(i).getMovie_id()));
		}
		DBConnectionHelper.closeConnection(con);
		return movies;
	}
}
