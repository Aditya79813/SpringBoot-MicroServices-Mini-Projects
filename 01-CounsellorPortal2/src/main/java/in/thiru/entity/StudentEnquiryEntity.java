package in.thiru.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "studentEnquiry_table")
@Setter
@Getter
public class StudentEnquiryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String name;
	private Long phno;
	private String classMode;
	private String course;
	private String enqStatus;
	
	@ManyToOne
	@JoinColumn(name = "counsellorId")
	private CounsellorEntity counsellor;
}
