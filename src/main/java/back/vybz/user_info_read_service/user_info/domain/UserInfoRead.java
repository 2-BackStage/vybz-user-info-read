package back.vybz.user_info_read_service.user_info.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Document("user_info_read")
public class UserInfoRead {

    @Id
    private String id;

    /**
     * 유저 uuid
     */
    @Field(name = "user_uuid")
    private String userUuid;

    /**
     * 닉네임
     */
    @Field(name = "nickname")
    private String nickname;

    /**
     * 프로필 이미지 URL
     */
    @Field(name = "profile_image_url")
    private String profileImageUrl;

    /**
     * 팔로잉 수
     */
    @Field(name = "following_count")
    private Integer followingCount;

    /**
     * 팔로잉 수 (표시용)
     */
    @Field(name = "display_following_count")
    private String displayFollowingCount;

    /**
     * 구독 수
     */
    @Field(name = "subscribe_count")
    private Integer subscribeCount;

    /**
     * vybz 티켓 수
     */
    @Field(name = "v_ticket_count")
    private Integer vTicketCount;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;

    public void updateUserInfo(String userUuid, String nickname, String profileImageUrl) {
        this.userUuid = userUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public void updateFollowingCount(Integer followingCount, String displayFollowingCount) {
        this.followingCount = followingCount;
        this.displayFollowingCount = displayFollowingCount;
    }

    @Builder
    public UserInfoRead(String id, String userUuid, String nickname, String profileImageUrl,
                        Integer followingCount, String displayFollowingCount, Integer subscribeCount, Integer vTicketCount,
                        Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userUuid = userUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.followingCount = followingCount;
        this.displayFollowingCount = displayFollowingCount;
        this.subscribeCount = subscribeCount;
        this.vTicketCount = vTicketCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
