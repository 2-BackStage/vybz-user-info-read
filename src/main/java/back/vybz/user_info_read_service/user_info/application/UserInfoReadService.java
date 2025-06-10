package back.vybz.user_info_read_service.user_info.application;

import back.vybz.user_info_read_service.user_info.dto.ResponseUserInfoReadDto;

public interface UserInfoReadService {

    /**
     * 유저 uuid로 유저 정보 조회
     * @param userUuid
     */
    ResponseUserInfoReadDto getUserInfo(String userUuid);

}
