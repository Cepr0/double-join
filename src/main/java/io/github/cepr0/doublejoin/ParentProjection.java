package io.github.cepr0.doublejoin;

import org.springframework.beans.factory.annotation.Value;

public interface ParentProjection {

	@Value("#{target.parent.id}")
	Integer getId();

	@Value("#{target.parent.partitionKey}")
	Integer getPartitionKey();

	@Value("#{target.parent.name}")
	String getName();

	Stepchild getStepchild();
}
