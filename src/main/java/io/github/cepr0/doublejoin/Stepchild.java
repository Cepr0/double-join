package io.github.cepr0.doublejoin;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "stepchildren")
public class Stepchild {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "partition_key")
	private Integer partitionKey;

	@Column(name = "parent_id")
	private Integer parentId;

	private String name;

	public Stepchild(Integer partitionKey, Integer parentId, String name) {
		this.partitionKey = partitionKey;
		this.parentId = parentId;
		this.name = name;
	}
}
