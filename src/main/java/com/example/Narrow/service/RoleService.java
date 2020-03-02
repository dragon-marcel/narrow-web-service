package com.example.Narrow.service;

import java.util.List;

import com.example.Narrow.model.Role;

public interface RoleService {
    List<Role> findAll();
    Role findById(Long id);
}
