package com.uleos.cryptotools.server;

import java.util.UUID;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class AbstractNaturalKeyEntity {

	private String uuid;
	@Transient
	private boolean markedToDelete;

	public AbstractNaturalKeyEntity() {
		uuid = UUID.randomUUID().toString();
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractNaturalKeyEntity other = (AbstractNaturalKeyEntity) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	public boolean isMarkedToDelete() {
		return markedToDelete;
	}

	public void setMarkedToDelete(boolean markedToDelete) {
		this.markedToDelete = markedToDelete;
	}

}
