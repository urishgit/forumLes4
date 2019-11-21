package telran.forum.service;

import java.util.List;

import telran.forum.dto.CommentDto;
import telran.forum.dto.DatePeriodDto;
import telran.forum.dto.NewCommentDto;
import telran.forum.dto.NewPostDto;
import telran.forum.dto.PostDto;

public interface ForumService {
	PostDto addNewPost(NewPostDto newPost, String author);

	PostDto getPost(String id);

	PostDto removePost(String id);

	PostDto updatePost(NewPostDto postUpdateDto, String id);

	boolean addLike(String id);

	PostDto addComment(String id, String author, NewCommentDto newCommentDto);

	Iterable<PostDto> findPostsByAuthor(String author);
	
	Iterable<PostDto> findPostsByTags(List<String> tags);
	
	Iterable<PostDto> findByDates(DatePeriodDto datePeriodDto);

	Iterable<CommentDto> findAllPostComments(String id);

	Iterable<CommentDto> findAllPostCommentsByAuthor(String id, String author);

}
