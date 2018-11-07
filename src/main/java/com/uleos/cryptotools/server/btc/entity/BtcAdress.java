package com.uleos.cryptotools.server.btc.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.uleos.cryptotools.server.AbstractNaturalKeyEntity;

@Entity
@Table(name = "BTC_ADRESS")
@SequenceGenerator(name = "seq", allocationSize = 10, sequenceName = "id_sequence_crypt")
public class BtcAdress extends AbstractNaturalKeyEntity {

	public BtcAdress() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "id")
	private Long id;

	@Column(name = "btcadress")
	private String adress;

	@Column(name = "ipadress")
	private String ip;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "BtcAdress [uuid=" + getUuid() + ", id=" + id + ", adress=" + adress + ", ip=" + ip + "]";
	}

	public BtcAdress(JsonObject object) {
		this.adress = object.getString("adress");
		this.ip = object.getString("ip");
		this.id = object.getJsonNumber("id").longValue();
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder().add("id", this.id).add("adress", this.adress).add("ip", this.ip).build();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BtcAdress other = (BtcAdress) obj;
		if (adress == null) {
			if (other.adress != null) {
				return false;
			}
		} else if (!adress.equals(other.adress)) {
			return false;
		}
		return true;
	}

}
