package in.thiru.dtos;

import lombok.Data;

@Data
public class StudentEnquiriesDTO {
	
	private Integer enqId;
	private String name;
	private Long phno;
	private String classMode;
	private String course;
	private String enqStatus;

}
