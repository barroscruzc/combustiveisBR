package com.distribuidorabr.Controller;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.distribuidorabr.Model.Company;
import com.distribuidorabr.Service.interfaces.CompanyServiceIntf;

import jakarta.validation.Valid;

@RestController
public class CompanyController {

	@Autowired
	private CompanyServiceIntf service;

	@GetMapping("/companies")
	public ResponseEntity<ArrayList<Company>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

	@GetMapping("/companies/id/{id}")
	public ResponseEntity<Company> findById(@PathVariable UUID id) {
		Company res = service.findById(id);
		if (res != null) {
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/companies/cnpj/{cnpj}")
	public ResponseEntity<Company> findByCnpj(@PathVariable String cnpj) {
		Company res = service.findByCnpj(cnpj);
		if (res != null) {
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/companies")
	public ResponseEntity<Company> save(@Valid @RequestBody Company company) {
		Company res = service.save(company);
		if (res != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(res);
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping("/companies")
	public ResponseEntity<Company> update(@Valid @RequestBody Company company) {
		Company res = service.update(company);
		if (res != null) {
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/companies/{id}")
	public ResponseEntity<Company> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@GetMapping("/companies/search/{corporateName}")
	public ResponseEntity<Company> findByCorporateName(@PathVariable String corporateName) {
		Company res = service.findByCorporateName(corporateName);
		if (res != null) {
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		return ResponseEntity.notFound().build();
	}

}
