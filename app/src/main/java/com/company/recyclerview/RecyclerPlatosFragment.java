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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import com.company.recyclerview.databinding.FragmentRecyclerPlatosBinding;
import com.company.recyclerview.databinding.ViewholderPlatoBinding;


import java.util.List;


public class RecyclerPlatosFragment extends Fragment {

    private FragmentRecyclerPlatosBinding binding;
    private PlatosViewModel platosViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerPlatosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        platosViewModel = new ViewModelProvider(requireActivity()).get(PlatosViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_recyclerPlatosFragment_to_nuevoPlatoFragment);
            }
        });

        PlatosAdapter platosAdapter = new PlatosAdapter();
        binding.recyclerView.setAdapter(platosAdapter);

        platosViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Plato>>() {
            @Override
            public void onChanged(List<Plato> platos) {
                platosAdapter.establecerLista(platos);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Plato plato = platosAdapter.obtenerPlato(posicion);
                platosViewModel.eliminar(plato);

            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    class PlatoViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPlatoBinding binding;

        public PlatoViewHolder(ViewholderPlatoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class PlatosAdapter extends RecyclerView.Adapter<PlatoViewHolder> {

        List<Plato> platos;

        @NonNull
        @Override

        public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PlatoViewHolder(ViewholderPlatoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {

            Plato plato = platos.get(position);

            holder.binding.nombre.setText(plato.nombre);
            holder.binding.valoracion.setRating(plato.valoracion);

            holder.binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(fromUser) {
                        platosViewModel.actualizar(plato, rating);
                    }
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    platosViewModel.seleccionar(plato);
                    navController.navigate(R.id.action_recyclerPlatosFragment_to_mostrarPlatosFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return platos != null ? platos.size() : 0;
        }

        public void establecerLista(List<Plato> platos){
            this.platos = platos;
            notifyDataSetChanged();
        }
        public Plato obtenerPlato(int posicion){
            return platos.get(posicion);
        }
    }
}