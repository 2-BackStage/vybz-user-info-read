package back.vybz.user_info_read_service.kafka.consumer;

import back.vybz.user_info_read_service.user_info.infrastructure.UserInfoReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteUserInfoEventConsumer {

    private final UserInfoReadRepository userInfoReadRepository;

    @KafkaListener(
            topics = "delete-user-info",
            groupId = "user-info-read-group",
            containerFactory = "stringUserInfoKafkaListenerContainerFactory"
    )
    public void consumeDeleteUserInfoEvent(String userUuid) {
        log.info("🗑️ [Kafka] 유저 정보 삭제 이벤트 수신: {}", userUuid);

        if (!userInfoReadRepository.existsByUserUuid(userUuid)) {
            log.warn("⚠️ [Kafka] 삭제할 유저 정보가 없습니다. userUuid: {}", userUuid);
            return;
        }

        userInfoReadRepository.deleteByUserUuid(userUuid);
        log.info("✅ [Kafka] 유저 정보 삭제 완료: {}", userUuid);
    }

}
