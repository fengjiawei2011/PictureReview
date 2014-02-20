package dao;

import helper.DBConnectionHelper;
import helper.ProjectHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.accessibility.internal.resources.accessibility;

import beans.MovieBean;
import beans.PictureBean;

public class PicDao {

	public void setIsLike(String id, boolean isLike) {
		ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		String sql = "update " + dbHelper.getTable() + " set interesting = "
				+ isLike + "where id='" + id + "'";

		try {
			Statement s = con.createStatement();
			s.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<PictureBean> getMoviesGroupEntries(String movie_id, int group_id) {
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() + " where group="
				+ group_id + "and movie_id = '" + movie_id + "'";
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getPicBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}

	public List<PictureBean> getPageEntries(int begin, int pages, String group, String movie_id) {
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() + " where `group`="
				+ group + " and movie_id='"+movie_id+"' limit " + begin + "," + pages;
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getPicBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}
	
	public List<PictureBean> getPageEntries(int begin, int pages,  String movie_id) {
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() + " where  movie_id='"+movie_id+"' limit " + begin + "," + pages;
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getPicBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}
	
	public List<PictureBean> getPageEntries(int begin, int pages) {
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() + " limit " + begin + "," + pages;
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getPicBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}

	public List<PictureBean> getAllEntries() {
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from" + dbHelper.getTable();
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getPicBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}

/*	public List<MovieBean> getMovies(ResultSet rs) {
		 List<MovieBean> movies = new ArrayList<MovieBean>();
		 
		 
	}	
	*/
	

	
	
	public List<PictureBean> getPicBeans(ResultSet rs) {
		List<PictureBean> empty = new ArrayList<PictureBean>();
		try {
			while (rs.next()) {

				PictureBean pb = new PictureBean();
				pb.setFrom(rs.getString("from"));
				pb.setGroup(rs.getInt("group"));
				pb.setId(rs.getInt("id"));
				if (rs.getInt("interesting") == 0
						|| rs.getInt("interesting") == 1) {
					pb.setInteresting(rs.getInt("interesting"));
				} else {
					pb.setInteresting(0);
				}

				pb.setLocal_add(rs.getString("local_add"));
				pb.setSource(rs.getString("source"));
				pb.setTitle(rs.getString("title"));
				pb.setUrl(rs.getString("url"));
				pb.setAlt(rs.getString("alt"));
				pb.setMovie_id(rs.getString("movie_id"));
				pb.setMovie_name(rs.getString("movie_name"));
				/*
				 * pb.setImg_id(rs.getString("img_id"));
				 * pb.setImg_title(rs.getString("img_title"));
				 * pb.setImg_description(rs.getString("img_description"));
				 * pb.setImg_isLike(rs.getInt("islike"));
				 */
				empty.add(pb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empty;
	}

	public int getRecords(String group) {
		int num = 0;
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;
		String sql = "select count(*) as num from " + dbHelper.getTable()
				+ " where `group`=" + group;
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				num = rs.getInt("num");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return num;
	}

	public void updateLike(Connection con, String id, int islike) {
		ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		// Connection con = helper.connectDatabase();
		Statement s = null;
		String sql = "update " + dbHelper.getTable() + " set interesting = "
				+ islike + " where id = '" + id + "'";
		try {
			s = con.createStatement();
			s.executeUpdate(sql);
			System.out.println("update like successful ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("update like error ");
		}

	}

}
