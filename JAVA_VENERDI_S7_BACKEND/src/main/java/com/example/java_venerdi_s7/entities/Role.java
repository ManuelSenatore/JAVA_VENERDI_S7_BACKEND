package com.example.java_venerdi_s7.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	public Role( RoleType roleType) {
		this.roleType = roleType;
		
		}
	
	
}
