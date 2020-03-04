package _03_personPage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_init.util.DBService;
import _03_personPage.dao.MemberDao;
import _03_personPage.model.MemberBean;

// 本類別使用為標準的JDBC技術來存取資料庫。
public class MemberDaoImpl_Jdbc implements MemberDao {

	private DataSource ds = null;
	private Connection conn = null;

	public MemberDaoImpl_Jdbc() {
		System.out.println("DBService.JNDI_DB_NAME=" + DBService.nameMy);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.nameMy);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#建構子發生例外: " + ex.getMessage());
		}
	}

	// 儲存MemberBean物件，將參數mb新增到Memberinfo表格內。
	public int saveMember(MemberBean mb) {
		String sql = "INSERT INTO Memberinfo " + " (user_id, user_name, user_password, user_gender, user_birthday, "
				+ " user_email, user_phone, user_address, fileName, user_picture, user_create_time,user_status,user_permission)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int n = 0;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, mb.getId());
			ps.setString(2, mb.getName());
			ps.setString(3, mb.getPassword());
			ps.setString(4, mb.getGender());
			ps.setDate(5, mb.getBirthday());
			ps.setString(6, mb.getEmail());
			ps.setString(7, mb.getCellphone());
			ps.setString(8, mb.getAddress());
			ps.setString(9, mb.getFileName());
			ps.setBlob(10, mb.getPicture());
			ps.setTimestamp(11, mb.getCreateTime());
			ps.setString(12, mb.getStatus());
			ps.setString(13, mb.getPermission());

			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			//需要再度丟出例外給使用這個程式的Sevlet知道
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#saveMember()發生例外: " + ex.getMessage());
		}
		return n;
	}

	// 更新MemberBean物件，將參數mb新增到Memberinfo表格內。
	@Override
	public int updateMember(MemberBean mb) {
		String sql = "UPDATE Memberinfo "
				+ " SET user_email= ?, user_phone= ?, user_address= ?, fileName= ?,user_picture= ?" + " WHERE user_id= ?";
		int n = 0;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, mb.getEmail());
			ps.setString(2, mb.getCellphone());
			ps.setString(3, mb.getAddress());
			ps.setString(4, mb.getFileName());
			ps.setBlob(5, mb.getPicture());
			ps.setInt(6, mb.getId());

			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#updateMember()發生例外: " + ex.getMessage());
		}
		return n;
	}

	// 判斷參數id(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		String sql = "SELECT * FROM Memberinfo WHERE memberID = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					exist = true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#idExists()發生例外: " + ex.getMessage());
		}
		return exist;
	}

	// 由參數 id (會員帳號) 到Memberinfo表格中 取得某個會員的所有資料，傳回值為一個MemberBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
	@Override
	public MemberBean queryMember(String id) {
		MemberBean mb = null;
		String sql = "SELECT * FROM Memberinfo WHERE user_id = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MemberBean();
					mb.setId(rs.getInt("user_id"));
					mb.setName(rs.getString("user_name"));
					mb.setPassword(rs.getString("user_password"));
					mb.setGender(rs.getString("user_gender"));
					mb.setBirthday(rs.getDate("user_birthday"));
					mb.setEmail(rs.getString("user_email"));
					mb.setCellphone(rs.getString("user_phone"));
					mb.setAddress(rs.getString("user_address"));
					mb.setFileName(rs.getString("fileName"));
					mb.setPicture(rs.getBlob("user_picture"));
					mb.setCreateTime(rs.getTimestamp("user_create_time"));
					mb.setStatus(rs.getString("user_status"));
					mb.setPermission(rs.getString("user_permission"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#queryMember()發生例外: " + ex.getMessage());
		}
		return mb;
	}

	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@Override
	public MemberBean checkIdPassword(String userId, String password) {
		MemberBean mb = null;
		String sql = "SELECT * FROM Memberinfo m WHERE m.memberId = ? and m.password = ?";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, userId);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MemberBean();
					mb.setId(rs.getInt("user_id"));
					mb.setName(rs.getString("user_name"));
					mb.setPassword(rs.getString("user_password"));
					mb.setGender(rs.getString("user_gender"));
					mb.setBirthday(rs.getDate("user_birthday"));
					mb.setEmail(rs.getString("user_email"));
					mb.setCellphone(rs.getString("user_phone"));
					mb.setAddress(rs.getString("user_address"));
					mb.setFileName(rs.getString("fileName"));
					mb.setPicture(rs.getBlob("user_picture"));
					mb.setCreateTime(rs.getTimestamp("user_create_time"));
					mb.setStatus(rs.getString("user_status"));
					mb.setPermission(rs.getString("user_permission"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#checkIDPassword()發生SQL例外: " + ex.getMessage());
		}
		return mb;
	}
	/*
	 * 功能：更新客戶的未付款訂購金額。 說明：處理客戶訂單時，[訂單的總金額 + 該客戶的未付款餘額]不能超過限額， 此限額定義在
	 * GlobalService類別的常數: ORDER_AMOUNT_LIMIT 步驟： 1. 取出Member表格內的
	 * Member#unpaid_amount欄位(未付款餘額) 2. unpaid_amount加上本訂單的總金額後，檢查該數值是否超過限額
	 * (GlobalService.ORDER_AMOUNT_LIMIT)。 如果超過限額， 則 該訂單不予處裡，
	 * 丟出UnpaidOrderAmountExceedingException， 否則更新Member表格的unpaid_amount欄位:
	 * Member#unpaid_amount += currentAmount;
	 */

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
}
