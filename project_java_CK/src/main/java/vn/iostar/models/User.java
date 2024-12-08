package vn.iostar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private int userid;
	private String adddress;
	private String email;
	private String fullname;
	private String images;
	private String password;
	private String phone;
	private boolean status;
	private Boolean isEdit = false;
	

}
