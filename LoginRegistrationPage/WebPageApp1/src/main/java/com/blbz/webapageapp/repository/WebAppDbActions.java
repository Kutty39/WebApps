package com.blbz.webapageapp.repository;

import static com.blbz.webapageapp.model.WebPageAppModel.insertQryBuilder;
import static com.blbz.webapageapp.model.WebPageAppModel.selectQryBuilder;
import static com.blbz.webapageapp.model.WebPageAppModel.updateQryBuilder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.blbz.webapageapp.service.WebAppDb;

public class WebAppDbActions implements WebAppDb {
	protected static final Logger log = Logger.getLogger(WebAppDbActions.class.getName());
	protected final String username = "appuser";
	protected final String password = "Appuser@2019";
	protected final String serverwithport = "localhost:3306";
	protected final String db = "webappDB";
	protected final String driveEngin = "mysql";

	@Override
	public int updateUSer(HttpServletRequest request) {
		log.info("updateuer - Enter");
		String usr = (String) request.getSession(false).getAttribute("user");
		String pas = (String) request.getParameter("pas");
		if (userValidation(usr, pas)) {
			String sql = updateQryBuilder().setTablename("user_info")
					.setFielddata("user_fname", (String) request.getParameter("fname"))
					.setFielddata("user_lname", (String) request.getParameter("lname"))
					.setFielddata("user_address", (String) request.getParameter("adrs"))
					.setFielddata("user_phn", (String) request.getParameter("phn"))
					.setWhere("user_email", usr).build();
			log.info("updateUser - Qry :" + sql);
			try (PreparedStatement stm = getConnect().prepareStatement(sql)) {
				int i = stm.executeUpdate();
				if (i > 0) {
					userValidation(usr, pas, request.getSession(false));
				}
				log.info("updateuer - Exit");
				return i;
			} catch (SQLException e) {
				log.info("updateUser - Qry error" + e.getMessage());
			}
		}
		log.info("updateuer - Exit0");
		return 0;
	}

	@Override
	public boolean checkStatement(String email) {
		String qry = selectQryBuilder().setColumname("user_email").setTablename("user_info")
				.setWhere("user_email", "=", email).build();
		log.info("checkStatement - Qry :" + qry);
		try (PreparedStatement stm = getConnect().prepareStatement(qry)) {
			ResultSet rs = stm.executeQuery();
			log.info("checkStatement - qry executed");
			return rs.next();
		} catch (SQLException e) {
			log.info("checkStatement - " + e.getMessage());
		}
		return false;
	}

	@Override
	public int registerUSer(HttpServletRequest request) {
		String sql = insertQryBuilder().setTablename("user_info")
				.setColumname("user_fname", (String) request.getParameter("fname"))
				.setColumname("user_lname", (String) request.getParameter("lname"))
				.setColumname("user_address", (String) request.getParameter("adrs"))
				.setColumname("user_phn", (String) request.getParameter("phn"))
				.setColumname("user_pass", encription((String) request.getParameter("pas")))
				.setColumname("user_email", (String) request.getParameter("eid")).build();
		log.info("registerUSer - Qry :" + sql);
		try (PreparedStatement stm = getConnect().prepareStatement(sql)) {
			log.info(stm.toString());
			return stm.executeUpdate();
		} catch (SQLException e) {
			log.info("registerUSer - Qry error" + e.getMessage());
		}
		return 0;
	}

	private Connection getConnect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:" + this.driveEngin + "://" + this.serverwithport + "/" + this.db,
					this.username, this.password);
			log.info("connection made");
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e.getMessage());
		}
		return con;
	}

	private String encription(String s) {
		try {
			byte[] b = MessageDigest.getInstance("SHA1").digest(s.getBytes());
			BigInteger number = new BigInteger(1, b);
			StringBuilder hexString = new StringBuilder(number.toString(16));
			s = hexString.toString();
			if (hexString.length() < 32) {
				s = "0".repeat(32 - hexString.length()) + s;
			}
			return s;
		} catch (NoSuchAlgorithmException e) {
			log.info("registerUSer - encr - error " + e.getMessage());
			return null;
		}

	}

	public boolean userValidation(String username, String pas) {
		String qry = selectQryBuilder().setColumname("user_pass").setWhere("user_email", "=", username)
				.setTablename("user_info").build();
		try (PreparedStatement stm = getConnect().prepareStatement(qry)) {
			ResultSet rs = stm.executeQuery();
			log.info("userValidation - qry executed");
			if (rs.next()) {
				return rs.getString("user_pass").equals(encription(pas));
			}
		} catch (SQLException e) {
			log.info("userValidation - " + e.getMessage());
		}
		return false;
	}

	@Override
	public HttpSession userValidation(String username, String pas, HttpSession session) {
		String qry = selectQryBuilder().setColumname("*").setWhere("user_email", "=", username)
				.setTablename("user_info").build();
		log.info("checkStatement - Qry :" + qry);
		try (PreparedStatement stm = getConnect().prepareStatement(qry)) {
			ResultSet rs = stm.executeQuery();
			log.info("checkStatement - qry executed");
			if (rs.next()) {
				if (rs.getString("user_pass").equals(encription(pas))) {
					HashMap<String, String> detail = new HashMap<>();
					session.setAttribute("user", rs.getString("user_email"));
					detail.put("firstname", rs.getString("user_fname"));
					detail.put("lastname", rs.getString("user_lname"));
					detail.put("address", rs.getString("user_address"));
					detail.put("phone", rs.getString("user_phn"));
					detail.put("email", rs.getString("user_email"));
					session.setAttribute("detail", detail);
					return session;
				}
			}
		} catch (SQLException e) {
			log.info("checkStatement - " + e.getMessage());
		}
		return session;
	}
}
