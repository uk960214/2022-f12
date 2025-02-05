package com.woowacourse.f12.application;

import com.woowacourse.f12.domain.KeyboardRepository;
import com.woowacourse.f12.domain.Review;
import com.woowacourse.f12.domain.ReviewRepository;
import com.woowacourse.f12.dto.request.ReviewRequest;
import com.woowacourse.f12.dto.response.ReviewPageResponse;
import com.woowacourse.f12.exception.KeyboardNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final KeyboardRepository keyboardRepository;

    public ReviewService(final ReviewRepository reviewRepository, final KeyboardRepository keyboardRepository) {
        this.reviewRepository = reviewRepository;
        this.keyboardRepository = keyboardRepository;
    }

    @Transactional
    public Long save(final Long productId, final ReviewRequest reviewRequest) {
        validateKeyboardExists(productId);
        final Review review = reviewRequest.toReview(productId);
        return reviewRepository.save(review)
                .getId();
    }

    private void validateKeyboardExists(final Long productId) {
        if (!keyboardRepository.existsById(productId)) {
            throw new KeyboardNotFoundException();
        }
    }

    public ReviewPageResponse findPageByProductId(final Long productId, final Pageable pageable) {
        validateKeyboardExists(productId);
        final Slice<Review> page = reviewRepository.findPageByProductId(productId, pageable);
        return ReviewPageResponse.from(page);
    }

    public ReviewPageResponse findPage(final Pageable pageable) {
        final Slice<Review> page = reviewRepository.findPageBy(pageable);
        return ReviewPageResponse.from(page);
    }
}
