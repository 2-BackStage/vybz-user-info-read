package back.vybz.user_info_read_service.user_info.dto;

import back.vybz.user_info_read_service.user_info.domain.UserInfoRead;
import back.vybz.user_info_read_service.user_info.vo.ResponseUserInfoReadVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseUserInfoReadDto {

    private String userUuid;
    private String nickname;
    private String profileImageUrl;
    private Integer followingCount;
    private Integer subscribeCount;
    private Integer vTicketCount;

    @Builder
    public ResponseUserInfoReadDto(String userUuid, String nickname, String profileImageUrl,
                                   Integer followingCount, Integer subscribeCount, Integer vTicketCount) {
        this.userUuid = userUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.followingCount = followingCount;
        this.subscribeCount = subscribeCount;
        this.vTicketCount = vTicketCount;
    }

    public static ResponseUserInfoReadDto from(UserInfoRead userInfoRead) {
        return ResponseUserInfoReadDto.builder()
                .userUuid(userInfoRead.getUserUuid())
                .nickname(userInfoRead.getNickname())
                .profileImageUrl(userInfoRead.getProfileImageUrl())
                .followingCount(userInfoRead.getFollowingCount())
                .subscribeCount(userInfoRead.getSubscribeCount())
                .vTicketCount(userInfoRead.getVTicketCount())
                .build();
    }

    public ResponseUserInfoReadVo toVo() {
        return ResponseUserInfoReadVo.builder()
                .userUuid(userUuid)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .followingCount(followingCount)
                .subscribeCount(subscribeCount)
                .vTicketCount(vTicketCount)
                .build();
    }

}
