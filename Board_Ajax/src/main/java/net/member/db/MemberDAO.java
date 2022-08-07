package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource ds;
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : "+ ex);
		}
	}

	public int isId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1; //DB에 해당 id가 없습니다.
		try {
			conn = ds.getConnection();

			String sql = "select id from member where id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 0; //DB에 해당 id가 있습니다.
			}
		} catch (Exception se) {
			se.printStackTrace();
		}  finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} 
		return result;
	}
	
	public int insert(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0; //초기값
		try {
			conn = ds.getConnection();
			System.out.println("getConnection : insert()");
			
			pstmt = conn.prepareStatement(
			  "INSERT INTO member (id, password, name, age, gender, email) VALUES (?,?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setInt(4, member.getAge());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getEmail());
			result = pstmt.executeUpdate();
			
		} catch(java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
			System.out.println("멤버 아이디 중복 에러입니다.");
		} catch (Exception se) {
			se.printStackTrace();
		}  finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} 
		return result;
	}

	public int isId(String id, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1; //아이디가 존재하지 않습니다.
		try {
			conn = ds.getConnection();

			String sql = "select id, password from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(2).equals(pass)) {
					result = 1; //아이디와 비밀번호 일치하는 경우
				} else {
					result = 0; //비밀번호가 일치하지 않는 경우
				}
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null)
					conn.close(); 
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} 
		return result;
	}

	public Member member_info(String id) {
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			conn = ds.getConnection();
			
			String sql = "select * from member where id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setId(rs.getString(1));
				m.setPassword(rs.getString(2));
				m.setName(rs.getString(3));
				m.setAge(rs.getInt(4));
				m.setGender(rs.getString(5));
				m.setEmail(rs.getString(6));
				m.setMemberfile(rs.getString(7));
			}
		} catch (Exception se) {
			se.printStackTrace();
		}  finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} 
		return m;
	}

	public int update(Member m) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update member "
				   + "set name=?, age=?, gender=?, email=?, memberfile=? "
				   + "where id=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setInt(2, m.getAge());
			pstmt.setString(3, m.getGender());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getMemberfile());
			pstmt.setString(6, m.getId());
			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getDetail() 에러 : " + ex);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from member where id != 'admin'");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return x;
	}

	public List<Member> getList(int page, int limit) {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			
			String sql = "select * "
					+ "   from (select b.*, rownum rnum  "
					+ "    	    from (select * from member "
					+ "               where id != 'admin'"
					+ "      		  order by id) b"
					+ "			) "
					+ " where rnum>=? and rnum<=?";		
			pstmt = conn.prepareStatement(sql);
			int startrow = (page - 1) * limit + 1;  // 읽기 시작할 row 번호(1 11 21 31 ...)
			int endrow = startrow + limit - 1; 		// 읽을 마지막 row 번호(10 20 30 40 ...)
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			Member m = new Member();
			m.setId(rs.getString("id"));
			m.setPassword(rs.getString(2));
			m.setName(rs.getString(3));
			m.setAge(rs.getInt(4));
			m.setGender(rs.getString(5));
			m.setEmail(rs.getString(6));
			list.add(m); // 값을 담은 객체를 리스트에 저장합니다.
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getBoardList() 에러 : " + ex);
		} finally {
			try {
				if (rs != null)
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (pstmt != null)
				pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (conn != null)
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			}
		return list;
	}

	public int getListCount(String field, String value) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from member "
					   + "	where id != 'admin' "
					   + " and " + field + " like ?"; //and name like '%a%'
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%"); //'%a%'
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return x;
	}

	public List<Member> getList(String field, String value, int page, int limit) {
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			
			String sql = "select * "
					+ "   from (select b.*, rownum rnum  "
					+ "    	    from (select * from member "
					+ "               where id != 'admin' "
					+ "				  and " + field + " like ? "
					+ "      		  order by id) b"
					+ "			) "
					+ " where rnum between ? and ? ";		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			
			int startrow = (page - 1) * limit + 1;  // 읽기 시작할 row 번호(1 11 21 31 ...)
			int endrow = startrow + limit - 1; 		// 읽을 마지막 row 번호(10 20 30 40 ...)
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			Member m = new Member();
			m.setId(rs.getString("id"));
			//m.setPassword(rs.getString(2));
			m.setName(rs.getString(3));
			//m.setAge(rs.getInt(4));
			//m.setGender(rs.getString(5));
			//m.setEmail(rs.getString(6));
			list.add(m); // 값을 담은 객체를 리스트에 저장합니다.
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getBoardList() 에러 : " + ex);
		} finally {
			try {
				if (rs != null)
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (pstmt != null)
				pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (conn != null)
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			}
		return list;
	}

	public int delete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "delete from member "
				   + "where id=? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}
