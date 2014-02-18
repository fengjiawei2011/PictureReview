package process;

import java.util.List;

import beans.PictureBean;
import dao.PicDao;

public class PaginationProcess {
	PicDao p_dao = new PicDao();
	public List<PictureBean> getPicturesByPage(int whichPage, int pages, String group){
		
		int begin = pages * (whichPage - 1);
		return p_dao.getPageEntries(begin, pages, group);
	}
	
	public int getRecords(String group){
		return  p_dao.getRecords(group);
	}

}
