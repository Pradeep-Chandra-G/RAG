package app.springai.RAG.controller;

import app.springai.RAG.service.AIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    @Autowired
    AIService aiService;

    @Autowired
    ChatClient chatClient;

    @PostMapping("/response")
    public String getResponse(@RequestParam String message){
        VectorStore vectorStore = aiService.addDataInVectorStore();

        return chatClient.prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
                .user(message)
                .call()
                .content();
    }


}
