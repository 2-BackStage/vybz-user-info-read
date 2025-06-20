package back.vybz.user_info_read_service.kafka.consumer;

import back.vybz.user_info_read_service.common.entity.BaseResponseStatus;
import back.vybz.user_info_read_service.common.exception.BaseException;
import back.vybz.user_info_read_service.kafka.event.UserFollowingCountEvent;
import back.vybz.user_info_read_service.user_info.domain.UserInfoRead;
import back.vybz.user_info_read_service.user_info.infrastructure.UserInfoReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFollowingCountEventConsumer {

    private final UserInfoReadRepository userInfoReadRepository;

    @KafkaListener(
            topics = "user-following-count",
            groupId = "user-following-group",
            containerFactory = "userFollowingCountKafkaListenerContainerFactory"
    )
    public void consumeUserFollowingCountEvent(UserFollowingCountEvent event) {
        log.info("📦 [Kafka] 유저 팔로워 수 업데이트 이벤트 수신: {}", event);

        UserInfoRead userInfoRead = userInfoReadRepository.findByUserUuid(event.getUserUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER));

        userInfoRead.updateFollowingCount(event.getFollowingCount(), event.getDisplayFollowingCount());
        userInfoReadRepository.save(userInfoRead);
        log.info("📦 [Kafka] 유저 팔로워 수 업데이트 완료: {}", userInfoRead);
    }

}
