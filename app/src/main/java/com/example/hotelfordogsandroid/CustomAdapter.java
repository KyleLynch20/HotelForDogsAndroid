package com.example.hotelfordogsandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Posts> posts;

    public CustomAdapter(List<Posts> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            return new SitterViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.sitter_posts,
                            parent,
                            false
                    )
            );
        } else {
            return new NeedViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.need_posts,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0){
            SitterPost sitterPost = (SitterPost) posts.get(position).getPostObject();
            ((SitterViewHolder) holder).setSitterData(sitterPost);
        } else {
            NeedPost needPost = (NeedPost) posts.get(position).getPostObject();
            ((NeedViewHolder) holder).setNeedData(needPost);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return posts.get(position).getPostType();
    }

    static class SitterViewHolder extends RecyclerView.ViewHolder {
        private TextView amountPerHour, bio, breedSize, city, state, date, email, fencedBackYard,
                fullName, otherAnimals, phone, title;

        public SitterViewHolder(@NonNull View itemView) {
            super(itemView);
            amountPerHour = itemView.findViewById(R.id.amount_per_hour);
            bio = itemView.findViewById(R.id.bio);
            breedSize = itemView.findViewById(R.id.breed_size);
            city = itemView.findViewById(R.id.city);
            state = itemView.findViewById(R.id.state);
            date = itemView.findViewById(R.id.date);
            email = itemView.findViewById(R.id.email);
            fencedBackYard = itemView.findViewById(R.id.fenced_backyard);
            fullName = itemView.findViewById(R.id.full_name);
            otherAnimals = itemView.findViewById(R.id.other_animals);
            phone = itemView.findViewById(R.id.phone);
            title = itemView.findViewById(R.id.title);
        }

        void setSitterData(SitterPost sitterPost) {
            amountPerHour.setText(sitterPost.getAmountPerHour());
            bio.setText(sitterPost.getBio());
            breedSize.setText(sitterPost.getBreedSize());
            city.setText(sitterPost.getCity());
            state.setText(sitterPost.getState());
            date.setText(sitterPost.getDate());
            email.setText(sitterPost.getEmail());
            fencedBackYard.setText(sitterPost.getFencedBackYard());
            fullName.setText(sitterPost.getFullName());
            otherAnimals.setText(sitterPost.getOtherAnimals());
            phone.setText(sitterPost.getPhone());
            title.setText(sitterPost.getTitle());
        }
    }

    static class NeedViewHolder extends RecyclerView.ViewHolder {
        private TextView amountOfTime, amountPerHour, animalFriendly,
                city, state, date, dogBreed, dogName, dogNeeds, email,
                fullName, phone, pottyTrained, title;

        public NeedViewHolder(@NonNull View itemView) {
            super(itemView);
            amountOfTime = itemView.findViewById(R.id.amount_of_time);
            amountPerHour = itemView.findViewById(R.id.amount_per_hour);
            animalFriendly = itemView.findViewById(R.id.animal_friendly);
            city = itemView.findViewById(R.id.city);
            state = itemView.findViewById(R.id.state);
            date = itemView.findViewById(R.id.date);
            dogBreed = itemView.findViewById(R.id.dog_breed);
            dogName = itemView.findViewById(R.id.dog_name);
            dogNeeds = itemView.findViewById(R.id.dog_needs);
            email = itemView.findViewById(R.id.email);
            fullName = itemView.findViewById(R.id.full_name);
            phone = itemView.findViewById(R.id.phone);
            pottyTrained = itemView.findViewById(R.id.potty_trained);
            title = itemView.findViewById(R.id.title);
        }

        void setNeedData(NeedPost needPost){
            amountOfTime.setText(needPost.getAmountOfTime());
            amountPerHour.setText(needPost.getAmountPerHour());
            animalFriendly.setText(needPost.getAnimalFriendly());
            city.setText(needPost.getCity());
            state.setText(needPost.getState());
            date.setText(needPost.getDate());
            dogBreed.setText(needPost.getDogBreed());
            dogName.setText(needPost.getDogName());
            dogNeeds.setText(needPost.getDogNeeds());
            email.setText(needPost.getEmail());
            fullName.setText(needPost.getFullName());
            phone.setText(needPost.getPhone());
            pottyTrained.setText(needPost.getPottyTrained());
            title.setText(needPost.getTitle());
        }
    }

}
