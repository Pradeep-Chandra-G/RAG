package app.springai.RAG.web.controller;

import app.springai.RAG.web.model.Message;
import app.springai.RAG.web.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/chat")
    public String chatPage(Model model, @RequestParam String sender, @RequestParam String receiver) {
        List<Message> messages = chatService.getMessages(sender, receiver);
        model.addAttribute("messages", messages);
        model.addAttribute("sender", sender);
        model.addAttribute("receiver", receiver);
        return "chat";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String sender, @RequestParam String receiver, @RequestParam String content) {
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        chatService.saveMessage(message);
        return "redirect:/chat?sender=" + sender + "&receiver=" + receiver;
    }
}

