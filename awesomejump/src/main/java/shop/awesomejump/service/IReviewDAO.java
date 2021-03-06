package shop.awesomejump.service;

import shop.awesomejump.domain.ReviewDTO;
import shop.awesomejump.domain.ReviewImageDTO;

public interface IReviewDAO {
		
	public boolean insertReview(ReviewDTO review);

	public boolean insertReviewImage(ReviewImageDTO reviewImage);
	
	public java.util.List<ReviewDTO> getReviewList(int product_no);
	
}
