package back.vybz.user_info_read_service.kafka.consumer;

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
public class UpdateUserInfoEventConsumer {

    private final UserInfoReadRepository userInfoReadRepository;

    @KafkaListener(
            topics = "update-user-info",
            groupId = "update-user-info-read-group",
            containerFactory = "userInfoKafkaListenerContainerFactory"
    )
    public void consumeUpdateUserInfoEvent(UserInfoEvent userInfoEvent) {
        log.info("📦 [Kafka] 유저 정보 업데이트 이벤트 수신: {}", userInfoEvent);

        UserInfoRead userInfoRead = userInfoReadRepository.findByUserUuid(userInfoEvent.getUserUuid())
                .orElse(null);

        if (userInfoRead == null) {
            log.warn("❌ [Kafka] 해당 유저 정보가 존재하지 않습니다. userUuid: {}", userInfoEvent.getUserUuid());
            return;
        }
        userInfoRead.updateUserInfo(
                userInfoEvent.getUserUuid(),
                userInfoEvent.getNickname(),
                userInfoEvent.getProfileImageUrl()
        );

        userInfoReadRepository.save(userInfoRead);
        log.info("✅ [Kafka] 유저 정보 업데이트 완료: {}", userInfoRead);
    }

}
