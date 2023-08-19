package exercise.controller;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Optional;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable long postId, @PathVariable long commentId) {
        return commentRepository.findByIdAndPostId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }
    @PostMapping(path = "/{postId}/comments")
    public Iterable<Comment> createComment(@PathVariable long postId, @RequestBody Comment comment) {

        comment.setPost(postRepository.findById(postId).get());
        commentRepository.save(comment);
        return commentRepository.findAllByPostId(postId);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable long postId,
                                 @PathVariable long commentId,
                                 @RequestBody Comment newComment) {

        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        comment.setContent(newComment.getContent());
        return commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {

        Comment comment = commentRepository.findByIdAndPostId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        commentRepository.delete(comment);
    }
    // END
}
