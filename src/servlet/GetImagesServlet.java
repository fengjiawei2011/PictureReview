package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import process.CatalogProcess;
import process.PaginationProcess;
import beans.MovieBean;
import beans.PictureBean;

/**
 * Servlet implementation class GetImagesServlet
 */
@WebServlet("/show")
public class GetImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final int NUMBER_OF_PER_PAGE = 20;

	int records_number, pages_number, current_page;
	int groups, group_current;
	String movie_id = "";
	PaginationProcess pp = new PaginationProcess();
	CatalogProcess cp = new CatalogProcess();
	MovieBean current_movie = null; 
	
	public GetImagesServlet() {
		super();
		current_page = 1;
		group_current = 1;
		setPageNum(group_current + "");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// PicDao picDao = new PicDao();
		// List<PictureBean> pictures = picDao.getAllEntries();
		// int currentPage =
		// Integer.parseInt(request.getParameter("currentPage"));
		String operation = request.getParameter("operation");
		
		List<PictureBean> pictures = null;
		
		String group = request.getParameter("group");
		if (group == null) {
			group = group_current + "";
		} else {
			if (Integer.parseInt(group) != group_current) {
				setPageNum(group);
				group_current = Integer.parseInt(group);
				current_page = 1;
			}
		}
		System.out.println("operation : " + operation);
		if (operation != null && operation.equals("next")) {
			if (GoodOrNotServlet.memory != null) {
				GoodOrNotServlet.memory.clear();
			}

			current_page++;

		} else if (operation != null && operation.equals("prev")) {
			if (GoodOrNotServlet.memory != null
					&& !GoodOrNotServlet.memory.isEmpty()) {
				GoodOrNotServlet.memory.clear();
			}
			current_page--;
		} else if (operation != null && operation.equals("saveNext")) {
			current_page++;
			if (GoodOrNotServlet.memory != null
					&& !GoodOrNotServlet.memory.isEmpty()) {
				GoodOrNotServlet.lp.updateByMap(GoodOrNotServlet.memory);
				GoodOrNotServlet.memory.clear();
			}

		}
		
		HttpSession hs = request.getSession();
		List<MovieBean> movies = cp.getMovies();
		
		String movie_id = request.getParameter("movie_id");
		
		if (movie_id != null && operation.equals("chooseMovie")) {
		
			for (MovieBean m : movies) {
				if (movie_id.equals(m.getMovie_id())) {
					current_movie = m;
					hs.setAttribute("current_movie", m);
					groups = m.getGroups();
					hs.setAttribute("groups", groups+"");
					hs.setAttribute("movie_name", m.getMovie_name());
					break;
				}
			}
			pictures = pp.getPicturesByPage(current_page, NUMBER_OF_PER_PAGE,group, current_movie.getMovie_id() );
		}else{
			pictures = pp.getPicturesByPage(current_page, NUMBER_OF_PER_PAGE );
		}
		
		if(operation != null && operation.equals("chooseGroup")){
			pictures = pp.getPicturesByPage(current_page, NUMBER_OF_PER_PAGE,group, current_movie.getMovie_id() );
		}

		hs.setAttribute("pictures", pictures);
		hs.setAttribute("movies", movies);
		// request.getRequestDispatcher("main.jsp?currentPage="+current_page+"&pages="+pages_number).forward(request,
		// response);
		response.sendRedirect("main.jsp?currentPage=" + current_page
				+ "&pages=" + pages_number + "&group=Group " + group);
	}

	public void setPageNum(String g) {
		records_number = pp.getRecords(g);
		if (records_number < NUMBER_OF_PER_PAGE) {
			pages_number = 1;
		} else {
			if (records_number % NUMBER_OF_PER_PAGE == 0) {
				pages_number = records_number / NUMBER_OF_PER_PAGE;
			} else {
				pages_number = records_number / NUMBER_OF_PER_PAGE + 1;
			}

		}
	}

}
