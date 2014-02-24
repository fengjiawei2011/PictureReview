package servlet;

import helper.ProjectHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import process.LikeProcess;

/**
 * Servlet implementation class GoodOrNotServlet
 */
@WebServlet("/like")
public class GoodOrNotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public GoodOrNotServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		likeFunction(request, response);
	}

	public void likeFunction(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		System.out.println("memory");
		Map<String, Object> memory = (Map<String, Object>)request.getSession().getAttribute("memory");
		LikeProcess lp = new LikeProcess();
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		String save = request.getParameter("save");
		//System.out.println("=--->" + save);
		if (save != null && save.equals("save")) {
			lp.updateByMap(memory);
			memory.clear();
			//System.out.println("save map successfully");
			map.put("success", " save successfully!!!!!");
			//response.sendRedirect("index.jsp");
			
		}else{
			String id = request.getParameter("id");
			String isLike_p = request.getParameter("isLike");
			int like;
			
			if (isLike_p.equals("like")) {
				like = 1;
			} else {
				like = 0;
			}
			
			Boolean isLike = false;
	
			if (!memory.containsKey(id)) {
				System.out.println("no such id in memory!!");
				memory.put(id, like);
				if (like == 1) {
					isLike = true;
				} else {
					isLike = false;
				}
			} else {
				System.out.println("has such id in memory!!");
				if ((int) memory.get(id) == 1) {
					isLike = false;
					memory.remove(id);
					memory.put(id, 0);
				} else {
					isLike = true;
					memory.remove(id);
					memory.put(id, 1);
				}
			}

			map.put("isLike", isLike);
			map.put("id", id);
			//ProjectHelper.write(response, map);
		}
		
		ProjectHelper.write(response, map);

	}

}
