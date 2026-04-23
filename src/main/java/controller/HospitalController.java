package controller;

import entity.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repository.DoctorRepository;
import repository.HospitalRepository;
import repository.PacienteRepository;

@Controller
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping(value = {"", "/"})
    public String listaHospitales(Model model) {
        model.addAttribute("listaHospitales", hospitalRepository.findAll());
        return "hospital/list";
    }

    @GetMapping("/mostrarDoctores")
    public String mostrarDoctores(@RequestParam("id") int hospitalId, Model model) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
        model.addAttribute("hospital", hospital);
        model.addAttribute("doctores", doctorRepository.findByHospitalId(hospitalId));
        return "hospital/doctoresList";
    }

    @GetMapping("/mostrarPacientes")
    public String mostrarPacientes(@RequestParam("id") int hospitalId, Model model) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
        model.addAttribute("hospital", hospital);
        model.addAttribute("pacientes", pacienteRepository.findByHospitalId(hospitalId));
        return "hospital/pacientesList";
    }
}