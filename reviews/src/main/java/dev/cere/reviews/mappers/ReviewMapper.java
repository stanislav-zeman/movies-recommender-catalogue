package dev.cere.reviews.mappers;

import dev.cere.reviews.api.ReviewDto;
import dev.cere.reviews.api.ReviewSimpleDto;
import dev.cere.reviews.data.entities.Review;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto mapToDto(Review review);

    Review mapFromSimpleDto(ReviewSimpleDto reviewSimpleDto);

    List<ReviewDto> mapToDtoList(List<Review> reviews);

    default Page<ReviewDto> mapToPageDto(Page<Review> reviews) {
        return new PageImpl<>(
                mapToDtoList(reviews.getContent()), reviews.getPageable(), reviews.getTotalPages());
    }
}
