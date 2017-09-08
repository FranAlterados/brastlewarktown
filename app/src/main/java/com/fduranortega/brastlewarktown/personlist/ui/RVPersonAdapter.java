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
        String name = lstPerson.get(position).getName();
        String age = "Age: " + lstPerson.get(position).getAge();
        String image = lstPerson.get(position).getPhoto();

        holder.personName.setText(name);
        holder.personAge.setText(age);
        Picasso.with(context).load(image).into(holder.personPhoto);
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

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cvPerson);
            personName = (TextView) itemView.findViewById(R.id.personName);
            personAge = (TextView) itemView.findViewById(R.id.personAge);
            personPhoto = (ImageView) itemView.findViewById(R.id.personPhoto);
        }
    }
}
