package Taxonomia;

/**
 * Created by Jorge Urueta on 24/10/2017.
 */

public class atributosTaxonomia {


    private String ID;
    private String Nombrecientifico;
    private String Nombrecomun;
    private String Familia;
    private String Caracteristicasgenerales;
    private String Foto;
    private String Hábitat;
    private String zonas;

    public atributosTaxonomia(){


    }

    public atributosTaxonomia(String zonas) {
        this.zonas = zonas;
    }




    public atributosTaxonomia(String ID, String nombrecientifico, String nombrecomun, String familia, String caracteristicasgenerales, String foto, String hábitat, String zonas) {
        this.ID = ID;
        Nombrecientifico = nombrecientifico;
        Nombrecomun = nombrecomun;
        Familia = familia;
        Caracteristicasgenerales = caracteristicasgenerales;
        Foto = foto;
        Hábitat = hábitat;
        this.zonas = zonas;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {


        this.ID = ID;
    }

    public String getNombrecientifico() {

        return Nombrecientifico;
    }

    public void setNombrecientifico(String nombrecientifico) {
        Nombrecientifico = nombrecientifico;
    }

    public String getNombrecomun() {
        return Nombrecomun;
    }

    public void setNombrecomun(String nombrecomun) {

        Nombrecomun = nombrecomun;
    }

    public String getFamilia() {
        return Familia;
    }

    public void setFamilia(String familia) {
        Familia = familia;
    }

    public String getCaracteristicasgenerales() {
        return Caracteristicasgenerales;
    }

    public void setCaracteristicasgenerales(String caracteristicasgenerales) {
        Caracteristicasgenerales = caracteristicasgenerales;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getHábitat() {
        return Hábitat;
    }

    public void setHábitat(String hábitat) {
        Hábitat = hábitat;
    }

    public String getZonas() {
        return zonas;
    }

    public void setZonas(String zonas) {
        this.zonas = zonas;
    }


}
