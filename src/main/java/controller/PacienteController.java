package controller;

import entity.Paciente;
import entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.PacienteRepository;
import repository.DoctorRepository;

import java.util.List;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping(value = {"", "/"})
    public String listaPacientes(Model model) {
        model.addAttribute("listaPacientes", pacienteRepository.findAll());
        return "paciente/list";
    }

    @GetMapping("/derivar")
    public String derivarPacientes(Model model) {
        model.addAttribute("listaDoctores", doctorRepository.findAll());
        return "paciente/derivar";
    }

    @PostMapping("/guardarDerivacion")
    public String guardarDerivacion(@RequestParam("doctorOrigen") int doctorOrigen,
                                    @RequestParam("doctorDestino") int doctorDestino,
                                    Model model) {

        List<Paciente> pacientes = pacienteRepository.findByDoctorId(doctorOrigen);
        Doctor doctor = doctorRepository.findById(doctorDestino).orElse(null);

        if (doctor != null) {
            for (Paciente p : pacientes) {
                p.setDoctor(doctor);
                pacienteRepository.save(p);
            }
        }

        return "redirect:/paciente";
    }
}