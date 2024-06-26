package tabling.request;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import lombok.Setter;
import tabling.dto.RestaurantDTO;

@Setter
public class RestaurantRequest {
	private String urlStr;
	private Gson gson;

	private boolean openFilter; // 식당 리스트 프레임에서 영업중 필터가 걸리면 true
	private boolean likeFilter; // 식당 리스트 프레임에서 좋아요 필터가 걸리면 true

	public RestaurantRequest() {
		urlStr = "http://" + Request.getIp() + ":8080/restaurant";
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	/**
	 * 모든 식당 리스트 요청 <BR>
	 * 영업중, 좋아요 필터 여부에 따라 받는 리스트가 달라질 수 있음
	 */
	public List<RestaurantDTO> getAllRestaurants(int customerId) {
		List<RestaurantDTO> list = new ArrayList<>();
		try {
			String strUrl = urlStr + "/all/" + "customerId=" + String.valueOf(customerId) + "&" + "open=" + String.valueOf(openFilter) + "&" + "like="
					+ String.valueOf(likeFilter);
			String str = Request.getRequest(strUrl);
			Type dtoType = new TypeToken<List<RestaurantDTO>>() {
			}.getType();
			list = gson.fromJson(str, dtoType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 해당 카테고리의 식당 리스트 요청 <BR>
	 * 영업중, 좋아요 필터 여부에 따라 받는 리스트가 달라질 수 있음
	 */
	public List<RestaurantDTO> getRestaurantsByCategory(int categoryId, int customerId) {
		List<RestaurantDTO> list = new ArrayList<>();
		try {
			String strUrl = urlStr + "/category/" + "customerId=" + String.valueOf(customerId) + "&" + "categoryId=" + String.valueOf(categoryId)
					+ "&" + "open=" + String.valueOf(openFilter) + "&" + "like=" + String.valueOf(likeFilter);
			String str = Request.getRequest(strUrl);
			Type dtoType = new TypeToken<List<RestaurantDTO>>() {
			}.getType();
			list = gson.fromJson(str, dtoType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 해당 지역의 식당 리스트 요청 <BR>
	 * 영업중, 좋아요 필터 여부에 따라 받는 리스트가 달라질 수 있음
	 */
	public List<RestaurantDTO> getRestaurantsByLocation(int locationId, int customerId) {
		List<RestaurantDTO> list = new ArrayList<>();
		try {
			String strUrl = urlStr + "/location/" + "customerId=" + String.valueOf(customerId) + "&" + "locationId=" + String.valueOf(locationId)
					+ "&" + "open=" + String.valueOf(openFilter) + "&" + "like=" + String.valueOf(likeFilter);
			String str = Request.getRequest(strUrl);
			Type dtoType = new TypeToken<List<RestaurantDTO>>() {
			}.getType();
			list = gson.fromJson(str, dtoType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 해당 식당의 정보 요청
	public RestaurantDTO getRestaurantByRestaurantId(int restaurantId) {
		RestaurantDTO dto = null;
		try {
			String strUrl = urlStr + "/restaurant/" + "restaurantId=" + String.valueOf(restaurantId) + "&";
			String str = Request.getRequest(strUrl);
			dto = gson.fromJson(str, RestaurantDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}

}
