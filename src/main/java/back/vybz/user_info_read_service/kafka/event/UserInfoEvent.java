package back.vybz.user_info_read_service.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoEvent {

    private String userUuid;
    private String nickname;
    private String profileImageUrl;
    private Integer followingCount;
    private Integer subscribeCount;
    private Integer vTicketCount;

}
