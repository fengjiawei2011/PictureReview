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
	public static Map<String, Object> memory;
	public static LikeProcess lp;

	public GoodOrNotServlet() {
		super();
		memory = new LinkedHashMap<String, Object>();
		lp = new LikeProcess();
		// for(int i = 1; i <= 100; i++ ){
		// memory.put( i+".JPG", 0);
		// }

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
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		String save = request.getParameter("save");
		//System.out.println("=--->" + save);
		if (save != null && save.equals("save")) {
			lp.updateByMap(memory);
			memory.clear();
			System.out.println("save map successfully");
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
			
			
			// lp.update(id, like);
			Boolean isLike = false;
			// System.out.println(isLike_p);
			// if(isLike_p.equals("like")){
			// isLike = true;
			//
			// }else{
			// isLike = false;
			// lp.update(id, 0);
			// }
			//
			// System.out.println("success'");

			// System.out.println("id="+ id);
			if (!memory.containsKey(id)) {
				memory.put(id, like);
				if (like == 1) {
					isLike = true;
				} else {
					isLike = false;
				}
			} else {
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

		
		
		// System.out.println("map----> "+map);
		// System.out.println("memory-> "+memory);
		

		// response.sendRedirect("main.jsp");
	}

}
