package com.example.demo.controller.admin;

import java.util.List;

import com.example.demo.model.Messages;
import com.example.demo.repository.MessageRepo;
import com.example.demo.security.jwt.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private MessageRepo messageR;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping
    public List<Messages> getAllMessages() {
        return messageR.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Messages> getMessageById(@PathVariable long id) {
        Messages messages = messageR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not exist with id:" + id,
                        "Message not exist with id:" + id));
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public Messages createMessage(@RequestBody Messages message) {
        return messageR.save(message);
    }

    @PutMapping("{id}")
    public ResponseEntity<Messages> updateMessage(@PathVariable long id, @RequestBody Messages messageDetails) {
        Messages updateMessage = messageR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not exist with id:" + id,
                        "Message not exist with id:" + id));

        updateMessage.setNomUser(messageDetails.getNomUser());
        updateMessage.setMessage(messageDetails.getMessage());
        updateMessage.setDateDeclar(messageDetails.getDateDeclar());

        messageR.save(updateMessage);

        return ResponseEntity.ok(updateMessage);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMessage(@PathVariable long id) {

        Messages message = messageR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not exist with id:" + id,
                        "Message not exist with id:" + id));

        messageR.delete(message);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
