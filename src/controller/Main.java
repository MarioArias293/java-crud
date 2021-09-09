package controller;

import model.CRUD_model;
import model.Serie_model;
import view.Series_view;
public class Main {
    public static void main(String[]args){
        Serie_model modPro= new Serie_model();
        CRUD_model modCts= new CRUD_model();
        Series_view viewPro= new Series_view();
    
        Series_control control= new Series_control(modPro,modCts,viewPro);
        control.iniciar();
        viewPro.setVisible(true);
    }
}
