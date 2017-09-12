package jogjamedianet.com.jogjamedianet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import jogjamedianet.com.jogjamedianet.ListPelanggan;
import jogjamedianet.com.jogjamedianet.Prefs.UserInfo;
import jogjamedianet.com.jogjamedianet.R;
import jogjamedianet.com.jogjamedianet.detil;

/**
 * Created by mery on 7/29/2017.
 */
public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder>{
    Context context;
    private UserInfo session;

    //menangkap data
    ArrayList<HashMap<String,String>> list_data;
    public AdapterList(ListPelanggan listpelanggan, ArrayList<HashMap<String,String>>list_data)
    {
        this.context=listpelanggan;
        this.list_data=list_data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        session = new UserInfo(context);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null);
        return  new  ViewHolder(view);
    }

    //onBindViewHolder berguna untuk menampilkan item data beserta posisi nya
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
     //   Glide.with(context)
              /*  .crossFade()
                .placeHolder(R.mipmap.ic_launcher)
                */
        //session=new UserInfo();
        session= new UserInfo(context);
        //String peru= list_data.get();
      //  holder.txtpel.setText(list_data.get(position).get("id"));
        holder.txtnamaperu.setText( list_data.get(position).get("namaperusahaan"));
        holder.txtjenisusaha.setText("Jenis Usaha : "+list_data.get(position).get("jenis_usaha"));
        holder.txtnamapel.setText("Nama Pelanggan : "+list_data.get(position).get("nama_pelanggan"));
    //    holder.txtid.setText(list_data.get(position).get("ID_Pegawai"));

        holder.item.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putString("namaperusahaan",holder.txtnamaperu.getText().toString());
                extras.putString("jenis_usaha",holder.txtjenisusaha.getText().toString());
                extras.putString("nama_pelanggan",holder.txtnamapel.getText().toString());
                Intent intent = new Intent(context, detil.class);
                intent.putExtras(extras);
                context.startActivity(intent);
        }});


    }
    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtid,txtnamaperu,txtnamapel,txtjenisusaha,txtpel;
        RelativeLayout item;

        public ViewHolder(View itemView)
        {


            super(itemView);
            txtnamaperu=(TextView) itemView.findViewById(R.id.txtnamaperusahaan);
            txtnamapel=(TextView) itemView.findViewById(R.id.txtnamapelanggan);
            txtjenisusaha=(TextView) itemView.findViewById(R.id.txtjenisusaha);
            this.item = (RelativeLayout) itemView.findViewById(R.id.item);
        }
    }
}
