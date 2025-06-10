package back.vybz.user_info_read_service.user_info.application;

import back.vybz.user_info_read_service.common.entity.BaseResponseStatus;
import back.vybz.user_info_read_service.common.exception.BaseException;
import back.vybz.user_info_read_service.user_info.domain.UserInfoRead;
import back.vybz.user_info_read_service.user_info.dto.ResponseUserInfoReadDto;
import back.vybz.user_info_read_service.user_info.infrastructure.UserInfoReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoReadServiceImpl implements UserInfoReadService {

    private final UserInfoReadRepository userInfoReadRepository;

    /**
     * 유저 uuid로 유저 정보 조회
     * @param userUuid
     */
    @Override
    public ResponseUserInfoReadDto getUserInfo(String userUuid) {
        UserInfoRead userInfoRead = userInfoReadRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        return ResponseUserInfoReadDto.from(userInfoRead);
    }

}
