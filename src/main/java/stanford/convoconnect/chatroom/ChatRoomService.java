package stanford.convoconnect.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId).map(ChatRoom::getChatId).or(() ->{
           if (createNewRoomIfNotExists){
               var chatId = createChatId(senderId, recipientId);
               return Optional.of(chatId);
           }
           return Optional.empty();
        });
    }

    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);
        ChatRoom senderRecipient = ChatRoom.builder().recipientId(recipientId).senderId(senderId).chatId(chatId).build();
        ChatRoom recipientSender = ChatRoom.builder().recipientId(senderId).senderId(recipientId).chatId(chatId).build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return chatId;

    }
}
