package cn.edu.scau.hometown.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.kogitune.activity_transition.ActivityTransitionLauncher;

import cn.edu.scau.hometown.R;

/**
 *
 * @author JUN
 *
 */
public class SearchCourseMainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_course_guide);
//		getActionBar().setDisplayHomeAsUpEnabled(true);
//		getActionBar().setDisplayShowHomeEnabled(true);
	}
	public void actualSearch(View v){
		Intent intent =new Intent(SearchCourseMainActivity.this,SearchCoursesActivity.class);
		ActivityTransitionLauncher.with(SearchCourseMainActivity.this).from(v).launch(intent);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				this.finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
