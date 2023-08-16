package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")

    public List<Course> getPreviousCourses(@PathVariable long id) {
        String path = courseRepository.findById(id).getPath();
        List<Long> listId = getListId(path);
        List<Course> listCourses = new ArrayList<>();
        if (listId != null) {
            listCourses = listId.stream()
                    .map(this::getCourse)
                    .collect(Collectors.toList());
        }
        return listCourses;
    }
    private List<Long> getListId(String path) {
        if (path != null && !"".equals(path)) {
            return Arrays.stream(path.split("\\."))
                    .map(Long::parseLong)
                    .toList();
        }
        return null;
    }
    // END
}
