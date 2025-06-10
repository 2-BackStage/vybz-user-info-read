package back.vybz.user_info_read_service.user_info.presentation;

import back.vybz.user_info_read_service.common.entity.BaseResponseEntity;
import back.vybz.user_info_read_service.user_info.application.UserInfoReadService;
import back.vybz.user_info_read_service.user_info.dto.ResponseUserInfoReadDto;
import back.vybz.user_info_read_service.user_info.vo.ResponseUserInfoReadVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-info-read")
public class UserInfoReadController {

    private final UserInfoReadService userInfoReadService;

    /**
     * 유저 uuid로 유저 정보 조회
     * @param userUuid
     */
    @Operation(summary = "유저 uuid로 마이페이지 유저 정보 조회", description = "유저 uuid로 마이페이지 유저 정보를 조회합니다.", tags = {"User-Info-Read-Service"})
    @GetMapping("/{userUuid}")
    public BaseResponseEntity<ResponseUserInfoReadVo> getUserInfo(@PathVariable("userUuid") String userUuid) {
        ResponseUserInfoReadDto responseUserInfoReadDto = userInfoReadService.getUserInfo(userUuid);
        return new BaseResponseEntity<>(responseUserInfoReadDto.toVo());
    }

}
