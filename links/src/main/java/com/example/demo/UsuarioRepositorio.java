package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}