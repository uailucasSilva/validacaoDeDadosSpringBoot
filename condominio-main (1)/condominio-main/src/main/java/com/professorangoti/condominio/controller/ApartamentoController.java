package com.professorangoti.condominio.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.professorangoti.condominio.model.Apartamento;
import com.professorangoti.condominio.repository.ApartamentoRepository;
import com.professorangoti.condominio.repository.ProprietarioRepository;

@Controller
public class ApartamentoController {

    @Autowired
    ApartamentoRepository repository;

    @Autowired
    ProprietarioRepository repository2;

    @GetMapping("cad_apto")
    public String formCadastroApartamento(Model model) {
        model.addAttribute("apartamento", new Apartamento());
        model.addAttribute("proprietarios", repository2.findAll());
        return "form_apto";
    }

    @PostMapping("cad_apto")
    public String gravaNovoApartamento(@Valid Apartamento apartamento, BindingResult validacao) {
        if (validacao.hasErrors())
            return "form_apto";
        repository.save(apartamento);
        return "redirect:/rel_apto";
    }

    @GetMapping("rel_apto")
    public String relatorio(Model model) {
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        List<Apartamento> lista = repository.findAll();
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        System.out.println("------------ tempo de execução: " + (timestamp2.getTime() - timestamp1.getTime()));
        timestamp1 = new Timestamp(System.currentTimeMillis());
        List<Apartamento> lista2 = repository.findAll2();
        timestamp2 = new Timestamp(System.currentTimeMillis());
        System.out.println("=============tempo de execução: " + (timestamp2.getTime() - timestamp1.getTime()));
        model.addAttribute("apartamentos", lista);
        return "rel_apto";
    }

}
