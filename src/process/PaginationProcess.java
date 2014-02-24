package process;

import helper.DBConnectionHelper;

import java.sql.Connection;
import java.util.List;

import beans.MovieBean;
import beans.PictureBean;
import dao.MovieDao;
import dao.PicDao;

public class PaginationProcess {
	PicDao p_dao = new PicDao();

	public List<PictureBean> getPicturesByPage(int whichPage, int pages,
			String group, String movie_id) {

		int begin = pages * (whichPage - 1);
		return p_dao.getPageEntries(begin, pages, group, movie_id);
	}

	public List<PictureBean> getPicturesByPage(int whichPage, int pages,
			String movie_id) {

		int begin = pages * (whichPage - 1);
		return p_dao.getPageEntries(begin, pages, movie_id);
	}

	public List<PictureBean> getPicturesByPage(int whichPage, int pages) {

		int begin = pages * (whichPage - 1);
		return p_dao.getPageEntries(begin, pages);
	}

	public int getRecords() {
		return p_dao.getRecords();
	}

	public int getRecords(String movie_id) {
		return p_dao.getRecords(movie_id);
	}
	
	public int getRecords(String movie_id, String group) {
		return p_dao.getRecords(movie_id, group);
	}
//	MovieDao md = new MovieDao();
//	DBConnectionHelper helper = new DBConnectionHelper();
//	public List<MovieBean> getMovies(){
//		Connection con = helper.connectDatabase();
//		List<MovieBean> movies = md.getMovies(con);
//		DBConnectionHelper.closeConnection(con);
//		return movies;
//	}
}
