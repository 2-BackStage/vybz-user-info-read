package back.vybz.user_info_read_service.user_info.infrastructure;

import back.vybz.user_info_read_service.user_info.domain.UserInfoRead;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserInfoReadRepository extends MongoRepository<UserInfoRead, String> {

    /**
     * 유저 uuid로 유저 정보 조회
     * @param userUuid
     */
    Optional<UserInfoRead> findByUserUuid(String userUuid);

    /**
     * 유저 uuid로 유저 존재 여부 확인
     * @param userUuid
     */
    boolean existsByUserUuid(String userUuid);

    /**
     * 유저 uuid로 유저 정보 삭제
     * @param userUuid
     */
    void deleteByUserUuid(String userUuid);

}
