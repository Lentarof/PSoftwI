package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

interface OrdenRepositorio extends JpaRepository<Orden, Long>  {
}
