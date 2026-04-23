package controller;

import entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repository.DoctorRepository;
import repository.PacienteRepository;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping(value = {"", "/"})
    public String listaDoctores(Model model) {
        model.addAttribute("listaDoctores", doctorRepository.findAll());
        return "doctor/list";
    }

    @GetMapping("/proximasCitas")
    public String proximasCitas(@RequestParam("id") int doctorId, Model model) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        model.addAttribute("doctor", doctor);
        model.addAttribute("pacientes", pacienteRepository.findByDoctorId(doctorId));
        return "doctor/pacientesList";
    }
}