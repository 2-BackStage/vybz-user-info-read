package back.vybz.user_info_read_service.kafka.consumer;

import back.vybz.user_info_read_service.kafka.event.SubscribeCountEvent;
import back.vybz.user_info_read_service.user_info.domain.UserInfoRead;
import back.vybz.user_info_read_service.user_info.infrastructure.UserInfoReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscribeCountEventConsumer {

    private final UserInfoReadRepository userInfoReadRepository;

    @KafkaListener(
            topics = "subscribe-count-events",
            groupId = "subscribe-count-group",
            containerFactory = "stringSubscribeCountEventConcurrentKafkaListenerContainerFactory"
    )
    public void consumeSubscribeCountEvent(SubscribeCountEvent subscribeCountEvent) {
        log.info("📦 [Kafka] 구독 수 이벤트 수신: {}", subscribeCountEvent);

        userInfoReadRepository.findByUserUuid(subscribeCountEvent.getUserUuid()).ifPresentOrElse(
                userInfo -> {
                    userInfo.setSubscribeCount(subscribeCountEvent.getSubscriptionCount());
                    userInfoReadRepository.save(userInfo);
                }, () -> {
                    userInfoReadRepository.save(UserInfoRead.builder()
                            .userUuid(subscribeCountEvent.getUserUuid())
                            .vTicketCount(subscribeCountEvent.getSubscriptionCount())
                            .build());
                });
    }
}
