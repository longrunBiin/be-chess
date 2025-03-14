package chess.game;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AiService {

    @Value("${OPENAI_API_KEY}")
    private String openaiApiKey;

    private String difficulty;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    public String[] getMove(String gameState) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 프롬프트 생성
        String prompt = generatePrompt(gameState);

        // 요청 바디 생성 (JSONObject 사용)
        JSONObject requestJson = new JSONObject();
        requestJson.put("model", "gpt-3.5-turbo");

        // Create messages as a JSONArray
        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "system").put("content", "You are a chess AI."));
        messages.put(new JSONObject().put("role", "user").put("content", prompt));

        requestJson.put("messages", messages);
        requestJson.put("max_tokens", 100);
        requestJson.put("temperature", 0.7);

        HttpEntity<String> entity = new HttpEntity<>(requestJson.toString(), headers);

        // API 요청
        ResponseEntity<String> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, String.class);

        // 응답 처리
        return parseMoveFromResponse(response.getBody());
    }

    private String generatePrompt(String gameState) {
        // 난이도에 따른 프롬프트 생성
        return "The current board state is:\n" + gameState +
                "\nMake a move according to the " + difficulty + " difficulty.\n" +
                "Provide the move only in the format 'start_position end_position' (e.g., 'e2 e4'). Do not provide any additional text.";
    }

    private String[] parseMoveFromResponse(String response) {
        JSONObject jsonResponse = new JSONObject(response);
        String move = jsonResponse.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");


        // Extract the start and end positions (e.g., "e2 e4")
        System.out.println("response = " + response);
        return move.trim().split(" ");
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
