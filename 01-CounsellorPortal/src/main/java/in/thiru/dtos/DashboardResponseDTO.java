package in.thiru.dtos;

import lombok.Data;

@Data
public class DashboardResponseDTO {
	
	private Integer totalEnquiries;
	private Integer openEnquiries;
	private Integer enrolledEnquiries;
	private Integer lostEnquiries;

}
