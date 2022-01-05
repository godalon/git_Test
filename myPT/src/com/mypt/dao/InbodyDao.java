package com.mypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mypt.connection.DBConnection;
import com.mypt.dto.InbodyDto;
import com.mypt.dto.UserDto;

public class InbodyDao {
	private DBConnection db;

//	dao 싱글톤
	private static InbodyDao instance = new InbodyDao();

	public static InbodyDao getInstance() {
		return instance;
	}

	private InbodyDao() {
		db = DBConnection.getInstance();
	}
	

	public void insertInbody(InbodyDto inbodyBean) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "insert inbody(i_id,i_date,muscle,fat,height,weight) " + "values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, inbodyBean.getI_id());
			ps.setTimestamp(2, inbodyBean.getI_date());
			ps.setFloat(3, inbodyBean.getMuscle());
			ps.setFloat(4, inbodyBean.getFat());
			ps.setFloat(4, inbodyBean.getHeight());
			ps.setFloat(4, inbodyBean.getWeight());

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}

//	회원 정보+인바디 정보 가져오기, limit 10 (이)
//	public ArrayList<UserDto> getUserInbodyList(String id) {
	public JsonArray getUserInbodyList(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserDto user = null;
		InbodyDto inbody = null;

		Gson gson = new Gson();

//		ArrayList<UserDto> arr = new ArrayList<>();
		JsonArray jarr = new JsonArray();
		JsonObject obj = null;

		StringBuffer sb = new StringBuffer(200);
		sb.append("SELECT u.id, u.name, u.birth, u.gender, i.height, i.weight, i.fat, i.muscle, i.i_date ");
		sb.append("FROM user AS u ");
		sb.append("JOIN inbody AS i ");
		sb.append("ON u.id = i.i_id ");
		sb.append("WHERE u.id=? ");
		sb.append("ORDER BY i.i_date DESC LIMIT 10");

		String sql = sb.toString();

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				user = new UserDto();
				inbody = new InbodyDto();

				user.setId(rs.getString(1));
				user.setName(rs.getString(2));

				user.setBirth(rs.getString(3));

				user.setGender(rs.getString(4));

				inbody.setHeight(rs.getFloat(5));
				inbody.setWeight(rs.getFloat(6));
				inbody.setFat(rs.getFloat(7));
				inbody.setMuscle(rs.getFloat(8));
				inbody.setStrDate(rs.getTimestamp(9).toString());

				user.setInbody(inbody);

				String aa = gson.toJson(user);
				obj = gson.fromJson(aa, JsonObject.class);

				jarr.add(obj);

			}
			System.out.println(jarr);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

//		return arr;
		return jarr;
	}

	public InbodyDto getInbody(String i_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		InbodyDto inbodyBean = new InbodyDto();

		try {
			con = db.getConnection();
			sql = "select * from inbody where i_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, i_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				inbodyBean.setI_id(rs.getString("i_id"));
				inbodyBean.setI_date(rs.getTimestamp("i_date"));
				inbodyBean.setMuscle(rs.getFloat("muscle"));
				inbodyBean.setFat(rs.getFloat("fat"));
				inbodyBean.setHeight(rs.getFloat("height"));
				inbodyBean.setWeight(rs.getFloat("weight"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return inbodyBean;
	}

	public void updateInbody(InbodyDto inbodyBean) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "update inbody set i_date=?,muscle=?,fat=?,height=?,weight=?" + " where i_id=?";
			ps = con.prepareStatement(sql);

			ps.setTimestamp(1, inbodyBean.getI_date());
			ps.setFloat(2, inbodyBean.getMuscle());
			ps.setFloat(3, inbodyBean.getFat());
			ps.setFloat(4, inbodyBean.getHeight());
			ps.setFloat(5, inbodyBean.getWeight());
			ps.setString(6, inbodyBean.getI_id());

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}

	}

	public void deleteInbody(String i_id) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
 
		try {
			con = db.getConnection();
			sql = "delete from inbody where i_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, i_id);

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}
}
