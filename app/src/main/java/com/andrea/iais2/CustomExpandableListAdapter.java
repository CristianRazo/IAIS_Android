package com.andrea.iais2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<RecursosGupo> listGrupo;
    private HashMap<RecursosGupo, List<RecursosHijo>> expandableListHijo;

    public CustomExpandableListAdapter(Context context, List<RecursosGupo> listGrupo, HashMap<RecursosGupo, List<RecursosHijo>> expandableListHijo) {
        this.context = context;
        this.listGrupo = listGrupo;
        this.expandableListHijo = expandableListHijo;
    }

    @Override
    public int getGroupCount() {
        return this.listGrupo.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandableListHijo.get(listGrupo.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listGrupo.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableListHijo.get(this.listGrupo.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        RecursosGupo recursosGrupo = (RecursosGupo) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.elv_group,null);
        }

        ImageView imageView1 = convertView.findViewById(R.id.imagen1);
        ImageView imageView2 = convertView.findViewById(R.id.imagen2);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),recursosGrupo.getLogo());
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(),recursosGrupo.getNombre());

        imageView1.setImageBitmap(bitmap);
        imageView2.setImageBitmap(bitmap1);


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        RecursosHijo recursosHijo = (RecursosHijo) getChild(groupPosition,childPosition);
         if(convertView == null){
             LayoutInflater layoutInflater = (LayoutInflater) this.context.
                     getSystemService(Context.LAYOUT_INFLATER_SERVICE);

             convertView = layoutInflater.inflate(R.layout.elv_child,null);
         }

        TextView textView1 = convertView.findViewById(R.id.primero);
        final EditText textView2 = convertView.findViewById(R.id.segundo);

        textView1.setText(recursosHijo.getNombre());
        textView2.setText(recursosHijo.getDato());




        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
