package omar.HyperCell.intern.task2.controller;


import lombok.RequiredArgsConstructor;
import omar.HyperCell.intern.task2.exception.AppException;
import omar.HyperCell.intern.task2.exception.ValidationException;
import omar.HyperCell.intern.task2.model.db.Project;
import omar.HyperCell.intern.task2.model.dto.ProjectDto;
import omar.HyperCell.intern.task2.service.ProjectService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) throws ValidationException {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto dto) throws AppException {
        Project created = projectService.createProject(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody ProjectDto dto) throws AppException {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) throws ValidationException {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

}
