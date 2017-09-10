package com.fduranortega.brastlewarktown.personlist.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.model.Person;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by FranAlterados on 8/9/17.
 */
public class RVPersonAdapter extends RecyclerView.Adapter<RVPersonAdapter.PersonViewHolder> {

    private static RecyclerViewClickListener itemListener;
    List<Person> lstPerson;
    Context context;

    public RVPersonAdapter(List<Person> lstPerson) {
        this.lstPerson = lstPerson;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_person, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        String image = lstPerson.get(position).getPhoto();
        String name = lstPerson.get(position).getName();
        String age = "Age: " + lstPerson.get(position).getAge();
        String hairColor = "Hair Color: " + lstPerson.get(position).getHairColor();
        String weight = "Weight: " + String.format("%.2f", Double.parseDouble(lstPerson.get(position).getWeight())) + " kg";
        String height = "Height: " + String.format("%.2f", Double.parseDouble(lstPerson.get(position).getHeight())) + " m";

        Picasso.with(context).load(image).into(holder.ivPersonPhoto);
        holder.tvPersonName.setText(name);
        holder.tvPersonAge.setText(age);
        holder.tvPersonHairColor.setText(hairColor);
        holder.tvPersonWeight.setText(weight);
        holder.tvPersonHeight.setText(height);

    }

    @Override
    public int getItemCount() {
        if (lstPerson != null) {
            return lstPerson.size();
        } else {
            return 0;
        }
    }

    public void swap(List<Person> lstPerson) {
        this.lstPerson.clear();
        this.lstPerson.addAll(lstPerson);
        notifyDataSetChanged();
    }

    public void setItemListener(RecyclerViewClickListener itemListener) {
        RVPersonAdapter.itemListener = itemListener;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cvPerson;
        ImageView ivPersonPhoto;
        TextView tvPersonName;
        TextView tvPersonAge;
        TextView tvPersonHairColor;
        TextView tvPersonWeight;
        TextView tvPersonHeight;

        PersonViewHolder(View itemView) {
            super(itemView);
            ivPersonPhoto = (ImageView) itemView.findViewById(R.id.ivPersonPhoto);
            cvPerson = (CardView) itemView.findViewById(R.id.cvPerson);
            tvPersonName = (TextView) itemView.findViewById(R.id.tvPersonName);
            tvPersonAge = (TextView) itemView.findViewById(R.id.tvPersonAge);
            tvPersonHairColor = (TextView) itemView.findViewById(R.id.tvPersonHairColor);
            tvPersonWeight = (TextView) itemView.findViewById(R.id.tvPersonWeight);
            tvPersonHeight = (TextView) itemView.findViewById(R.id.tvPersonHeight);

            cvPerson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListener.recyclerViewListClicked(view, getLayoutPosition()
                    );
                }
            });
        }
    }
}
