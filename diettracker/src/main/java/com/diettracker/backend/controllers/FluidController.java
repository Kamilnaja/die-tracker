package com.diettracker.backend.controllers;

import com.diettracker.backend.models.Fluid;
import com.diettracker.backend.repositories.FluidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fluid")
public class FluidController {

    @Autowired
    private FluidRepository fluidRepository;

    @GetMapping
    public List<Fluid> getAllFluids() {
        return fluidRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fluid> getFluidById(@PathVariable Long id) {
        Optional<Fluid> fluid = fluidRepository.findById(id);
        return fluid.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fluid addFluid(@RequestBody Fluid fluid) {
        System.out.println("Dodano: " + fluid);
        return fluidRepository.save(fluid);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fluid> updateFluid(@PathVariable Long id, @RequestBody Fluid newFluid) {
        System.out.println("Aktualizacja: " + newFluid);

        return fluidRepository.findById(id)
                .map(fluid -> {
                    fluid.setName(newFluid.getName());
                    fluid.setVolume(newFluid.getVolume());
                    fluid.setCalories(newFluid.getCalories());
                    fluid.setImageUrl(newFluid.getImageUrl());
                    return ResponseEntity.ok(fluidRepository.save(fluid));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFluid(@PathVariable Long id) {
        if (fluidRepository.existsById(id)) {
            fluidRepository.deleteById(id);
            System.out.println("Usunięto: ID " + id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Fluid> searchFluidByName(@RequestParam String name) {
        return fluidRepository.findByNameContainingIgnoreCase(name);
    }
}
