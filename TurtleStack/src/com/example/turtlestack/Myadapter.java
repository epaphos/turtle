package com.example.turtlestack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Myadapter extends BaseAdapter{

	private Activity activity;
    //private ArrayList&lt;HashMap&lt;String, String&gt;&gt; data;
    private ArrayList<Post> data;
    private ArrayList<User> user;
    
    private static LayoutInflater inflater=null;
    //public ImageLoader imageLoader; //not needed
    
    //constructor
    public Myadapter(Activity a, ArrayList<Post> posts, ArrayList<User> user){
    	activity = a;
        data=posts;
        this.user = user;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }
    
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.answer_element, null);
 
        TextView body = (TextView)vi.findViewById(R.id.txtAnswer); 
        TextView author = (TextView)vi.findViewById(R.id.txtviewAuthor); 
        TextView reputation = (TextView)vi.findViewById(R.id.txtviewRep); 
        TextView count = (TextView) vi.findViewById(R.id.textViewCount1);
        
        Answer answer = (Answer) data.get(position);
        User usr = user.get(position);
 
        // Setting all values in listview
        body.setText(answer.getBody());
        author.setText(usr.getDisplayName());
        reputation.setText(String.valueOf(usr.getReputation()));
        count.setText(String.valueOf(answer.getScore()));
        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        return vi;
    }

}
