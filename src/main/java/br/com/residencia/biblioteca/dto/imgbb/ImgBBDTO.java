package br.com.residencia.biblioteca.dto.imgbb;

public class ImgBBDTO {
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ImgBBDTO [data=" + data + ", getData()=" + getData() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	} 

}
