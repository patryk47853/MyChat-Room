package pl.patrykjava.mychatroom.model;

import lombok.*;
import pl.patrykjava.mychatroom.utils.MessageType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private String content;
    private String sender;
    private MessageType type;
    private String time;
}
