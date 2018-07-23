package com.brillicaservices.gurjas.firebasemoviessample.series;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brillicaservices.gurjas.firebasemoviessample.R;

import java.util.ArrayList;

public class SeriesListAdapter extends RecyclerView.Adapter<SeriesListAdapter.SeriesViewHolder> {
    ArrayList<SeriesModelView> seriesViewHolderArrayList = new ArrayList<>();
    private final ListItemClickListener itemClickListener;

    public interface ListItemClickListener {
        void onListItemClickListener(int clickedItemIndex);
    }


    public SeriesListAdapter(ArrayList<SeriesModelView> seriesViewHolderArrayList, SeriesActivity itemClickListener) {
        this.seriesViewHolderArrayList = seriesViewHolderArrayList;
        this.itemClickListener = (ListItemClickListener) itemClickListener;

    }

    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.series_list, parent, false);

        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(itemView);

        return seriesViewHolder;
    }


    @Override
    public void onBindViewHolder(SeriesViewHolder holder, int position) {
        SeriesModelView seriesModelView = seriesViewHolderArrayList.get(position);

        holder.seriesThumbnail.setImageResource(seriesModelView.image1);
        holder.seriesName.setText(seriesModelView.title1);
        holder.seriesDescription.setText(seriesModelView.description1);
        holder.rating.setText("" + seriesModelView.rating1 + "/5");
    }

    @Override
    public int getItemCount() {
        return seriesViewHolderArrayList.size();
    }


    public class SeriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView seriesThumbnail;
        TextView seriesName, seriesDescription, rating;

        public SeriesViewHolder(View itemView) {
            super(itemView);
            seriesThumbnail = itemView.findViewById(R.id.series_thumbnail);
            seriesName = itemView.findViewById(R.id.series_name_title);
            seriesDescription = itemView.findViewById(R.id.series_description);
            rating = itemView.findViewById(R.id.series_rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();

            itemClickListener.onListItemClickListener(clickedPosition);
        }
    }
}
