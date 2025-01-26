package Model;

import java.time.LocalDate;

import lib_genericP65v0.generic;

public class cliente {
	private generic<Integer, String> dt1_p; // Para ID, DNI, nombre y email
    private generic<String, String> dt2_p; // Para teléfono y dirección
    private generic<LocalDate, Boolean> dt3_p; // Para fecha de creación y estado

    public cliente() {
        dt1_p = new generic<>(0,"","");
        dt2_p = new generic<>("","","");
        dt3_p = new generic<>(LocalDate.now(), false);
    }

    public cliente(int id_client, String name_client, String email, String dni, String phone, String address, LocalDate create_client, boolean state) {
        dt1_p = new generic<>(id_client, dni, name_client); // ID, DNI, nombre y email
        dt2_p = new generic<>(phone, address, email); // Teléfono y dirección
        dt3_p = new generic<>(create_client, state); // Fecha de creación y estado
    }

    // Getters y setters para los atributos
    public int getIdClient() {
    	if(dt1_p.getAttribute1() != null) {
        return dt1_p.getAttribute1();
    	}else {
    		throw new IllegalStateException("ID del cliente no inicializado");
    	}
    }

    public void setIdClient(int id_C) {
        dt1_p.setAttribute1(id_C);
    }

    public String getNameClient() {
        return dt1_p.getAttribute3(); // Aquí, Integer a String
    }

    public void setNameClient(String nameC) {
        // Aquí simplemente se asigna el String al atributo adecuado
        dt1_p.setAttribute3(nameC); 
    }

    public String getDni() {
        return dt1_p.getAttribute4();
    }

    public void setDni(String dni) {
        dt1_p.setAttribute4(dni);
    }

    public String getEmail() {
        return dt2_p.getAttribute3();
    }

    public void setEmail(String email) {
        dt2_p.setAttribute3(email);
    }

    public String getPhone() {
        return dt2_p.getAttribute1();
    }

    public void setPhone(String phone) {
        dt2_p.setAttribute1(phone);
    }

    public String getAddress() {
        return dt2_p.getAttribute2();
    }

    public void setAddress(String address) {
        dt2_p.setAttribute2(address);
    }

    public LocalDate getCreateC() {
        return dt3_p.getAttribute1();
    }

    public void setCreateC(LocalDate createC) {
        dt3_p.setAttribute1(createC);
    }

    public boolean getStateC() {
        return dt3_p.getAttribute3();
    }

    public void setStateC(boolean stateC) {
        dt3_p.setAttribute3(stateC);
    }

    @Override
    public String toString() {
        return "cliente [getIdClient()=" + getIdClient() + ", getNameClient()=" + getNameClient() + ", getDni()="
                + getDni() + ", getEmail()=" + getEmail() + ", getPhone()=" + getPhone() + ", getAddress()="
                + getAddress() + ", getCreateC()=" + getCreateC() + ", getStateC()=" + getStateC() + "]";
    }
}
