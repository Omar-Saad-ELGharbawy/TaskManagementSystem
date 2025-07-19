package omar.HyperCell.intern.task2.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import omar.HyperCell.intern.task2.exception.AppException;
import omar.HyperCell.intern.task2.exception.ErrorCode;
import omar.HyperCell.intern.task2.exception.ValidationException;
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

    public List<Project> getAllProjects()  {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) throws ValidationException, EntityNotFoundException {
        if (id == null) {
            throw new ValidationException(ErrorCode.INVALID_PROJECT_ID);
        }
        else if(id <= 0 ||  id > projectRepository.count() ) {
            throw new ValidationException(ErrorCode.OUT_OF_RANGE_TASK_ID);
        }

        return projectRepository.findById(id)
                .orElseThrow(() -> new ValidationException(ErrorCode.INVALID_PROJECT_ID));
    }

    public Project createProject(ProjectDto dto) throws AppException {
        if (dto.title() == null || dto.title().isEmpty()) {
            throw new ValidationException(ErrorCode.INVALID_PROJECT_TITLE);
        }
        else if (dto.description() == null || dto.description().isEmpty()) {
            throw new ValidationException(ErrorCode.INVALID_PROJECT_DESCRIPTION);
        }
        Project project = new Project();
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, ProjectDto dto) throws AppException {
        if (id == null) {
            throw new ValidationException(ErrorCode.INVALID_PROJECT_ID);
        }
        else if(id <= 0 ||  id > projectRepository.count() ) {
            throw new ValidationException(ErrorCode.OUT_OF_RANGE_PROJECT_ID);
        }

        if (dto.title() == null || dto.title().isEmpty()) {
            throw new ValidationException(ErrorCode.INVALID_PROJECT_TITLE);
        }
        else if (dto.description() == null || dto.description().isEmpty()) {
            throw new ValidationException(ErrorCode.INVALID_PROJECT_DESCRIPTION);
        }
        Project project = getProjectById(id);
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) throws ValidationException {
        if (id == null) {
            throw new ValidationException(ErrorCode.INVALID_PROJECT_ID);
        }
        else if(id <= 0 ||  id > projectRepository.count() ) {
            throw new ValidationException(ErrorCode.OUT_OF_RANGE_PROJECT_ID);
        }

        projectRepository.deleteById(id);
    }


}
