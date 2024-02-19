package com.example.securityproject.services;

import com.example.securityproject.Entity.Comment;
import com.example.securityproject.dto.CommentDto;
import com.example.securityproject.repositories.CommentRepository;
import com.example.securityproject.services.interfaces.CommentService;
import com.example.securityproject.services.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;


    public List<CommentDto> getAll(){
        return this.commentRepository.findAll()
                .stream().map(
                    c-> new CommentDto(c.getId() ,c.getComment(), c.getLikes())
                ).collect(Collectors.toList());
    }

    public Comment findById(Long id) throws EntityNotFoundException {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if(!comment.isPresent()) throw new EntityNotFoundException("Comment not found !");
        else return comment.get();
    }

    public CommentDto save(CommentDto commentDto) throws ValidationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Comment comment = new Comment().builder()
                          .comment(commentDto.getComment())
                          .likes(commentDto.getLikes())
                          .user(this.userService.findUserByUsername(authentication.getName()))
                          .build();
        if(commentDto.getId()!=null) comment.setId(commentDto.getId());
        this.commentRepository.save(comment);
        return commentDto;
    }

    public void delete(Long id) {
        Comment comment = this.findById(id);
        this.commentRepository.delete(comment);
    }
}
