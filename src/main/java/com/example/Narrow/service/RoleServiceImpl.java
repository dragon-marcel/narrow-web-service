package com.example.Narrow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Narrow.model.Role;
import com.example.Narrow.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
	return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
	return roleRepository.findById(id).orElse(null);
    }

}
