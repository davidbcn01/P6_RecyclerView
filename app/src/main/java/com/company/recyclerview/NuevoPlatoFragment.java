package com.company.recyclerview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.recyclerview.databinding.FragmentNuevoPlatoBinding;


public class NuevoPlatoFragment extends Fragment {
    private FragmentNuevoPlatoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoPlatoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PlatosViewModel platosViewModel = new ViewModelProvider(requireActivity()).get(PlatosViewModel.class);
        NavController navController = Navigation.findNavController(view);
        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();

                platosViewModel.insertar(new Plato(nombre, descripcion));

                navController.popBackStack();
            }
        });


    }
}