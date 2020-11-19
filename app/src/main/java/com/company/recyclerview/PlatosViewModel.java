package com.company.recyclerview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PlatosViewModel extends AndroidViewModel {

    PlatosRepositorio platosRepositorio;

    MutableLiveData<List<Plato>> listPlatosMutableLiveData = new MutableLiveData<>();

    public PlatosViewModel(@NonNull Application application) {
        super(application);

        platosRepositorio = new PlatosRepositorio();

        listPlatosMutableLiveData.setValue(platosRepositorio.obtener());
    }

    MutableLiveData<List<Plato>> obtener(){
        return listPlatosMutableLiveData;
    }
    MutableLiveData<Plato> platoSeleccionado = new MutableLiveData<>();

    void insertar(Plato plato){
        platosRepositorio.insertar(plato, new PlatosRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Plato> platos) {
                listPlatosMutableLiveData.setValue(platos);
            }
        });
    }

    void eliminar(Plato plato){
        platosRepositorio.eliminar(plato, new PlatosRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Plato> platos) {
                listPlatosMutableLiveData.setValue(platos);
            }
        });
    }

    void actualizar(Plato plato, float valoracion){
        platosRepositorio.actualizar(plato, valoracion, new PlatosRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Plato> platos) {
                listPlatosMutableLiveData.setValue(platos);
            }
        });
    }
    void seleccionar(Plato plato){
        platoSeleccionado.setValue(plato);
    }

    MutableLiveData<Plato> seleccionado(){
        return platoSeleccionado;
    }
}
