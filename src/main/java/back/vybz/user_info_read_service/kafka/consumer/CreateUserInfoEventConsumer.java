package back.vybz.user_info_read_service.kafka.consumer;

import back.vybz.user_info_read_service.common.entity.BaseResponseStatus;
import back.vybz.user_info_read_service.common.exception.BaseException;
import back.vybz.user_info_read_service.kafka.event.UserInfoEvent;
import back.vybz.user_info_read_service.user_info.domain.UserInfoRead;
import back.vybz.user_info_read_service.user_info.infrastructure.UserInfoReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserInfoEventConsumer {

    private final UserInfoReadRepository userInfoReadRepository;

    @KafkaListener(
            topics = "create-user-auth",
            groupId = "user-info-read-group",
            containerFactory = "userInfoKafkaListenerContainerFactory"
    )
    public void consumeUserInfoEvent(UserInfoEvent userInfoEvent) {
        log.info("🔥 Kafka 유저 정보 메시지 수신: {}", userInfoEvent);

        boolean exists = userInfoReadRepository.existsByUserUuid(userInfoEvent.getUserUuid());

        if (exists) {
            log.warn("🔥 유저 정보가 이미 존재합니다. userUuid: {}", userInfoEvent.getUserUuid());
            throw new BaseException(BaseResponseStatus.DUPLICATE_USER);
        }
        UserInfoRead userInfoRead = UserInfoRead.builder()
                .userUuid(userInfoEvent.getUserUuid())
                .nickname(userInfoEvent.getNickname())
                .profileImageUrl(userInfoEvent.getProfileImageUrl())
                .followingCount(userInfoEvent.getFollowingCount() == null ? 0 : userInfoEvent.getFollowingCount())
                .subscribeCount(userInfoEvent.getSubscribeCount() == null ? 0 : userInfoEvent.getSubscribeCount())
                .vTicketCount(userInfoEvent.getVTicketCount() == null ? 0 : userInfoEvent.getVTicketCount())
                .build();
        userInfoReadRepository.save(userInfoRead);
        log.info("🔥 유저 정보 저장 완료: {}", userInfoRead);
    }

}
