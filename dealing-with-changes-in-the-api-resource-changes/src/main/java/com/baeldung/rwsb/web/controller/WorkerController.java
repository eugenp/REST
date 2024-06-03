package com.baeldung.rwsb.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.baeldung.rwsb.domain.model.Worker;
import com.baeldung.rwsb.service.WorkerService;
import com.baeldung.rwsb.web.dto.NewWorkerDto;
import com.baeldung.rwsb.web.dto.OldWorkerDto;
import com.baeldung.rwsb.web.dto.NewWorkerDto.WorkerUpdateValidationData;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

    private WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping(value = "/{id}")
    public OldWorkerDto findOne(@PathVariable Long id) {
        Worker model = workerService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return OldWorkerDto.Mapper.toDto(model);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OldWorkerDto create(@RequestBody @Valid OldWorkerDto newWorker) {
        Worker model = OldWorkerDto.Mapper.toModel(newWorker);
        Worker createdModel = this.workerService.save(model);
        return OldWorkerDto.Mapper.toDto(createdModel);
    }

    @PutMapping(value = "/{id}")
    public OldWorkerDto update(@PathVariable Long id, @RequestBody @Validated(WorkerUpdateValidationData.class) OldWorkerDto updatedWorker) {
        Worker model = OldWorkerDto.Mapper.toModel(updatedWorker);
        Worker createdModel = this.workerService.updateWorker(id, model)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return OldWorkerDto.Mapper.toDto(createdModel);
    }

    @GetMapping(value = "/{id}", produces = "application/vnd.baeldung.new-worker+json")
    public NewWorkerDto findOneNewStructure(@PathVariable Long id) {
        Worker model = workerService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return NewWorkerDto.Mapper.toDto(model);
    }

    @PostMapping(produces = "application/vnd.baeldung.new-worker+json", consumes = "application/vnd.baeldung.new-worker+json")
    @ResponseStatus(HttpStatus.CREATED)
    public NewWorkerDto createNewStructure(@RequestBody @Valid NewWorkerDto newWorker) {
        Worker model = NewWorkerDto.Mapper.toModel(newWorker);
        Worker createdModel = this.workerService.save(model);
        return NewWorkerDto.Mapper.toDto(createdModel);
    }

    @PutMapping(value = "/{id}", produces = "application/vnd.baeldung.new-worker+json", consumes = "application/vnd.baeldung.new-worker+json")
    public NewWorkerDto updateNewStructure(@PathVariable Long id, @RequestBody @Validated(WorkerUpdateValidationData.class) NewWorkerDto updatedWorker) {
        Worker model = NewWorkerDto.Mapper.toModel(updatedWorker);
        Worker createdModel = this.workerService.updateWorker(id, model)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return NewWorkerDto.Mapper.toDto(createdModel);
    }
}