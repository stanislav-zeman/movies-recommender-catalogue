package dev.cere.recommendations.mappers;

import dev.cere.recommendations.api.ReviewDto;
import dev.cere.recommendations.api.ReviewSimpleDto;
import dev.cere.recommendations.data.review.Review;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto mapToDto(Review review);

    Review mapFromDto(ReviewDto reviewDto);

    Review mapFromSimpleDto(ReviewSimpleDto reviewSimpleDto);

    List<ReviewDto> mapToDtoList(List<Review> reviews);

    default Page<ReviewDto> mapToPageDto(Page<Review> reviews) {
        return new PageImpl<>(
                mapToDtoList(reviews.getContent()), reviews.getPageable(), reviews.getTotalPages());
    }
}
