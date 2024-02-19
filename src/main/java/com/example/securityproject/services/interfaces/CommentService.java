package com.example.securityproject.services.interfaces;

import com.example.securityproject.Entity.Comment;
import com.example.securityproject.dto.CommentDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

import java.util.List;

public interface CommentService {
    public List<CommentDto> getAll();
    public Comment findById(Long id) throws EntityNotFoundException;
    public CommentDto save(CommentDto comment) throws ValidationException;
    public void delete(Long id);
}
