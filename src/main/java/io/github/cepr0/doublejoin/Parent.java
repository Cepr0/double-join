package io.github.cepr0.doublejoin;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = "children")
@ToString(exclude = "children")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "parents")
public class Parent implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "partition_key")
	private Integer partitionKey;

	private String name;

	@OneToMany
	@JoinColumns({
			@JoinColumn(name = "parent_id", referencedColumnName = "id"),
			@JoinColumn(name = "partition_key", referencedColumnName = "partition_key")
	})
	private List<Child> children;

	public Parent(Integer partitionKey, String name) {
		this.partitionKey = partitionKey;
		this.name = name;
	}
}
