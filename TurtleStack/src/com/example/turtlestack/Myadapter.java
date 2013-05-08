package com.example.turtlestack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Myadapter extends BaseAdapter{

	private Activity activity;
    //private ArrayList&lt;HashMap&lt;String, String&gt;&gt; data;
    private ArrayList<Answer> data;
    private ArrayList<User> user;
    private Question question;
    private static String log = "Adapter";
    
    private static LayoutInflater inflater=null;
    //public ImageLoader imageLoader; //not needed
    
    //constructor
    public Myadapter(Activity a, Question q, ArrayList<Answer> posts, ArrayList<User> user){
    	activity = a;
        data=posts;
        question = q;
        this.user = user;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
        Log.v(log,"Adapter created");
        Log.v(log,"Answers expected = " + data.size());
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
        Log.v(log,"getView called");
        {
        	
        if (position==0){
        	if(convertView==null){
                vi = inflater.inflate(R.layout.question_element, null);
                Log.v(log,"pos=0 inflated");
        	}
        		TextView title = (TextView) vi.findViewById(R.id.textViewQuestionTitle);
        		TextView body = (TextView) vi.findViewById(R.id.textViewQuestionBody);
        		TextView author = (TextView) vi.findViewById(R.id.textViewQuestionOwner);
        		TextView rep = (TextView) vi.findViewById(R.id.textViewQuestionOwnerRep);
        		TextView count = (TextView) vi.findViewById(R.id.textViewQuestionVotes);
        		
        		title.setText(question.getTitle());
        		body.setText(Html.fromHtml(question.getBody()).toString());
                //author.setText(usr.getDisplayName());
                //reputation.setText(String.valueOf(usr.getReputation()));
                count.setText(String.valueOf(question.getScore()));
                
        } else {
        	
        Log.v(log,"pos="+position);
        //if(convertView==null)
            vi = inflater.inflate(R.layout.answer_element, null);
        Log.v(log,"New thingy inflated");
        
        TextView body = (TextView)vi.findViewById(R.id.txtAnswer); 
        TextView author = (TextView)vi.findViewById(R.id.txtviewAuthor); 
        TextView reputation = (TextView)vi.findViewById(R.id.txtviewRep); 
        TextView count = (TextView) vi.findViewById(R.id.textViewCount1);
        
        Log.v(log,"Got elements");
        if(body==null)
        	Log.v(log,"body == null!!");
        Log.v(log,"body="+body.toString()+" author="+author.toString()+" reputation="+reputation.toString()+" count="+count.toString());
        Answer answer = (Answer) data.get(position);
        User usr = user.get(position);
        
        Log.v(log,"got answer and user");
        // Setting all values in listview
        //body.setText(Html.fromHtml(answer.getBody()).toString());
        body.setText(answer.getBody());
        author.setText(usr.getDisplayName());
        reputation.setText(String.valueOf(usr.getReputation()));
        count.setText(String.valueOf(answer.getScore()));
        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        Log.v(log,"filled values in ");
        }
        }
        return vi;
    }

}
