package in.thiru.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPasswordDTO {

	private Integer userId;
	private String email;
	private String oldPwrd;
	private String newPassword;
	private String confirmPassword;

}
