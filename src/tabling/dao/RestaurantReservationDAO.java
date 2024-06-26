package tabling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tabling.dto.ReservationDTO;
import tabling.dto.ReservationForRestaurantDTO;
import tabling.util.DBConnectionManager;
import tabling.util.Define;

public class RestaurantReservationDAO {

	// 해당 식당의 예약 리스트를 반환하는 메서드
	public List<ReservationDTO> getReservationByRestaurantId(int restaurantId) throws SQLException {

		List<ReservationDTO> list = new ArrayList<>();

		String query = Define.SELECT_RESERVATION_BY_RESTAURANTID;

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, restaurantId);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ReservationDTO dto = new ReservationDTO().builder().reservationId(rs.getInt("reservation_id"))
							.reservationState(rs.getString("reservation_state")).reservationTime(rs.getString("reservation_time"))
							.customerId(rs.getInt("customer_id")).restaurantId(rs.getInt("restaurant_id")).build();
					list.add(dto);
				}
				for (ReservationDTO reservationDTO : list) {
					System.out.println(reservationDTO);
				}
			}
		}
		return list;
	}

	public List<ReservationForRestaurantDTO> getCustomerInfoByReservation(int restaurantId) throws SQLException {

		List<ReservationForRestaurantDTO> list = new ArrayList<>();

		// JOIN으로 필요한 것만 띄우기 - 고객이름, 전화번호, 예약시간, 예약상태
		String query = Define.SELECT_CUSTOMER_AND_RESERVATION;

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, restaurantId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ReservationForRestaurantDTO dto2 = new ReservationForRestaurantDTO().builder().customerName(rs.getString("customer_name"))
							.customerPhone(rs.getString("phone")).reservationTime(rs.getString("reservation_time"))
							.state(rs.getString("reservation_state")).build();
					list.add(dto2);
				}

			}
		}
		return list;
	};

}
