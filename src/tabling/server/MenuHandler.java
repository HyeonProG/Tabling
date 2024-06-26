package tabling.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import tabling.dao.MenuDAO;
import tabling.dto.MenuDTO;

public class MenuHandler implements HttpHandler {

	private MenuDAO dao;
	private Gson gson;

	public MenuHandler() {
		dao = new MenuDAO();
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String method = exchange.getRequestMethod();
		if ("GET".equalsIgnoreCase(method)) {
			// GET 요청시 여기서 동작
			handleGetRequest(exchange);
		} else {
			// GET이 아닌 요청시
		}
	}

	// GET 요청시 동작
	private void handleGetRequest(HttpExchange exchange) {
		URI uri = exchange.getRequestURI();
		String path = uri.getPath();
		String response = null;
		String[] pathSegments = path.split("/");
		if (pathSegments.length >= 4) {
			String type = pathSegments[2];
			String query = pathSegments[3];
			try {
				// 키값에 대한 벨류값 초기화
				int foodId = 0;
				int restaurantId = 0;
				String[] pairs = query.split("&");
				for (String pair : pairs) {
					String[] keyValue = pair.split("=");
					if (keyValue[0].equalsIgnoreCase("foodId")) {
						foodId = Integer.parseInt(keyValue[1]);
					} else if (keyValue[0].equalsIgnoreCase("restaurantId")) {
						restaurantId = Integer.parseInt(keyValue[1]);
					}
				}
				// 음식 이름 요청 응답
				if (type.equalsIgnoreCase("food")) {
					response = dao.getFoodName(foodId);
					// 메뉴 리스트 요청 응답
				} else if (type.equalsIgnoreCase("restaurant")) {
					List<MenuDTO> list = dao.getMenuByRestaurantId(restaurantId);
					response = gson.toJson(list);
				}
				try {
					byte[] bytes = response.getBytes();
					exchange.setAttribute("Content-Type", "text/plain; charset=UTF-8");
					exchange.sendResponseHeaders(200, bytes.length);
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(exchange.getResponseBody()));
					writer.write(response);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
