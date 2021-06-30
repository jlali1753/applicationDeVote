package fr.hou1753.applicationvote;

public class Vote {
    public  String text;
    public int nombreOui;
    public int nombreNon;

    public Vote(String text) {
        this.text = text;
        this.nombreOui=0;
        this.nombreNon=0;
    }

    public Vote(String text, String nombreOui, String nombreNon) {
        this.text = text;
        this.nombreOui = Integer.parseInt(nombreOui);
        this.nombreNon = Integer.parseInt(nombreNon);
    }
    public Vote(String text, int nombreOui, int nombreNon) {
        this.text = text;
        this.nombreOui = nombreOui;
        this.nombreNon = nombreNon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNombreOui() {
        return nombreOui;
    }

    public void setNombreOui(int nombreOui) {
        this.nombreOui = nombreOui;
    }

    public int getNombreNon() {
        return nombreNon;
    }

    public void setNombreNon(int nombreNon) {
        this.nombreNon = nombreNon;
    }
}
