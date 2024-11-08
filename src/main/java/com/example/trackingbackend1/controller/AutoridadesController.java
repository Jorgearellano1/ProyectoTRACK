package com.example.trackingbackend1.controller;

import com.example.trackingbackend1.dtos.UsuarioDTO;
import com.example.trackingbackend1.model.User;
import com.example.trackingbackend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/autoridades")
public class AutoridadesController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('AUTHORITY')")
    public List<UsuarioDTO> getAllAuthorities() {
        List<User> authorities = userService.getAllUsers();
        return authorities.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('AUTHORITY')")
    public ResponseEntity<UsuarioDTO> getAuthorityById(@PathVariable Long id) {
        Optional<User> authority = userService.getUserById(id);
        if (authority.isPresent()) {
            return ResponseEntity.ok(convertToDto(authority.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHORITY') or #id == authentication.principal.id")
    public ResponseEntity<UsuarioDTO> updateAuthority(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        Optional<User> existingAuthority = userService.getUserById(id);
        if (existingAuthority.isPresent()) {
            User updatedAuthority = userService.updateUser(convertToEntity(usuarioDTO, existingAuthority.get()));
            return ResponseEntity.ok(convertToDto(updatedAuthority));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAuthority(@PathVariable Long id) {
        if (userService.getUserById(id).isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private UsuarioDTO convertToDto(User user) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEnabled(user.isEnabled());
        dto.setRoles(user.getRoles().stream().map(role -> role.getName()).collect(java.util.stream.Collectors.toSet()));
        return dto;
    }

    private User convertToEntity(UsuarioDTO dto, User user) {
        user.setUsername(dto.getUsername());
        user.setEnabled(dto.isEnabled());
        return user;
    }
}
