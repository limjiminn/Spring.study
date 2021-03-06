package shop.awesomejump.config.webSocket;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import shop.awesomejump.domain.MemberDTO;
import shop.awesomejump.service.IMemberDAO;
import shop.awesomejump.util.FileUtils;

@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler{
	
	@Autowired
	private IMemberDAO mDao;

	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	  private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	  
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			// 웹소켓 클라이언트가 연결되면 호출
			sessionList.add(session);
			logger.info("{} 연결됨", session.getId());
			 
		}
		
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			//웹소켓 클라이언트가 데이터를 전송하면 호출
			logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
			
			String msg = message.getPayload();
			String user = "";
			int idx1 = message.getPayload().indexOf(":");
			int idx2 = message.getPayload().indexOf(":",4);
			//System.out.println(idx1);
			//System.out.println(idx2);
			String img = "";
			if(idx1 > 0) {
				System.out.println("사용자닉네임 --> " + msg.substring(idx1+1,idx2));
				user = msg.substring(idx1+1,idx2);
				
				//닉네임으로 회원 이미지 검색
				MemberDTO mDto = mDao.selectProfile(user);
				img = new FileUtils().setMemberProfileImagePath(mDto).getMem_profile_img();
				System.out.println(img);
				if(mDto.getMem_profile_img().equals("profile.jpg")) {
					img = "";
				}
			}
			
			for(WebSocketSession sess: sessionList) {
				sess.sendMessage(new TextMessage(user + message.getPayload() + img));
			}
		}
		
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			//웹소켓 클라이언트가 연결을 직접 끊거나 서버에서 타임아웃이 발생해서 연걸을 끊을 때 호출
			sessionList.remove(session);
			logger.info("{} 연결끊김", session.getId());
		}
}
