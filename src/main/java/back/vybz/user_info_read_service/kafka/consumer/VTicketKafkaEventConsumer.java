package back.vybz.user_info_read_service.kafka.consumer;

import back.vybz.user_info_read_service.kafka.event.TicketChangedEvent;
import back.vybz.user_info_read_service.user_info.domain.UserInfoRead;
import back.vybz.user_info_read_service.user_info.infrastructure.UserInfoReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VTicketKafkaEventConsumer {

    private final UserInfoReadRepository userInfoReadRepository;

    @KafkaListener(
            topics = "create-ticket-changed",
            groupId = "create-ticket-changed-group",
            containerFactory = "ticketChangedEventConcurrentKafkaListenerContainerFactory"
    )
    public void consumeTicketCountEvent(TicketChangedEvent ticketChangedEvent) {
        log.info("📦 [Kafka] v-티켓 이벤트 수신: {}", ticketChangedEvent);

        userInfoReadRepository.findByUserUuid(ticketChangedEvent.getUserUuid()).ifPresentOrElse(
                userInfo -> {
                    userInfo.setVTicketCount(ticketChangedEvent.getTicketCount());
                    userInfoReadRepository.save(userInfo);
                }, () -> {
                    userInfoReadRepository.save(UserInfoRead.builder()
                            .userUuid(ticketChangedEvent.getUserUuid())
                            .vTicketCount(ticketChangedEvent.getTicketCount())
                            .build());
                });
    }
}
