package com.company.recyclerview;

import java.util.ArrayList;
import java.util.List;

public class PlatosRepositorio {

    List<Plato> platos = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Plato> platos);
    }

    PlatosRepositorio(){
        platos.add(new Plato("Fabada", "Alubias acompañadas con trozos de papa, embutidos y carne de cerdo y azafrán."));
        platos.add(new Plato("Arroz con costra", "Arroz con pollo, embutidos, carne de cerdo y garbanzos horneado. Seacompaña con perejil y hebras de azafrán."));
        platos.add(new Plato("Bajoques farcides", "Pimientos rellenos de jamón, pollo, jitomate escalfado, acompañado de unasalsa de azafrán."));
        platos.add(new Plato("Atún mechado", "Atún mechado con tocino, pimienta y ajo y cocido con aceite de oliva y vinoblanco. Se presenta en rodajas acompañado con la salsa de su cocción"));
        platos.add(new Plato("Bienmesabe de café","Biscocho de café cubierto de pelo de ángel, bañado con salsa de almendrásy espolvoreado de azúcar lustre."));

    }

    List<Plato> obtener() {
        return platos;
    }

    void insertar(Plato plato, Callback callback){
        platos.add(plato);
        callback.cuandoFinalice(platos);
    }

    void eliminar(Plato elemento, Callback callback) {
        platos.remove(elemento);
        callback.cuandoFinalice(platos);
    }

    void actualizar(Plato plato, float valoracion, Callback callback) {
        plato.valoracion = valoracion;
        callback.cuandoFinalice(platos);
    }
}
