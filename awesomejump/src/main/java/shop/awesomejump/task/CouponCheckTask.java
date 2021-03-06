package shop.awesomejump.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import shop.awesomejump.mapper.MemberMapper;


@Component
public class CouponCheckTask {
	
	private static final Logger logger =
			LoggerFactory.getLogger(CouponCheckTask.class);
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Scheduled(cron="0 0 0 * * *")
	public void checkCoupon() throws Exception{
		
		Integer result1 = memberMapper.oldCoupon();
		Integer result2 = memberMapper.todayOldCoupon();
		
		logger.warn(result1 + "//" + result2);
		logger.warn("====유효기간 +30 데이터 삭제====");
		logger.warn("====사용안한 쿠폰 삭제====");
	}
	
	
}
