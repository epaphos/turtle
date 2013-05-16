package com.example.turtlestack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Myadapter extends BaseAdapter{

	
	private Activity activity;
    //private ArrayList&lt;HashMap&lt;String, String&gt;&gt; data;
    private ArrayList<Post> data;
    private ArrayList<User> user;
    private static String log = "Adapter";
    
    private static LayoutInflater inflater=null;
    //public ImageLoader imageLoader; //not needed
    
    //constructor
    public Myadapter(Activity a, ArrayList<Post> posts, ArrayList<User> user){
    	activity = a;
        data=posts;
        
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
		return data.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        Log.v(log,"getView called");
        
        	
        if (data.get(position).getPostTypeId()==1){
        	
                vi = inflater.inflate(R.layout.question_element, null);
                Log.v(log,"pos=0 inflated");
        	
        		TextView qTitle = (TextView) vi.findViewById(R.id.textViewQuestionTitle);
        		TextView qBody = (TextView) vi.findViewById(R.id.textViewQuestionBody);
        		TextView qAuthor = (TextView) vi.findViewById(R.id.textViewQuestionOwner);
        		TextView qReputation = (TextView) vi.findViewById(R.id.textViewQuestionOwnerRep);
        		TextView qCount = (TextView) vi.findViewById(R.id.textViewQuestionVotes);
        		
        		vi.setTag(position);
        		
        		Question question = (Question) data.get(position);
        		User tempUser = user.get(position);
        		qTitle.setText(question.getTitle());
        		qBody.setText(Html.fromHtml(question.getBody()).toString());
        		//TODO: Add tags to linear layout
        		
                qAuthor.setText("By: " + tempUser.getDisplayName());
                qReputation.setText("Reputation: " + String.valueOf(tempUser.getReputation()));
                qCount.setText(String.valueOf(question.getScore()));
                
                Button btnVoteUp = (Button) vi.findViewById(R.id.btnQuestionVoteUp);
                
                
               try{ 
            	   //btnVoteUp.setOnClickListener(onClickVoteUp);
            	   //TODO: find out why this throws null pointer exception but still works
            	   btnVoteUp.setOnClickListener(new OnClickListener(){
                	@Override
                	public void onClick(View view){
                		int pos =  (Integer) view.getTag();
                		
                		Log.v(log,"Clicked on VoteUpQuestion" + pos);
                	}
                });
               } catch (Exception e) {
            	   
            	   Log.v(log,"not able to attach OnClickListener to VoteUpQuestion" + e.toString());
               }
                
               try{
            	   qAuthor.setClickable(true);
            	   qAuthor.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						int pos = (Integer) v.getTag();
						Log.v(log,"Clicked on Author label in question");
						//TODO: fix Intent
						//Intent intent = new Intent(a, UserViewActivity.class);
						//intent.putExtra("userId", user.get(pos).getUserId()); //Sample Id which exists in database
						//startActivity(intent);
						
					}
				});
               } catch (Exception e) {
            	   
            	   Log.v(log,"not able to attach OnClickListener to Author label" + e.toString());
               }
                
        } 
        
        if (data.get(position).getPostTypeId()==2) //check that type equals answer
        {
        	
	        Log.v(log,"pos="+position);
	        //if(convertView==null)
	            vi = inflater.inflate(R.layout.answer_element, null);
	        //Log.v(log,"New thingy inflated");
	        
	        TextView body = (TextView)vi.findViewById(R.id.txtAnswer); 
	        TextView author = (TextView)vi.findViewById(R.id.txtviewAuthor); 
	        TextView reputation = (TextView)vi.findViewById(R.id.txtviewRep); 
	        TextView count = (TextView) vi.findViewById(R.id.textViewCount1);
	        CheckBox accepted = (CheckBox) vi.findViewById(R.id.chkAcceptedAnswer);
	        
	        vi.setTag(position);
	        //Log.v(log,"Got elements");
	        //Log.v(log,"body="+body.toString()+" author="+author.toString()+" reputation="+reputation.toString()+" count="+count.toString());
	        Answer answer = (Answer) data.get(position);
	        User usr = user.get(position);
	        
	        //Log.v(log,"got answer and user");
	        // Setting all values in listview
	        body.setText(Html.fromHtml(answer.getBody()).toString());
	        //body.setText(answer.getBody());
	        author.setText("By: " + usr.getDisplayName());
	        reputation.setText("Reputation: " + String.valueOf(usr.getReputation()));
	        count.setText(String.valueOf(answer.getScore()));
	        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
	        //Log.v(log,"filled values in ");
	        
	        if(answer.getId()==((Question)data.get(0)).getAcceptedAnswer()){
	        	accepted.setChecked(true);
	        }
	        
        	Button btnVoteUp = (Button) vi.findViewById(R.id.btnVoteup);
                
	        try {
	        	btnVoteUp.setOnClickListener(new OnClickListener(){
	        
	        	@Override
	        	public void onClick(View view){
	        		int pos = (Integer) view.getTag();
	        		Log.v(log,"Clicked on VoteUp for Answer " + pos);
	        	}
	        });
	        } catch (Exception e) {
	        	Log.v(log, "not able to attach listener AnswerVoteup" + e.toString());
	        }
        }
        
        
        
        return vi;
    }


	
	private OnClickListener onClickVoteUp = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Log.v(log, "VoteUp Clicked");
			//final int position = mListView.getPositionForView((View) v.getParent());
			//(ListView) v.getParent().;
		}
	};

}
