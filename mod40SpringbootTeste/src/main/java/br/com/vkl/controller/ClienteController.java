package br.com.vkl.controller;

import br.com.vkl.domain.Cliente;
import br.com.vkl.repository.IClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteRepository clienteRepository;

    public ClienteController(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("cliente", new Cliente());
        return "clientes";
    }


    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }


    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }


    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }
}
