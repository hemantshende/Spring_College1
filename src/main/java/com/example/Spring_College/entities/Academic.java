package com.example.Spring_College.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "academic_records")
public class Academic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "qualification_id")
	private Qualification qualification;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "interests_id")
	private Interests interests;

	@Column(name = "previous_college_name")
	private String previousCollegeName;

	@Column(name = "specialization")
	private String specialization;

	@ElementCollection
	@CollectionTable(name = "academic_files", joinColumns = @JoinColumn(name = "academic_id"))
	@Column(name = "file_data", columnDefinition = "LONGBLOB")
	private List<byte[]> files; // Store binary file data, not the actual MultipartFile objects


	@ElementCollection // to efficiently store and manage a collection of simple values associated with
						// an entity
						// without the need to create a separate entity class for them
	@CollectionTable(name = "academic_languages", joinColumns = @JoinColumn(name = "academic_id"))
	@Column(name = "language")
	private List<String> laguagesKnown;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

}
