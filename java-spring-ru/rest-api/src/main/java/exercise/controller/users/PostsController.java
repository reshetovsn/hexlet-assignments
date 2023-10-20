package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> show(@PathVariable String id) {
        return posts.stream()
                .filter(p -> p.getUserId() == Integer.parseInt(id))
                .toList();
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> create(
            @PathVariable String id,
            @RequestBody Post data) {
        var post = new Post();
        post.setSlug(data.getSlug());
        post.setTitle(data.getTitle());
        post.setBody(data.getBody());
        post.setUserId(Integer.parseInt(id));
        posts.add(post);
        return ResponseEntity.status(201)
                .body(post);
    }
}
// END
