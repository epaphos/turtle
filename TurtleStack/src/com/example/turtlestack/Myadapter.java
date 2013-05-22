package com.example.turtlestack;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Myadapter extends BaseAdapter{

	
	private Activity activity;
    //private ArrayList&lt;HashMap&lt;String, String&gt;&gt; data;
    private ArrayList<Post> data;
    private ArrayList<User> user;
    private static String log = "Adapter";
    private String imageUrl = "http://www.gravatar.com/avatar/";
    private static LayoutInflater inflater=null;
    //public ImageLoader imageLoader; //not needed
    
    private Context context;

   
    //constructor
    public Myadapter(Context context, ArrayList<Post> posts, ArrayList<User> user){
    	activity = (Activity) context;
        data=posts;
        this.context = context;
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
        		ImageView profilePicture = (ImageView) vi.findViewById(R.id.userProfilePicture);                  
                TextView qTag = (TextView) vi.findViewById(R.id.questionTags);
        		
                vi.setTag(position);
        		
        		Question question = (Question) data.get(position);
        		User tempUser = user.get(position);
        		qTitle.setText(question.getTitle());
        		String bodyText = question.getBody().replace("\\n", " ");
        		qBody.setText(Html.fromHtml(bodyText));
        		String tmptag = question.getTags().replaceAll("<", "").replaceAll(">", ", ");
        	    qTag.setText(tmptag);

                qAuthor.setText("By: " + tempUser.getDisplayName());
                qAuthor.setTag(position);
                qReputation.setText("Reputation: " + String.valueOf(tempUser.getReputation()));
                qCount.setText(String.valueOf(question.getScore()));
                String emailHash = tempUser.getEmailHash();
                Bitmap bimage=  BitmapReciver.getBitmapFromURL(imageUrl + emailHash);
                profilePicture.setImageBitmap(bimage);
                
               try{
            	   qAuthor.setClickable(true);
            	   qAuthor.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Log.v("teststrings", "Clicked on Author label in question");
						int pos = (Integer) v.getTag();
						
						Intent intent = new Intent(context, UserViewActivity.class);
						intent.putExtra("userId", user.get(pos).getUserId()); //Sample Id which exists in database
						context.startActivity(intent);
						
					}
				});
               } catch (Exception e) {
            	   
            	   Log.v(log,"not able to attach OnClickListener to Author label" + e.toString());
               }
                
        } 
        
        if (data.get(position).getPostTypeId()==2) //check that type equals answer
        {
        	
	        Log.v(log,"pos="+position);
	            vi = inflater.inflate(R.layout.answer_element, null);
	        
	        TextView body = (TextView)vi.findViewById(R.id.txtAnswer); 
	        TextView author = (TextView)vi.findViewById(R.id.txtviewAuthor); 
	        TextView reputation = (TextView)vi.findViewById(R.id.txtviewRep); 
	        TextView count = (TextView) vi.findViewById(R.id.textViewCount1);
	        CheckBox accepted = (CheckBox) vi.findViewById(R.id.chkAcceptedAnswer);
	        ImageView profilePicture = (ImageView) vi.findViewById(R.id.answerProfilePicture);
	        
	        vi.setTag(position);
	        Answer answer = (Answer) data.get(position);
	        User usr = user.get(position);
	        
	        String bodyText = answer.getBody().replace("\\n", " ");
    		body.setText(Html.fromHtml(bodyText));
	        
	        author.setText("By: " + usr.getDisplayName());
	        author.setTag(position); 
	        reputation.setText("Reputation: " + String.valueOf(usr.getReputation()));
	        count.setText(String.valueOf(answer.getScore()));
	        String emailHash = usr.getEmailHash();
            Bitmap bimage=  BitmapReciver.getBitmapFromURL(imageUrl + emailHash);
            profilePicture.setImageBitmap(bimage);
	         
            if(answer.getId()==((Question)data.get(0)).getAcceptedAnswer()){
	        	accepted.setChecked(true);
	        }
	        try{
         	   author.setClickable(true);
         	   author.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Log.v("teststrings", "Clicked on Author label in question");
						int pos = (Integer) v.getTag();
						
						Intent intent = new Intent(context, UserViewActivity.class);
						intent.putExtra("userId", user.get(pos).getUserId()); //Sample Id which exists in database
						context.startActivity(intent);
						
					}
				});
            } catch (Exception e) {
         	   
         	   Log.v(log,"not able to attach OnClickListener to Author label" + e.toString());
            }    
	       
        }
               
        return vi;
    }
}
