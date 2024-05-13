package stanford.convoconnect.chat;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String senderId;
    private String recipientId;
    private String content;
}
