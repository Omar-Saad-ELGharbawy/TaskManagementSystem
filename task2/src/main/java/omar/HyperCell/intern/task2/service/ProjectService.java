package omar.HyperCell.intern.task2.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import omar.HyperCell.intern.task2.model.db.Project;
import omar.HyperCell.intern.task2.model.dto.ProjectDto;
import omar.HyperCell.intern.task2.repositry.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID " + id));
    }

//    public Project getProjectById(Long id) {
//        Optional<Project> result = projectRepository.findById(id);
//        Project project = null;
//        if(result.isPresent()){
//            project = result.get();
//        }
//        else{
//            throw new EntityNotFoundException("Project not found with ID " + id);
//        }
//        return project;
//    }

    public Project createProject(ProjectDto dto) {
        Project project = new Project();
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, ProjectDto dto) {
        Project project = getProjectById(id);
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }


}
