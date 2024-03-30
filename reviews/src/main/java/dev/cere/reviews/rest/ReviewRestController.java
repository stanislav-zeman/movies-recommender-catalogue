package dev.cere.reviews.rest;

import dev.cere.reviews.api.ReviewDto;
import dev.cere.reviews.facade.ReviewFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return ResponseEntity.ok(reviewFacade.findById(id));
    }

    @GetMapping
    public Page<ReviewDto> findAll(Pageable pageable) {
        return reviewFacade.findAll(pageable);
    }
}
