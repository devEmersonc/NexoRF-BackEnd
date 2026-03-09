package com.devemersonc.backend_sys_picking.service.user;

import com.devemersonc.backend_sys_picking.DTO.RoleRequest;
import com.devemersonc.backend_sys_picking.DTO.UserCreateDTO;
import com.devemersonc.backend_sys_picking.DTO.UserResponseDTO;
import com.devemersonc.backend_sys_picking.DTO.UserUpdateDTO;
import com.devemersonc.backend_sys_picking.exception.ResourceNotFoundException;
import com.devemersonc.backend_sys_picking.mapper.UserMapper;
import com.devemersonc.backend_sys_picking.model.Role;
import com.devemersonc.backend_sys_picking.model.User;
import com.devemersonc.backend_sys_picking.repository.RoleRepository;
import com.devemersonc.backend_sys_picking.repository.UserRepository;
import jakarta.transaction.Transactional;
import kotlin.collections.ArrayDeque;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTOList(users);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public UserResponseDTO getUser(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));
        return userMapper.toDTO(user);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void registerUser(UserCreateDTO userCreateDTO, RoleRequest roleRequest) throws ResourceNotFoundException{
        User existingUser = userRepository.findByRut(userCreateDTO.getRut());
        if(existingUser != null) {
            throw new ResourceNotFoundException("El rut ya está vinculado a un usuario existente.");
        }

        //Buscar el rol en DB
        List<Role> roles = roleRepository.findByName(roleRequest.getName());

        if(roles.isEmpty()) {
            throw new ResourceNotFoundException("El rol ingresado no existe.");
        }

        String roleName = roleRequest.getName();

        //Validar rol permitido
        if(!roleName.equals("ROLE_ADMIN") && !roleName.equals("ROLE_PICKER")) {
            throw new ResourceNotFoundException("Rol no permitido");
        }

        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setName(userCreateDTO.getName());
        user.setLastName(userCreateDTO.getLastName());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setRut(userCreateDTO.getRut());
        user.setRoles(roles);

        userRepository.save(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User user = findById(id);
        user.setUsername(userUpdateDTO.getUsername());
        user.setName(userUpdateDTO.getName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setRut(userUpdateDTO.getRut());
        userRepository.save(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteUser(Long user_id) {
        User user = findById(user_id);
        userRepository.deleteById(user.getId());
    }

    @Override
    public User findById(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return user;
    }
}