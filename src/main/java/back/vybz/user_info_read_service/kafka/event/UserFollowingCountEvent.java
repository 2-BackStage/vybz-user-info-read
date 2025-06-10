package back.vybz.user_info_read_service.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowingCountEvent {

    private String userUuid;
    private Integer followingCount;
    private String displayFollowingCount;

}
