package dev.cere.reviews.rest;

import dev.cere.reviews.api.ReviewDto;
import dev.cere.reviews.api.ReviewPutDto;
import dev.cere.reviews.api.ReviewSimpleDto;
import dev.cere.reviews.facade.ReviewFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reviews")
public class ReviewRestController {
    private final ReviewFacade reviewFacade;

    @Autowired
    public ReviewRestController(ReviewFacade reviewFacade) {
        this.reviewFacade = reviewFacade;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReviewDto> findById(@PathVariable("id") Long id) {
        var reviewDto = reviewFacade.findById(id);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @GetMapping
    public Page<ReviewDto> findAll(Pageable pageable) {
        return reviewFacade.findAll(pageable);
    }

    @GetMapping(path = "/content/{id}")
    public Page<ReviewDto> findAllByContentId(@PathVariable("id") Long id, Pageable pageable) {
        return reviewFacade.findAllByContentId(id, pageable);
    }

    @GetMapping(path = "/users/{id}")
    public Page<ReviewDto> findAllByUserId(@PathVariable("id") Long id, Pageable pageable) {
        return reviewFacade.findAllByUserId(id, pageable);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> create(@RequestBody ReviewSimpleDto reviewSimpleDto) {
        var reviewDto = reviewFacade.create(reviewSimpleDto);
        return new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReviewDto> updateById(
            @PathVariable("id") Long id, @RequestBody ReviewPutDto reviewPutDto) {
        var reviewDto = reviewFacade.update(id, reviewPutDto);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> removeById(@PathVariable("id") Long id) {
        reviewFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
