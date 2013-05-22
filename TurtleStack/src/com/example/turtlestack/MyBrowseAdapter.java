package com.example.turtlestack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyBrowseAdapter extends BaseAdapter {
	
	private Activity activity;
    private ArrayList<Question> questions;
    
    private static LayoutInflater inflater=null;
    
    public MyBrowseAdapter(Activity a, ArrayList<Question> qs){
    	activity = a;
        questions = qs;
        
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

	@Override
	public int getCount() {
		return questions.size(); 
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
	    if(convertView==null) 
	    	vi = inflater.inflate(R.layout.question_browse_element, null);
	        
	    TextView qTitle = (TextView) vi.findViewById(R.id.questionTitle);
	    TextView qTag = (TextView) vi.findViewById(R.id.questionTags);
	    TextView qNumberOfAnswers = (TextView) vi.findViewById(R.id.numberOfAnswers);
	    TextView qNumberOfVotes = (TextView) vi.findViewById(R.id.numberOfVotes);
	    
	    vi.setTag(position);
	    
	    Question question = questions.get(position);
	    qTitle.setText(question.getTitle());
	    
	    String tmptag = question.getTags().replaceAll("<", "").replaceAll(">", ", ");
	    qTag.setText(tmptag);
	    
	    
	    qNumberOfAnswers.setText(String.valueOf(question.getAnswerCount())); 
	    qNumberOfVotes.setText(String.valueOf(question.getScore())); 
	         
	     
	    return vi;
		
	}

}
