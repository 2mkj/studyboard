package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.member_info(id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("member/updateForm.jsp");
		forward.setRedirect(false);
		request.setAttribute("memberinfo", m);
		return forward;
	}

}
