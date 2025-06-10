package back.vybz.user_info_read_service.user_info.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseUserInfoReadVo {

    private String userUuid;
    private String nickname;
    private String profileImageUrl;
    private Integer followingCount;
    private Integer subscribeCount;
    private Integer vTicketCount;

    @Builder
    public ResponseUserInfoReadVo(String userUuid, String nickname, String profileImageUrl,
                                  Integer followingCount, Integer subscribeCount, Integer vTicketCount) {
        this.userUuid = userUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.followingCount = followingCount;
        this.subscribeCount = subscribeCount;
        this.vTicketCount = vTicketCount;
    }

}
