package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import process.CatalogProcess;
import process.LikeProcess;
import process.PaginationProcess;
import beans.MovieBean;
import beans.PictureBean;

/**
 * Servlet implementation class GetImagesServlet
 */
@WebServlet("/show")
public class GetImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final int NUMBER_OF_PER_PAGE = 15;

//	int records_number, pages_number, current_page;
//	int groups, group_current;
//	String movie_id = "";
//	PaginationProcess pp = new PaginationProcess();
//	CatalogProcess cp = new CatalogProcess();
//	MovieBean current_movie = null;

	public GetImagesServlet() {
		super();
//		current_page = 1;
//		group_current = 1;
//		setPageNum();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		myPost(request, response);
	}
	
	
	
	public void myPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PictureBean> pictures = null;
		String url = "main.jsp";
		String operation = request.getParameter("operation");
		HttpSession hs = request.getSession();
		LikeProcess lp = new LikeProcess();
		PaginationProcess pp = new PaginationProcess();
		CatalogProcess cp = new CatalogProcess();
		if(operation != null && operation.equals("next")) {
			
			if (hs.getAttribute("memory") != null) {
				((Map<String, Object>)hs.getAttribute("memory")).clear();
			}
			int currentPage =((Integer)hs.getAttribute("current_page")).intValue();
			int pages_num = ((Integer)hs.getAttribute("pages_num")).intValue();
			if(currentPage < pages_num ){
				hs.setAttribute("current_page",++currentPage);
			}
			//get next page gossip
			pictures = this.getPicture(hs);
			
		}else if (operation != null && operation.equals("prev")) {

			if (request.getSession().getAttribute("memory") != null) {
				((Map<String, Object>)hs.getAttribute("memory")).clear();
			}
			
			int currentPage =((Integer)hs.getAttribute("current_page")).intValue();
			if(currentPage > 1){
				hs.setAttribute("current_page",--currentPage );
			}
			//get prev page gossip
			pictures = this.getPicture(hs);
			
		}else if (operation != null && operation.equals("saveNext")) {
			
			Map<String, Object> memory =(Map<String, Object>) hs.getAttribute("memory");
			int currentPage =((Integer)hs.getAttribute("current_page")).intValue();
			int pages_num = ((Integer)hs.getAttribute("pages_num")).intValue();
			if(currentPage < pages_num ){
				hs.setAttribute("current_page",++currentPage);
			}
			if (memory != null && ! memory.isEmpty()) {
				lp.updateByMap(memory);
				memory.clear();
			}
			
			pictures = this.getPicture(hs);
			
		}else if(operation != null && operation.equals("showAll")){
			
			Map<String, Object> memory = new LinkedHashMap<String, Object>();
			
			List<MovieBean> movies = cp.getMovies();
			hs.setAttribute("movies",movies);
			hs.setAttribute("current_page", 1);
			hs.setAttribute("pages_num", getPages());
			hs.setAttribute("memory", memory);
			
			pictures = pp.getPicturesByPage(1, NUMBER_OF_PER_PAGE);
			
		}else if (operation != null && operation.equals("chooseMovie")) {
			
			hs.setAttribute("current_page",1);
			String movie_id = request.getParameter("movie_id");
			hs.setAttribute("pages_num", getPages(movie_id));
			MovieBean cm = getCurrentMovie(movie_id, (List<MovieBean>)hs.getAttribute("movies"));
			hs.setAttribute("groups", cm.getGroups()+"");
			hs.setAttribute("current_group","1");
			hs.setAttribute("current_movie", cm );
			
			pictures = pp.getPicturesByPage(1, NUMBER_OF_PER_PAGE,"1", movie_id);
			//get movie related gossip 
		}else if(operation != null && operation.equals("chooseGroup")){
			String group = request.getParameter("group");
			hs.setAttribute("current_group", group);
			String movie_id = ((MovieBean)hs.getAttribute("current_movie")).getMovie_id();
			hs.setAttribute("pages_num",  getPages(movie_id,group));
			hs.setAttribute("current_page", 1);
			pictures = this.getPicture(hs);
			
		}
		request.setAttribute("pictures", pictures);
		request.getRequestDispatcher(url).forward(request,response);
	}
	
	
	
	
	public MovieBean getCurrentMovie(String movie_id , List<MovieBean> movies){
		MovieBean currentM = null;
		for (MovieBean m : movies) {
			if (movie_id.equals(m.getMovie_id())) {
				currentM = m;break;
			}
		}
		
		return currentM;
	}
	
	
	public int getPages(String movie_id, String group_id){
		PaginationProcess pp = new PaginationProcess();
		int recordsNum = pp.getRecords(movie_id,group_id);
		int pagesNum = 0;
		if (recordsNum < NUMBER_OF_PER_PAGE) {
			pagesNum = 1;
		} else {
			if (recordsNum % NUMBER_OF_PER_PAGE == 0) {
				pagesNum = recordsNum / NUMBER_OF_PER_PAGE;
			} else {
				pagesNum = recordsNum / NUMBER_OF_PER_PAGE + 1;
			}

		}
		return pagesNum;
	}
	
	public int getPages(String movie_id){
		PaginationProcess pp = new PaginationProcess();
		int recordsNum = pp.getRecords(movie_id);
		int pagesNum = 0;
		if (recordsNum < NUMBER_OF_PER_PAGE) {
			pagesNum = 1;
		} else {
			if (recordsNum % NUMBER_OF_PER_PAGE == 0) {
				pagesNum = recordsNum / NUMBER_OF_PER_PAGE;
			} else {
				pagesNum = recordsNum / NUMBER_OF_PER_PAGE + 1;
			}

		}
		return pagesNum;
	}
	
	public int getPages(){
		PaginationProcess pp = new PaginationProcess();
		int recordsNum = pp.getRecords();
		int pagesNum = 0;
		if (recordsNum < NUMBER_OF_PER_PAGE) {
			pagesNum = 1;
		} else {
			if (recordsNum % NUMBER_OF_PER_PAGE == 0) {
				pagesNum = recordsNum / NUMBER_OF_PER_PAGE;
			} else {
				pagesNum = recordsNum / NUMBER_OF_PER_PAGE + 1;
			}

		}
		return pagesNum;
	}
	
	public List<PictureBean> getPicture(HttpSession hs){
		PaginationProcess pp = new PaginationProcess();
		MovieBean mb = (MovieBean)hs.getAttribute("current_movie");
		String group = (String)hs.getAttribute("current_group");
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		
		if(mb != null &&  group != null){
			pictures = pp.getPicturesByPage((Integer)hs.getAttribute("current_page"), NUMBER_OF_PER_PAGE,group,mb.getMovie_id());
		}else if(mb!=null && group == null){
			pictures = pp.getPicturesByPage((Integer)hs.getAttribute("current_page"), NUMBER_OF_PER_PAGE,mb.getMovie_id());
		}else{
			pictures = pp.getPicturesByPage((Integer)hs.getAttribute("current_page"), NUMBER_OF_PER_PAGE);
		}
		
		return pictures;
	}


}
