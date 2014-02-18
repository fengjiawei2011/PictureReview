package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import process.PaginationProcess;
import beans.PictureBean;
import dao.PicDao;

/**
 * Servlet implementation class GetImagesServlet
 */
@WebServlet("/show")
public class GetImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final int NUMBER_OF_PER_PAGE = 20;
	int records_number, pages_number, current_page;
	int group_current;
	PaginationProcess pp = new PaginationProcess();

	public GetImagesServlet() {
		super();
		current_page = 1;
		group_current= 1;
		setPageNum(group_current+"");
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
		String group = request.getParameter("group");
		

		if (group == null) {
			group = group_current + "";
		}else{
			if(Integer.parseInt(group) != group_current){
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

		List<PictureBean> pictures = pp.getPicturesByPage(current_page,
				NUMBER_OF_PER_PAGE, group);
		HttpSession hs = request.getSession();
		hs.setAttribute("pictures", pictures);
		// request.getRequestDispatcher("main.jsp?currentPage="+current_page+"&pages="+pages_number).forward(request,
		// response);
		response.sendRedirect("main.jsp?currentPage=" + current_page
				+ "&pages=" + pages_number + "&group=Group " + group);
	}
	
	public void setPageNum(String g){
		records_number = pp.getRecords(g);
		if (records_number < NUMBER_OF_PER_PAGE) {
			pages_number = 1;
		}else {
			if(records_number % NUMBER_OF_PER_PAGE == 0){
				pages_number = records_number / NUMBER_OF_PER_PAGE;
			}else{
				pages_number = records_number / NUMBER_OF_PER_PAGE + 1;
			}
			
		}
	}

}
