package com.company.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.company.recyclerview.databinding.FragmentMostrarPlatoBinding;


public class MostrarPlatoFragment extends Fragment {
    private FragmentMostrarPlatoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarPlatoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PlatosViewModel platosViewModel = new ViewModelProvider(requireActivity()).get(PlatosViewModel.class);

        platosViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Plato>() {
            @Override
            public void onChanged(Plato plato) {
                binding.nombre.setText(plato.nombre);
                binding.descripcion.setText(plato.descripcion);
                binding.valoracion.setRating(plato.valoracion);

                binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if(fromUser){
                            platosViewModel.actualizar(plato, rating);
                        }
                    }
                });
            }
        });
    }
}