package gov.nic.model;

import java.util.List;

public class CertTextPartb2 {

	private Section1 section1;
	private Section2 section2;
	private Section3 section3;
	private Section4 section4;
	private List<Qualifications> qualifications;
	private Signature signature;

	public void setSection1(Section1 section1) {
		this.section1 = section1;
	}

	public Section1 getSection1() {
		return section1;
	}

	public void setSection2(Section2 section2) {
		this.section2 = section2;
	}

	public Section2 getSection2() {
		return section2;
	}

	public void setSection3(Section3 section3) {
		this.section3 = section3;
	}

	public Section3 getSection3() {
		return section3;
	}

	public void setSection4(Section4 section4) {
		this.section4 = section4;
	}

	public Section4 getSection4() {
		return section4;
	}

	public void setQualifications(List<Qualifications> qualifications) {
		this.qualifications = qualifications;
	}

	public List<Qualifications> getQualifications() {
		return qualifications;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}

	public Signature getSignature() {
		return signature;
	}

}
