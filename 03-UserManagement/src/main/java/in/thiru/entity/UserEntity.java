package in.thiru.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "user_master")
@Setter
@Getter
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String name;
	private String email;
	private Long phno;
	private String pswrd;
	private String isPswrdUpdated;
	

	@ManyToOne
    @JoinColumn(name = "country_id")
	private CountryEntity country;
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	private StateEntity state;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private CityEntity city;
	
	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;

}
