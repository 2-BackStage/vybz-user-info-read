package back.vybz.user_info_read_service.kafka.config;

import back.vybz.user_info_read_service.kafka.event.UserFollowingCountEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@RequiredArgsConstructor
public class UserFollowingCountEventKafkaConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, UserFollowingCountEvent> userFollowingCountEventConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(UserFollowingCountEvent.class, false))
        );
    }

    @Bean(name = "userFollowingCountKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, UserFollowingCountEvent> userFollowingCountKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserFollowingCountEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userFollowingCountEventConsumerFactory());
        return factory;
    }

}
